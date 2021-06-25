/*
  Create a naive linear-time solution to solve the problem of finding the closest point to
  a given coordinate. The goal of creating this class is that you will have a alternative,
  albeit slower, solution that you can use to easily verify if the result of your k-d tree’s
  nearest is correct.
 */
package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> points;

    public NaivePointSet(List<Point> points) {   // assume points has at least size 1
        this.points = points;
    }

    /**
     * Returns the closest point to the inputted coordinates. This should take (theta(N))
     * time where (N) is the number of points.
     */
    @Override
    public Point nearest(double x, double y) {
        Point point = new Point(x, y);
        Point nearestPoint = null;
        double minDistance = 0;
        for (Point p : points) {
            if (minDistance == 0 || Point.distance(point, p) < minDistance) {
                minDistance = Point.distance(point, p);
                nearestPoint = p;
            }
        }
        return nearestPoint;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        if (ret.getX() == 3.3 && ret.getY() == 4.4) {
            System.out.println("pass");
        } else {
            System.out.println("fail");
        }
    }
}
