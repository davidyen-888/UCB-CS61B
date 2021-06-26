package bearmaps;

import java.util.List;

public class KDTree implements PointSet {
    private static final boolean HORIZONTAL = false;
    private static final boolean VERTICAL = true;
    private Node root;

    private class Node {
        private Point p;
        private boolean orientation;
        private Node leftChild, rightChild;   // rightChild can also be upChild(depends on level), vice versa.

        public Node(Point givenP, boolean o) {
            p = givenP;
            orientation = o;
        }
    }

    public KDTree(List<Point> points) {
        for (Point p : points) {
            root = add(p, root, HORIZONTAL);
        }
    }

    /** Adds a point to the KDTree */
    private Node add(Point p, Node n, boolean orientation) {
        if (n == null) {
            return new Node(p, orientation);
        }
        int cmp = comparePoints(n.p, p, orientation);
        if (cmp < 0) {
            n.leftChild = add(p, n.leftChild, !orientation);
        } else if (cmp > 0) {
            n.rightChild = add(p, n.rightChild, !orientation);
        }
        return n;
    }

    private int comparePoints(Point a, Point b, boolean orientation) {
        if (orientation == HORIZONTAL) {
            return Double.compare(a.getX(), b.getX());
        } else {
            return Double.compare(a.getY(), b.getY());
        }
    }

    @Override
    public Point nearest(double x, double y) {
        return null;
    }

    public static void main(String[] args) {
        // Constructs Points as the KDTree insertion demo in
        // @Source: https://docs.google.com/presentation/d/1WW56RnFa3g6UJEquuIBymMcu9k2nqLrOE1ZlnTYFebg/edit#slide=id.g54b6045b73_0_38
        Point A = new Point(2, 3);
        Point B = new Point(4, 2);
        Point C = new Point(4, 5);
        Point D = new Point(3, 3);
        Point E = new Point(1, 5);
        Point F = new Point(4, 4);

        KDTree kd = new KDTree(List.of(A, B, C, D, E, F));
    }
}
