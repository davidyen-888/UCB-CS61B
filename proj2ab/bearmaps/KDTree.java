package bearmaps;

import java.util.List;

public class KDTree implements PointSet {
    private static final int XAxis = 0;
    private static final int YAxis = 1;
    private Node root;

    private static class Node {
        public Point pt;
        public int dimension;
        public Node leftChild, rightChild; // rightChild can also be upChild(depends on level), vice versa.

        public Node(Point p, int di, Node leftChild, Node rightChild) {
            pt = p;
            dimension = di;
            this.rightChild = rightChild;
            this.leftChild = leftChild;
        }
    }

    public KDTree(List<Point> points) {
        root = null;
        for (Point p : points) {
            add(p);
        }
    }

    public void add(Point p) {
        root = add(p, root, XAxis);
    }

    /** Adds a point to the KDTree */
    private Node add(Point p, Node n, int or) {
        if (n == null) {
            return new Node(p, or, null, null);
        } else if (p.equals(n.pt)) { // node already exists
            return n;
        } else {
            double cmp = comparePoints(p, n.pt, or);
            if (cmp < 0) {
                n.leftChild = add(p, n.leftChild, (or + 1) % 2);
            } else { // cmp >= 0
                n.rightChild = add(p, n.rightChild, (or + 1) % 2);
            }
        }
        return n;
    }

    /** Compares the two points' x or y value based on the orientation. */
    private double comparePoints(Point a, Point b, int di) {
        if (di == XAxis) {
            return a.getX() - b.getX();
        } else {
            return a.getY() - b.getY();
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        return nearestHelper(root, goal, root).pt;
    }

    private Node nearestHelper(Node n, Point goal, Node best) {
        // base case, if reaches end of KDTree, return best
        if (n == null) {
            return best;
        }
        // Changes best base on comparing different points
        if (Point.distance(n.pt, goal) < Point.distance(best.pt, goal)) {
            best = n;
        }
        // Determines good and bad side
        Node goodSide, badSide;
        double cmp = comparePoints(goal, n.pt, n.dimension);
        if (cmp < 0) { // goal < n
            goodSide = n.leftChild;
            badSide = n.rightChild;
        } else {
            goodSide = n.rightChild;
            badSide = n.leftChild;
        }
        // Finds all good sides first by recursion
        best = nearestHelper(goodSide, goal, best);

        // Pruning, checks bad sides also if possible
        if (Math.pow(cmp, 2) < Point.distance(best.pt, goal)) {
            best = nearestHelper(badSide, goal, best);
        }
        return best;
    }
}
