package locusV2.polyline;

import locusV2.Point;

import java.util.List;

public class PolylineUtils {

    public static String toString(List<Point> polyline) {
        String str = "[ ";
        for( Point p : polyline) {
            str += p;
        }
        return str + " ]";
    }

    public static String toMarkers(List<Point> polyline) {
        String str = "";
        for( Point p : polyline) {
            str += "|" + p.getLat()+","+p.getLon();
        }
        return str.substring(1, str.length());
    }
}