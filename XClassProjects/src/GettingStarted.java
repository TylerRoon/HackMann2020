import org.dalton.polyfun.Polynomial;

import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.Color;

/**
 * GettingStarted class to show off Polynomials, Open Source Physics and JUnit test.
 */
public class GettingStarted {
    public static void main(String[] args) {
        /*
             Polynomial examples
         */
        Polynomial fx = new Polynomial(2);
        Polynomial vx = new Polynomial(new double[]{1, 2, 3});
        Polynomial gx = new Polynomial(new double[]{5,0,0,0,4});

        ////
        System.out.println("g(x) = " + gx);
        System.out.println("f(x) = " + fx);
        System.out.println("v(x) = " + vx);
        System.out.println("v(2) = " + vx.evaluateWith(2));
        System.out.println("f(x) + v(x) = " + fx.plus(vx));
        System.out.println("v(x)" + vx);
        System.out.println(vx.getCoefficientAtTerm(1));
        System.out.println("degree: "+vx.getDegree());
        System.out.println("vx:"+vx);
        for (int i = 0; i < vx.getDegree()+1; i++) {
            System.out.println(vx.getCoefficientAtTerm(i));
        }

        ////
        System.out.println();
        System.out.println();
        ////

        System.out.println("f(x) = " + fx);
        System.out.println("v(x) = " + vx);
        System.out.println("f(x) * v(x) = " + fx.times(vx));

        System.out.println();
        System.out.println("f(2) = " + fx.evaluateWith(2));

        System.out.println();
        System.out.println(vx.getCoefficientAtTerm(2));
        System.out.println(vx.getCoefficientArray()[2]);

        /*
              Open Source Physics (OSP) Example
         */
        // Setup the graph
        PlotFrame plotFrame = new PlotFrame("x", "y", "Getting Started");
        plotFrame.setSize(400, 400); // window size
        plotFrame.setPreferredMinMax(0, 10, 0, 15); // x and y ranges
        plotFrame.setConnected(true); // if you want to connect the dots
        plotFrame.setDefaultCloseOperation(3);  // if you want closing the graph to end the program
        plotFrame.setVisible(true); // need this to show the graph, it is false by default

        // Data set 0 (red line)
        plotFrame.setLineColor(0, Color.RED);
//        plotFrame.append(0, 0, 0);
//        plotFrame.append(0, 1, 1);
//        plotFrame.append(0, 2, 2);
//        plotFrame.append(0, 3, 3);
        Trail redtrail = new Trail();
        redtrail.color = Color.RED;
        for (int i = 0; i < 10; i++) {
            redtrail.addPoint(i,fx.evaluateWith(i));
        }
        plotFrame.addDrawable(redtrail);



        // Data set 1 (green line)
        plotFrame.setLineColor(1, Color.GREEN);
//        plotFrame.append(1, 0, 0);
//        plotFrame.append(1, 1, 1);
//        plotFrame.append(1, 1.5, 2.25);
//        plotFrame.append(1, 2, 4);
//        plotFrame.append(1, 2.5, 6.25);
//        plotFrame.append(1, 3, 9);
//        plotFrame.append(1, 3.5, 12.25);
        Trail greentrail = new Trail();
        greentrail.color = Color.GREEN;
        for (int i = 0; i < 10; i++) {
            greentrail.addPoint(i,vx.evaluateWith(i));
        }
        plotFrame.addDrawable(greentrail);

        // Example of a Trail.
        Trail orangetrail = new Trail();
        orangetrail.color = Color.ORANGE;
        for (int i = 0; i < 10; i++) {
            orangetrail.addPoint(i,gx.evaluateWith(i));
        }
        plotFrame.addDrawable(orangetrail); // add the trail to the plot frame

        // Add trail points.
    }



    /**
     * Go to /test/GettingStartedTest.java to see how to test this method with Junit.
     *
     * @param a first num to add
     * @param b second num to add
     * @return the sum
     */

    public static int add(int a, int b) {
        return a + b;
    }
}
