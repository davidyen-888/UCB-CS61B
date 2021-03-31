/**
 * The goal of this class is to simulate a universe specified in one of the data
 * files. Input example: java NBody 20000000 20000 ./data/planets.txt
 */
public class NBody {
    private static final String backgroundMusic = "./audio/2001.mid"; // background music

    public static double readRadius(String file) {
        In bodiesIn = new In(file);

        // int planetNumber = planetsIn.readInt();
        // double radius = planetsIn.readDouble();
        // return radius;

        bodiesIn.readDouble(); // Skip over first lines.
        return bodiesIn.readDouble();
    }

    public static Body[] readBodies(String file) {
        In bodiesIn = new In(file); // Starts reading in planets.txt
        int bodiesNum = bodiesIn.readInt(); // First line of number is the number of planets.
        Body[] bodies = new Body[bodiesNum]; // "bodies" Array Instantiation.
        // Skip over 2 lines.
        bodiesIn.readLine();
        bodiesIn.readLine();
        for (int i = 0; i < bodiesNum; i++) {
            bodies[i] = new Body(bodiesIn.readDouble(), bodiesIn.readDouble(), bodiesIn.readDouble(),
                    bodiesIn.readDouble(), bodiesIn.readDouble(), bodiesIn.readString());
        }
        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]); // https://stackoverflow.com/questions/5769669/convert-string-to-double-in-java
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        In bodiesIn = new In(filename);
        int bodiesNum = bodiesIn.readInt();
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);

        // Universe setted from (-radius, -radius) up to (radius,radius).
        StdDraw.setScale(-radius, radius);

        /** Create Animation */
        StdDraw.enableDoubleBuffering(); // This is a graphics technique to prevent flickering in the animation.
        int time = 0;
        while (time < T) {
            // Create an xForces array and yForces array.
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            // Calculate the net x and y forces for each Body, storing these in the xForces
            // and yForces arrays respectively.
            for (int i = 0; i < bodiesNum; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            // Call update on each of the Bodys. This will update each body’s position,
            // velocity, and acceleration.
            for (int i = 0; i < bodiesNum; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            // Draw the Background.
            String imageToDraw = "images/starfield.jpg";
            StdDraw.picture(0, 0, imageToDraw);
            // Draw all of the Bodys.
            // @source:
            // https://blogs.oracle.com/corejavatechtips/using-enhanced-for-loops-with-your-classes
            for (Body b : bodies) {
                b.draw();
            }
            // Show the offscreen buffer. Display on screen and pause for 10 milliseconds
            StdDraw.show();
            StdDraw.pause(10);
            // Increase your time variable by dt.
            time += dt;
            // Play the space-audio
            StdAudio.play(backgroundMusic);
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", bodies[i].xxPos, bodies[i].yyPos,
                    bodies[i].xxVel, bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}
