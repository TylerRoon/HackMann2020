package com.tylerroonprapunt.riemann;

import org.opensourcephysics.display.Trail;

import java.awt.*;

public abstract class AbstractRiemann {

    /** CALCULATE DELTAX
     * Calculates Δx based on the endpoints of the Riemann sum and the number of subintervals;
     * in other words, this method finds the width of each rectangle.
     * @param left: the left hand endpoint of the Riemann sum
     * @param right: the right hand endpoint of the Riemann sum
     * @param subintervals: the number of subintervals into which to divide the interval
     * @return: delta x
     */
    public double calculateDeltaX(double left, double right, int subintervals) {
        double deltaX = Math.abs(right - left) / subintervals;
        return deltaX;
    }

    //add abstract methods
    /** SLICE
     * Approximates a slice of the (signed) area between the graph of a polynomial and the x-axis,
     * over a given interval on the x-axis. The specific Riemann sum rule used in the calculation is
     * defined by the subclass. This method should be used in rs(Polynomial, double, double, int)
     * to find the total area.
     * @param poly: the polynomial whose area (over or under the x-axis),
     *            over the interval from sLeft to sRight, is to be calculated
     * @param sleft: the left hand endpoint of the interval
     * @param sright: the right hand endpoint of the interval
     * @return: A slice of the com.tylerroonprapunt.riemann sum
     */
    public abstract double slice​(org.dalton.polyfun.Polynomial poly, double sleft, double sright);

    /** SLICEPLOT
     * Plots a slice of the (signed) area between the graph of a polynomial and the x-axis,
     * over a given interval on the x-axis. The specific Riemann sum rule used is defined by the subclass.
     * This method should be used in rsPlot(PlotFrame, Polynomial, double, double, int) to find the total area.
     * @param pframe: the PlotFrame on which the slicePlot is to be drawn
     * @param poly: the polynomial whose area (over or under the x-axis), over the interval from xLeft to xRight, is to be drawn
     * @param sleft: the left hand endpoint of the interval
     * @param sright:  the right hand endpoint of the interval
     */
    public abstract void slicePlot(org.opensourcephysics.frames.PlotFrame pframe, org.dalton.polyfun.Polynomial poly, double sleft, double sright);

    /** RS
     * Calculates a Riemann sum, using the particular implementation of
     * slice(Polynomial, double, double) defined in the subclass.
     * @param poly: the polynomial whose Riemann sum is to be calculated
     * @param left: the left hand endpoint of the Riemann sum
     * @param right: the right hand endpoint of the Riemann sum
     * @param subintervals: the number of subintervals into which to divide the interval
     * @return: An approximation of the Riemann sum over the interval [left, right].
     */
    //write rs
    public double rs​(org.dalton.polyfun.Polynomial poly, double left, double right, int subintervals) {
        double sum = 0;
        double total = right - left;
        double widthRect = total / subintervals;
        for (int i = 0; i < subintervals; i++) {
            sum = sum + slice​(poly, left + widthRect*i, (left + widthRect*(i+1)));
        }
        return sum;
    }

    /** RSPLOT
     * Plots a Riemann sum in a given PlotFrame.
     * @param pframe: the PlotFrame on which the polynomial and the Riemann sum are drawn
     * @param poly: the polynomial whose Riemann sum is to be calculated
     * @param left: the left hand endpoint of the Riemann sum
     * @param right: the right hand endpoint of the Riemann sum
     * @param subintervals: the number of subintervals into which to divide the interval
     */
    //plot rs
    public void rsPlot​(org.opensourcephysics.frames.PlotFrame pframe, org.dalton.polyfun.Polynomial poly, double left, double right, int subintervals) {
        //Plots a Riemann sum in a given PlotFrame
        double widthRect = calculateDeltaX(left,right,subintervals);

        for (int i = 0; i < subintervals; i++) {
            slicePlot(pframe, poly, left + i*widthRect, left + (i+1)*widthRect);
        }
    }

    /**
     *
     * @param pframe: the PlotFrame on which the polynomial and the Riemann sum are drawn
     * @param poly: the polynomial whose accumulation function is to be calculated
     * @param xLeft: the left hand endpoint of the accumulation function
     * @param xRight: the right hand endpoint of the accumulation function
     */
    //Graphs the accumulation function corresponding to the input polynomial and the input left hand endpoint.
    public void rsAcc​(org.opensourcephysics.frames.PlotFrame pframe, org.dalton.polyfun.Polynomial poly, double xLeft,double xRight, int subintervals) {
        double sum = 0;
        Trail trail = new Trail();
        trail.color = Color.green;
        for (double i = xLeft; i < xRight; i=i+0.1) {
            sum = sum + slice​(poly, i, i+calculateDeltaX(xLeft,xRight,subintervals));
            trail.addPoint(i,sum);
        }
        pframe.addDrawable(trail);
        pframe.setVisible(true);
    }
}

