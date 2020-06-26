package com.tylerroonprapunt.riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

public class TrapezoidRule extends AbstractRiemann{
    /**
     * Slice calculates a Riemann sum, using the particular implementation of
           * slice(Polynomial, double, double) defined in the subclass.
     * @param poly: the polynomial whose area (over or under the x-axis),
     *            over the interval from sLeft to sRight, is to be calculated
     * @param sleft: the left hand endpoint of the interval
     * @param sright: the right hand endpoint of the interval
     * @return: the area of a given slice
     */
    public double slice​(Polynomial poly, double sleft, double sright) {
        double width = sright - sleft;
        double avgheight = (poly.evaluateWith(sleft) + poly.evaluateWith(sright))/2;
        double area = width * avgheight;
        return area;
    }

    /**
     * slicePlot, plots a slice of the (signed) area between the graph of a polynomial
     * and the x-axis, over a given interval on the x-axis.
     * @param pframe: the PlotFrame on which the slicePlot is to be drawn
     * @param poly: the polynomial whose area (over or under the x-axis), over the interval from xLeft to xRight, is to be drawn
     * @param sleft: the left hand endpoint of the interval
     * @param sright:  the right hand endpoint of the interval
     */
    public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
        Trail trail = new Trail();
        trail.addPoint(sleft,0);
        trail.addPoint(sleft,poly.evaluateWith(sleft));
        trail.addPoint(sright, poly.evaluateWith(sright));
        trail.addPoint(sright, 0);

        trail.color = Color.red;
        pframe.addDrawable(trail);
        pframe.setPreferredMinMax(-25, 25, -25, 25); // Scale of graph.
//        pframe.setDefaultCloseOperation(3); // Make it so x'ing out of the graph stops the program.
        pframe.setVisible(true); // Required to show plot frame.
    }
}
