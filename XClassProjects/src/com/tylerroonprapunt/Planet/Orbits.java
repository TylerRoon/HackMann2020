package XClassProjects.src.com.tylerroonprapunt.Planet;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.util.ArrayList;

public class Orbits extends AbstractSimulation {
    PlotFrame space = new PlotFrame("x","y", "Orbit Simulation");

    Circle circle1 = new Circle();
    Circle circle2 = new Circle();

    public static double G = 6.67*Math.pow(10,-11);
    public double timestep;
    public static boolean updateGraph;

    public ArrayList<Planet> planets = new ArrayList<Planet>();

    @Override
    public void reset() {
        control.setValue("Starting X0", 0);
        control.setValue("Starting Y0", 0);
        control.setValue("Starting V0", 0);
        control.setValue("Mass 0", 6E+24);
        control.setValue("Starting X1", 10);
        control.setValue("Starting Y1", 0);
        control.setValue("Starting V1", 0);
        control.setValue("Mass 1", 6E+24);
        control.setValue("Starting X2", -10);
        control.setValue("Starting Y2", 0);
        control.setValue("Starting V2", 0);
        control.setValue("Mass 2", 6E+24);
    }
    @Override
    public void initialize() {
        planets.add(new Planet(0, 0, 0.5*Math.pow(10,5),new Vector(3,Math.PI/2,true), new Circle(control.getDouble("Starting X0"), control.getDouble("Starting Y0"))));
        planets.add(new Planet(0, 0, -0.5*Math.pow(10,5),new Vector(3,3*Math.PI/2,true), new Circle(control.getDouble("Starting X1"), control.getDouble("Starting Y1"))));
        planets.add(new Planet(0, 1*Math.pow(10,4), 0,new Vector(3,Math.PI,true), new Circle(control.getDouble("Starting X2"), control.getDouble("Starting Y2"))));

        planets.get(0).setMass(control.getDouble("Mass 0"));
        planets.get(1).setMass(control.getDouble("Mass 1"));
        planets.get(2).setMass(control.getDouble("Mass 2"));

        planets.get(0).setX(control.getDouble("Starting X0"));
        planets.get(1).setX(control.getDouble("Starting X1"));
        planets.get(2).setX(control.getDouble("Starting X2"));

        planets.get(0).setY(control.getDouble("Starting Y0"));
        planets.get(1).setY(control.getDouble("Starting Y1"));
        planets.get(2).setY(control.getDouble("Starting Y2"));

        space.setPreferredMinMax(-100,100,-100,100);
        for (int i = 0; i <planets.size() ; i++) {
            space.addDrawable(planets.get(i).getCircle());
        }

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
            Vector sum = new Vector(0, 0);
            for (int i = 0; i < planets.size(); i++) { //planet in question
                for (int j = 0; j < planets.size(); j++) { //planet in relation
                    if (i == j) {
                    } else {
                        sum = sum.plus(fg(planets.get(i), planets.get(j)));
                    }
                }
                planets.get(i).setAppliedFg(sum);
                sum.setTheta(0);
                sum.setMagnitude(0);
            }
            //update XY
            for (int i = 0; i < planets.size(); i++) {
                Planet currentPlanet = planets.get(i);
                Vector vt = currentPlanet.getVelo().plus(new Vector((currentPlanet.getAppliedFg().getMagnitude() * timestep)/currentPlanet.getMass(), currentPlanet.getAppliedFg().getTheta(), true));
                if(updateGraph){
                    currentPlanet.visSetXY(currentPlanet.getX() + (currentPlanet.velo.getX() + vt.getX()) * timestep * .5, currentPlanet.getY() + (currentPlanet.velo.getY() + vt.getY()) * timestep * .5);
                }else{
                    currentPlanet.setXY(currentPlanet.getX() + (currentPlanet.velo.getX() + vt.getX()) * timestep * .5, currentPlanet.getY() + (currentPlanet.velo.getY() + vt.getY()) * timestep * .5);
                }
                currentPlanet.velo = vt.clone();
            }
            System.out.println(planets.get(0).toString());
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
