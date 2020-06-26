package com.tylerroonprapunt.riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

public class ArcLength extends AbstractRiemann {
    /**
     *
     * @param poly: the polynomial whose area (over or under the x-axis),
     *            over the interval from sLeft to sRight, is to be calculated
     * @param xleft: the left point of the slice
     * @param xright: the right point of the slice
     * @return: the distance of the line from xLeft to xRight
     */
    public double slice​(Polynomial poly, double xleft, double xright) {
        double yleft = poly.evaluateWith(xleft);
        double yright = poly.evaluateWith(xright);
        double deltaxsquared = Math.pow((xright-xleft),2);
        double deltaysquared = Math.pow((yright-yleft),2);

        double distance = Math.sqrt(deltaxsquared+deltaysquared);
        return distance;
    }

    /**
     * @param pframe: the PlotFrame on which the slicePlot is to be drawn
     * @param poly: the polynomial whose area (over or under the x-axis), over the interval from xLeft to xRight, is to be drawn
     * @param sleft: the left hand endpoint of the interval
     * @param sright:  the right hand endpoint of the interval
     */
    public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
        Trail trail = new Trail();
        trail.addPoint(sleft,poly.evaluateWith(sleft));
        trail.addPoint(sright,poly.evaluateWith(sright));
        trail.color = Color.BLACK;
        pframe.addDrawable(trail);
        pframe.setVisible(true);
    }
}
