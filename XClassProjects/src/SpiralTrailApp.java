import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

import java.util.ArrayList;
import java.util.Random;

/***
 * MovingBallApp is an extension of AbstractSimulation (an abstract class).
 *
 * An abstract class is a class that almost works except that the author intentionally left some methods empty. It is up
 * to the programmer who uses it to complete those empty methods for the class to fully work.
 *
 * In AbstractSimulation, these methods need to be completed:
 *     1. reset (adds options to the Control Panel)
 *     2. initialize (gets data from the Control Panel and sets up the graph)
 *     3. doStep (actions to take to do the animation, invoked every 1/10 second)
 *
 * You also need a main method to run the simulation (scroll to bottom).
 */
public class SpiralTrailApp extends AbstractSimulation {

    Circle circle = new Circle();
    Circle[] circles = new Circle[25];

    // Set up global variables.
    PlotFrame plotFrame = new PlotFrame("x","y", "Spiral Trail App");

    @Override
    public void reset() {

    }

    /**
     * Technically optional, but the simulation won't work without it.
     * Tied to the "Initialize" button. Gets information from the Control Panel and
     * configures the plot frame.
     */
    @Override
    public void initialize() {
        // Configure plot frame
        plotFrame.setPreferredMinMax(0, 10, 0, 10); // Scale of graph.
        DrawableShape rect = DrawableShape.createRectangle(3, 3, 5, 5);
        plotFrame.addDrawable(rect);

        for (int i = 0; i < 5; i++) {
            circles[i] = new Circle();
            circles[i].setX(1 + i);
            circles[i].setY(1 + i);
        }
    }

    /**
     * Required method, invoked once every 1/10 second until Stop is pressed.
     * The doStep method is also called when the Step button is pressed.
     */
    public void doStep() {

    }

    /**
     * Optional method, tied to Stop button.
     */
    @Override
    public void stop(){

    }

    /**
     * Required main method, runs the simulation.
     * @param args
     */
    public static void main(String[] args) {
        SimulationControl.createApp(new SpiralTrailApp());
        // The x and y parameters place the center of the rectangle.

    }
}
