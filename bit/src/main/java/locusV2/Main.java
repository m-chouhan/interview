package locusV2;

import java.util.List;

public class Main {

    public static void main(String [] args) {
        Point source = new Point(12.94523, 77.61896), dest = new Point(12.95944, 77.66085);
        List<Point> path = GMaps.getInstance().findPath(source, dest, 0.1);
        for(Point point : path)
            System.out.println(point + ",");
    }
}
