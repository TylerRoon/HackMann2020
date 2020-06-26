package XClassProjects.src.com.tylerroonprapunt.Planet;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.util.ArrayList;

public class Orbits extends AbstractSimulation {
    public PlotFrame space = new PlotFrame("x","y", "Orbit Simulation");

    Circle circle1 = new Circle();
    Circle circle2 = new Circle();

    public static double G = 6.67*Math.pow(10,-11);
    public double timestep;
    public static boolean updateGraph;

    public ArrayList<Planet> planets = new ArrayList<Planet>();

    @Override
    public void reset() {

    }
    @Override
    public void initialize() {
        planets.add(new Planet(6E+24, 0, 0.5*Math.pow(10,5),new Vector(3,Math.PI/2)));
        planets.add(new Planet(6E+24, 0, -0.5*Math.pow(10,5),new Vector(3,3*Math.PI/2)));
        planets.add(new Planet(6E+24, 1*Math.pow(10,4), 0,new Vector(3,Math.PI)));
        timestep = 2000;
    }
    public void doStep() {
        for(int n = 0; n < 10; n++) {
            if(n == 0){
                updateGraph = true;
            }else if (n == 1){
                updateGraph = false;
            }else{}
            ///set FG
            for (int i = 0; i < planets.size(); i++) { //planet in question
                Vector sum = new Vector(0, 0);
                for (int j = 0; j < planets.size(); j++) { //planet in relation
                    if (i == j) {
                    } else {
                        Vector holder = fg(planets.get(i), planets.get(j));
                        sum = sum.plus(holder);
                    }
                }
                planets.get(i).setAppliedFg(sum);
                sum.setTheta(0);
                sum.setMagnitude(0);
            }
            //update XY
            for (int i = 0; i < planets.size(); i++) {
                
            }
        }
    }
    @Override
    public void stop() { }
    public static void main(String[] args) {
        SimulationControl.createApp(new Orbits());
    }
    public static Vector fg(Planet planetI, Planet planetJ) {
        double magnitude = G*planetI.getMass()*planetJ.getMass()/distance(planetI,planetJ);
        double theta = Math.atan((planetI.getY()-planetJ.getY())/(planetI.getX()-planetJ.getX()));
        Vector result = new Vector(magnitude, theta);
        return result;
    }



    public static double distance(Planet planet1, Planet planet2) {
        double distance = Math.sqrt(Math.pow((planet1.getX() - planet2.getX()),2) + Math.pow((planet1.getY() - planet2.getY()),2));
        return distance;
    }
}
