package com.tylerroonprapunt.riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.frames.PlotFrame;

public class RandomRule extends AbstractRiemann {
    public double slice​(Polynomial poly, double sleft, double sright) {
        double width = sright - sleft;
        double random = Math.random()*width + sleft;
        double height = poly.evaluateWith(random);
        double area = height * width;
        return area;
    }
    public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
        double xcenter = (sright+sleft) / 2;
    }
}
