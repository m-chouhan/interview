package locusV2;

import locusV2.polyline.PolylineDecoder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/** This class fetches list of points from a backend api, and then adds new points to the
    list based on given granularity.
    TODO : Use abstractions instead. 
*/
public class GMaps {

    static GMaps instance;
    final OkHttpClient httpClient;
    final PolylineDecoder polylineDecoder;

    static GMaps getInstance() {
        if(instance == null)
            instance = new GMaps();
        return instance;
    }

    public GMaps() {
        // one instance, reuse
        httpClient = new OkHttpClient();
        polylineDecoder = new PolylineDecoder();
    }

    /* Returns a list of points along shortest distance with given granularity */    
    List<Point> findPath(Point source, Point dest, double distance) {
        try {
            List<Point> path = fetchPath(source, dest);
            return updatePath(path, distance);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* Adds new points in list if distance < lenght of adjacent points. */    
    List<Point> updatePath(List<Point> path, double distance) {

        LinkedList<Point> linkedList = new LinkedList<>();

        for(int i = 1; i < path.size(); ++i) {
            Point current = path.get(i), prev = path.get(i-1);
            double currentDist = measureDistance(current, prev);

            if(currentDist > distance) {
                List<Point> newPath = breakPath(prev, current, distance);
                linkedList.addAll(newPath);
            }
            else if(Math.abs(currentDist - distance) < 0.05f ) {
                linkedList.add(current); 
            }
        }

        return linkedList;
    }

    /* Addes points between previous and current points by distance */
    List<Point> breakPath(Point prev, Point current, double distance) {
        LinkedList<Point> points = new LinkedList<>();
        double dx = current.lat - prev.lat, dy = current.lon - prev.lon;
        double reqDistance = measureDistance(prev, current);
        Point dirVector = new Point(dx, dy);
        double currentDist = 0;
        points.add(prev);

        while((currentDist + distance) < reqDistance) {
            currentDist += distance;
            double ratio = currentDist/reqDistance;
            Point point = new Point(dirVector.lat * ratio + prev.lat, dirVector.lon * ratio + prev.lon);
            points.add(point);
        }
        points.add(current);
        return points;
    }

    /* Returns distance between two points */
    double measureDistance(Point current, Point prev) {
        double p = Math.PI/180;
        double lat2 = current.lat, lat1 = prev.lat, lon2 = current.lon, lon1 = prev.lon;
        double a = 0.5 - Math.cos((lat2 - lat1) * p)/2 +
                        Math.cos(lat1 * p) * Math.cos(lat2 * p) *
                        (1 - Math.cos((lon2 - lon1) * p))/2;
        return 12742 * Math.asin(Math.sqrt(a)); // 2 * R; R = 6371 km
    }

    /* Fetch path from a backend api */    
    List<Point> fetchPath(Point source, Point dest) throws Exception {

        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/directions/json?" +
                        "origin=" + source.toString() +
                        "&destination=" + dest.toString() +
                        "&key=AIzaSyAb8ohmBXqtK4y2_a5CFnFnfLGiOsuwjIo")
                .addHeader("User-Agent", "OkHttp Bot")
                .build();
        List<Point> list = new LinkedList<>();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String jsonData = response.body().string();
            JSONObject jobject = new JSONObject(jsonData);
            JSONArray routesArray = jobject.getJSONArray("routes");
            JSONArray legsArray = ((JSONObject)routesArray.get(0)).getJSONArray("legs");
            //System.out.println(legsArray);
            for(int i = 0; i < legsArray.length(); ++i) {
                JSONObject legsItem = legsArray.getJSONObject(i);
                JSONArray stepsArray = legsItem.getJSONArray("steps");
                for(int j = 0; j < stepsArray.length(); ++j) {
                    JSONObject stepsItem = stepsArray.getJSONObject(j);
                    JSONObject start = stepsItem.getJSONObject("start_location");
                    Point stPoint = new Point(start);
                    JSONObject end = stepsItem.getJSONObject("end_location");
                    Point enPoint = new Point(end);
                    list.add(stPoint);
                    String polyPoints = stepsItem.getJSONObject("polyline").getString("points");
                    List<Point> pointList = polylineDecoder.decode(polyPoints);
                    list.addAll(pointList);
                    list.add(enPoint);
                }
            }
        }
        return list;
    }
}
