package com.tylerroonprapunt.riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

public class RightHandRule extends AbstractRiemann{
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
        double height = poly.evaluateWith(sright);
        double area = width * height;
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
        //rectangles are created using the center points
        double xcenter = (sright+sleft) / 2;
        double ycenter = poly.evaluateWith(sright)/2;
        DrawableShape rect = DrawableShape.createRectangle(xcenter, ycenter, sright-sleft, poly.evaluateWith(sright));
        pframe.addDrawable(rect);

        // Configure plot frame
        pframe.setPreferredMinMax(-25, 25, -25, 25); // Scale of graph.
//        pframe.setDefaultCloseOperation(3); // Make it so x'ing out of the graph stops the program.
        pframe.setVisible(true); // Required to show plot frame.

    }
}
