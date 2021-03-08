public class Body {
    /** Instance variables */
    public double xxPos = 1.0;
    public double yyPos = 2.0;
    public double xxVel = 3.0;
    public double yyVel = 4.0;
    public double mass = 5.0;
    public String imgFileName = "jupiter.gif";
    public static double G = 6.67e-11;

    /**
     * First constructor, which takes six arguments, initializes these fields for
     * all new Body objects.
     */
    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /**
     * The second constructor should take in a Body object and initialize an
     * identical Body object (i.e. a copy).
     */
    public Body(Body b) {
        this(b.xxPos, b.yyPos, b.xxVel, b.yyVel, b.mass, b.imgFileName);
    }

    /** Calculates the distance between two Bodys. */
    public double calcDistance(Body b) {
        return Math.sqrt(
                (this.xxPos - b.xxPos) * (this.xxPos - b.xxPos) + (this.yyPos - b.yyPos) * (this.yyPos - b.yyPos));
    }

    /** Returns a double describing the force exerted on this body. */
    public double calcForceExertedBy(Body b) {
        return (G * this.mass * b.mass) / (calcDistance(b) * calcDistance(b));
    }

    /** Describe the force exerted in the X directions. */
    public double calcForceExertedByX(Body b) {
        return (((G * this.mass * b.mass) / (calcDistance(b) * calcDistance(b))) * (b.xxPos - this.xxPos))
                / calcDistance(b);
    }

    /** Describe the force exerted in the Y directions. */
    public double calcForceExertedByY(Body b) {
        return (((G * this.mass * b.mass) / (calcDistance(b) * calcDistance(b))) * (b.yyPos - this.yyPos))
                / calcDistance(b);
    }

    /**
     * Calculates the net X force exerted by all bodies in that array upon the
     * current Body.
     */
    public double calcNetForceExertedByX(Body[] b) {
        double xTotal = 0;
        for (int i = 0; i < b.length; i++) {
            if (!b[i].equals(this)) {
                xTotal += calcForceExertedByX(b[i]);
            }
        }
        return xTotal;
    }

    /**
     * Calculates the net Y force exerted by all bodies in that array upon the
     * current Body.
     */
    public double calcNetForceExertedByY(Body[] b) {
        double yTotal = 0;
        for (int i = 0; i < b.length; i++) {
            if (!b[i].equals(this)) {
                yTotal += calcForceExertedByY(b[i]);
            }
        }
        return yTotal;
    }

    /**
     * Determines how much the forces exerted on the body will cause that body to
     * accelerate, and the resulting change in the body’s velocity and position in a
     * small period of time dt.
     */
    public void update(double dt, double fX, double fY) {
        // Calculate acceleration.
        double aNetX = fX / this.mass;
        double aNetY = fY / this.mass;
        // Calculate new velocity.
        this.xxVel += dt * aNetX;
        this.yyVel += dt * aNetY;
        // Calculate new position
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}