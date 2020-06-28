package XClassProjects.src.com.tylerroonprapunt.Planet;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;
import java.util.Random;
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
        control.setValue("Mass 0", 2E+30);
        control.setValue("Starting X1", 1.5E+11);
        control.setValue("Starting Y1", 0);
        control.setValue("Starting V1", 0);
        control.setValue("Mass 1", 6E+24);
        control.setValue("Starting X2", 1.5E+11);
        control.setValue("Starting Y2", 1.5E+10);
        control.setValue("Starting V2", 0);
        control.setValue("Mass 2", 7E+22);
    }
    @Override
    public void initialize() {
        space.clearDrawables();
        Random gen = new Random();
        for (int i = 0; i < 100 ; i++) {
            double x = gen.nextInt(10)*5E+10-1E+11;
            double y = gen.nextInt(10)*5E+10-1E+11;
            planets.add(new Planet(5E+30,x ,y,new Vector(0,0),new Circle(x,y)));
            }
        space.setPreferredMinMax(-2E+11,2E+11,-2E+11,2E+11);
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
                //updateGraph = false;
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
                Vector a = new Vector((currentPlanet.getAppliedFg().getMagnitude())/currentPlanet.getMass(),currentPlanet.getAppliedFg().getTheta(), true);
                currentPlanet.setVelo(new Vector(currentPlanet.getVelo().clone().getX() + a.getX()*timestep, currentPlanet.getVelo().clone().getY() + a.getY()*timestep));
                currentPlanet.visSetXY(currentPlanet.getX() + currentPlanet.getVelo().getX()*timestep,currentPlanet.getY() + currentPlanet.getVelo().getY()*timestep);
            }
        }
        double xMax = 0;
        double yMax = 0;
        for (int i = 0; i <planets.size() ; i++) {
            if (planets.get(i).getY()>yMax){
                yMax = planets.get(i).getY();
            }
            if (planets.get(i).getX() > xMax){
                xMax = planets.get(i).getX();
            }
        }
        space.setPreferredMinMax(-2*xMax,2*xMax,-2*yMax,2*yMax);
    }
    @Override
    public void stop() { }
    public static void main(String[] args) {
        SimulationControl.createApp(new Orbits());
    }


    public static Vector fg(Planet planetI, Planet planetJ) {
        double magnitude = G*planetI.getMass()*planetJ.getMass()/(distance(planetI,planetJ)*distance(planetI,planetJ));
        double theta;
        if(planetI.getX() < planetJ.getX()) {
            theta = Math.atan((planetI.getY() - planetJ.getY()) / (planetI.getX() - planetJ.getX()));
        }else if(planetI.getX() > planetJ.getX()){
            theta = Math.atan((planetI.getY() - planetJ.getY()) / (planetI.getX() - planetJ.getX()))+Math.PI;
        }else {
            theta = Math.atan((planetI.getY() - planetJ.getY()) / (planetI.getX() - planetJ.getX())+1);
        }
        Vector result = new Vector(magnitude, theta,true);
        return result;
    }

    public static double distance(Planet planet1, Planet planet2) {
        double distance = Math.sqrt(Math.pow((planet1.getX() - planet2.getX()),2) + Math.pow((planet1.getY() - planet2.getY()),2));
        return distance;
    }
}
