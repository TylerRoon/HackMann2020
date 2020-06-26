package com.tylerroonprapunt.riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

public class RiemannApp {
    public static void main(String[] args) {
//        Polynomial poly = new Polynomial(new double[]{0,7,-3});
        Polynomial poly = new Polynomial(new double[]{0,15,-3});
        PlotFrame rightHandPlot = new PlotFrame("x","y","Right Hand Plot");
        PlotFrame leftHandPlot = new PlotFrame("x","y","Left Hand Plot");
        PlotFrame trapPlot = new PlotFrame("x","y","Trapezoid Plot");
        PlotFrame maxPlot = new PlotFrame("x","y","Max Plot");
        PlotFrame minPlot = new PlotFrame("x","y","Min Plot");
        PlotFrame arcPlot = new PlotFrame("x","y","Arc Length Plot");

        RightHandRule rightHandRule = new RightHandRule();
        LeftHandRule leftHandRule = new LeftHandRule();
        TrapezoidRule trapRule = new TrapezoidRule();
        MaximumRule maxRule = new MaximumRule();
        MinimumRule minRule = new MinimumRule();
        ArcLength arcLength = new ArcLength();

        int subintervals = 19;

        //////right hand rule
        rightHandRule.rsPlot​(rightHandPlot, poly, 0,5, subintervals);
        rightHandPlot.setPreferredMinMax(0,5,0,20); // Scale of graph.
        rightHandPlot.setVisible(true); // Required to show plot frame.

        Trail redtrail2 = new Trail();
        redtrail2.color = Color.RED;
        for (double i = 0; i < subintervals; i=i+0.1) {
            redtrail2.addPoint(i,poly.evaluateWith(i));
        }
        rightHandPlot.addDrawable(redtrail2);

        //////left hand rule
        leftHandRule.rsPlot​(leftHandPlot, poly, 0,5, subintervals);
        leftHandPlot.setPreferredMinMax(0,5,0,20); // Scale of graph.
        leftHandPlot.setVisible(true); // Required to show plot frame.

        Trail redtrail = new Trail();
        redtrail.color = Color.RED;
        for (double i = 0; i < subintervals; i=i+0.1) {
            redtrail.addPoint(i,poly.evaluateWith(i));
        }
        leftHandPlot.addDrawable(redtrail);

        ////////trapezoid rule
        trapRule.rsPlot​(trapPlot, poly, 0,5, subintervals);
        trapPlot.setPreferredMinMax(0,5,0,20); // Scale of graph.
        trapPlot.setVisible(true); // Required to show plot frame.

        Trail trail2 = new Trail();
        trail2.color = Color.BLUE;
        for (double i = 0; i < subintervals; i=i+0.1) {
            trail2.addPoint(i,poly.evaluateWith(i));
        }
        trapPlot.addDrawable(trail2);

        ////////maximum rule
        maxRule.rsPlot​(maxPlot, poly, 0,5, subintervals);
        maxPlot.setPreferredMinMax(0,5,0,20); // Scale of graph.
        maxPlot.setVisible(true); // Required to show plot frame.

        Trail trail4 = new Trail();
        trail4.color = Color.BLUE;
        for (double i = 0; i < subintervals; i=i+0.1) {
            trail4.addPoint(i,poly.evaluateWith(i));
        }
        maxPlot.addDrawable(trail4);

        ////////minimum rule
        minRule.rsPlot​(minPlot, poly, 0,5, subintervals);
        minPlot.setPreferredMinMax(0,5,0,20); // Scale of graph.
        minPlot.setVisible(true); // Required to show plot frame.

        Trail trail5 = new Trail();
        trail5.color = Color.BLUE;
        for (double i = 0; i < subintervals; i=i+0.1) {
            trail5.addPoint(i,poly.evaluateWith(i));
        }
        minPlot.addDrawable(trail5);

        /////arclength
        arcLength.rsPlot​(arcPlot, poly, 0,5, subintervals);
        arcPlot.setPreferredMinMax(0,0.5,0,3); // Scale of graph.
        arcPlot.setVisible(true); // Required to show plot frame.

        Trail trail3 = new Trail();
        trail3.color = Color.blue;
        for (double i = 0; i < subintervals; i=i+0.01) {
            trail3.addPoint(i,poly.evaluateWith(i));
        }
        arcPlot.addDrawable(trail3);

        int Lvalue = 0;
        int Rvalue = 5;

        System.out.println("Right hand estimated area: " + rightHandRule.rs​(poly,Lvalue, Rvalue, subintervals));
        System.out.println("Left hand estimated area: " + leftHandRule.rs​(poly,Lvalue, Rvalue, subintervals));
        System.out.println("Trapezoid estimated area: " + trapRule.rs​(poly,Lvalue, Rvalue,subintervals));
        System.out.println("Maximum rule estimated area: " + maxRule.rs​(poly,Lvalue, Rvalue,subintervals));
        System.out.println("Minimum rule estimated area: " + minRule.rs​(poly,Lvalue, Rvalue,subintervals));
        System.out.println("Actual area according to integral calculator: " + 62.5);
        System.out.println("");
        System.out.println("Therefore, the closest area is the trazpeoid estimated area.");
        System.out.println("This makes sense because the right hand rule and the left hand rule will always be");
        System.out.println("either greater than or less than the average.");
        System.out.println("The trapezoid rule, which essentially finds the average between the right and the left hand rule,");
        System.out.println("will find the closest approximation out of the 3 rules.");
        System.out.println("Note: in this example, right hand and left hand rule find the same area because it is a parabola");
        System.out.println("");
        System.out.println("Extensions:");
        System.out.println("Maximum rule estimated area: " + maxRule.rs​(poly,0, 2,subintervals));
        System.out.println("Minimum rule estimated area: " + minRule.rs​(poly,0, 2,subintervals));
        System.out.println("Arc Length estimated length: " + arcLength.rs​(poly,0, 1,subintervals));

        Polynomial poly2 = new Polynomial(new double[]{0,7,-3});
        PlotFrame accPlot = new PlotFrame("x","y","Accumulation Plot");
        accPlot.setPreferredMinMax(0,10,-10,30); // Scale of graph./ Scale of graph.
        rightHandRule.rsAcc​(accPlot,poly2,0,3,subintervals);

        Trail trail6 = new Trail();
        trail6.color = Color.blue;
        for (double i = -10; i < subintervals; i=i+0.01) {
            trail6.addPoint(i,poly2.evaluateWith(i));
        }

        accPlot.addDrawable(trail6);
        accPlot.setVisible(true);

    }
}
