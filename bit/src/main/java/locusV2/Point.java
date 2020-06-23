package locusV2;

import org.json.JSONObject;

public class Point {

    double lat, lon;

    public Point(double lat, double lon) { this.lat = lat; this.lon = lon; }

    public Point(JSONObject jsonObject) {
        lon = jsonObject.getDouble("lng");
        lat = jsonObject.getDouble("lat");
    }

    @Override
    public String toString() {
        return lat + "," + lon;
    }

    public double getLat() { return lat; }
    public double getLon() { return lon; }
}
