package HackMann2020.src.com.tylerroonprapunt.Planet;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;
import java.util.ArrayList;

public class Orbits extends AbstractSimulation {
    PlotFrame space = new PlotFrame("x","y", "Orbit Simulation");

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
        control.setValue("Starting V1", 30000);
        control.setValue("Mass 1", 6E+24);
        control.setValue("Starting X2", 1.5E+11);
        control.setValue("Starting Y2", 1.5E+10);
        control.setValue("Starting V2", 0);
        control.setValue("Mass 2", 7E+23);
    }
    @Override
    public void initialize() {
        planets.add(new Planet(0, 0, 0.5*Math.pow(10,5),new Vector(control.getDouble("Starting V0"),Math.PI/2,true), new Circle(control.getDouble("Starting X0"), control.getDouble("Starting Y0")), 6.96E+8));
        planets.add(new Planet(0, 0, -0.5*Math.pow(10,5),new Vector(control.getDouble("Starting V1"),Math.PI/2,true), new Circle(control.getDouble("Starting X1"), control.getDouble("Starting Y1")), 6.96E+8));
        planets.add(new Planet(0, 0, -0.5*Math.pow(10,5),new Vector(163,30000), new Circle(control.getDouble("Starting X2"), control.getDouble("Starting Y2")), 6.96E+8));

        planets.get(0).setMass(control.getDouble("Mass 0"));
        planets.get(1).setMass(control.getDouble("Mass 1"));
        planets.get(2).setMass(control.getDouble("Mass 2"));

        planets.get(0).setX(control.getDouble("Starting X0"));
        planets.get(1).setX(control.getDouble("Starting X1"));
        planets.get(2).setX(control.getDouble("Starting X2"));

        planets.get(0).setY(control.getDouble("Starting Y0"));
        planets.get(1).setY(control.getDouble("Starting Y1"));
        planets.get(2).setY(control.getDouble("Starting Y2"));

        planets.get(0).getCircle().color = Color.YELLOW;
        planets.get(1).getCircle().color = Color.BLUE;
        planets.get(2).getCircle().color = Color.GRAY;

        planets.get(0).getCircle().pixRadius = 20;
        planets.get(1).getCircle().pixRadius = 10;
        planets.get(2).getCircle().pixRadius = 5;

        space.setSize(800,800);
        space.setPreferredMinMax(-2E+11,2E+11,-2E+11,2E+11);
        for (int i = 0; i <planets.size() ; i++) {
            space.addDrawable(planets.get(i).getCircle());
        }
        timestep = 20000;
    }
    public void doStep() {
        double maxFg = 1;
        for(int n = 0; n < 10; n++) {
            if (n == 0) {
                updateGraph = true;
            } else if (n == 1) {
                updateGraph = false;
            }
            Vector sum = new Vector(0, 0);
            for (int i = 0; i < planets.size(); i++) { //planet in question
                for (int j = 0; j < planets.size(); j++) { //planet in relation
                    if (i == j) {
                    } else {
                        if (distance(planets.get(i), planets.get(j)) <= planets.get(i).getRad() + planets.get(j).getRad()) {
                            Vector totalMomentum = new Vector(planets.get(i).getVelo().getMagnitude() * planets.get(i).getMass(), planets.get(i).getVelo().getTheta(), true).plus(new Vector(planets.get(j).getVelo().getMagnitude() * planets.get(j).getMass(), planets.get(j).getVelo().getTheta(), true));
                            totalMomentum.magnitude /= planets.get(i).getMass() + planets.get(j).getMass();

                            planets.get(i).setMass(planets.get(i).getMass() + planets.get(j).getMass());
                            planets.get(i).setVelo(totalMomentum);

                            planets.get(j).getCircle().pixRadius = 0;
                            planets.get(j).setMass(0);
                            planets.get(j).setXY(planets.get(j).getX() + planets.get(j).getRad() + planets.get(i).getRad(), planets.get(j).getX() + planets.get(j).getRad() + planets.get(i).getRad());
                        }
                        sum = sum.plus(fg(planets.get(i), planets.get(j)));
                    }
                }
                planets.get(i).setAppliedFg(sum);
                if (sum.getMagnitude() > maxFg) {
                    maxFg = sum.getMagnitude();
                }
                sum.setTheta(0);
                sum.setMagnitude(0);
            }
            //update XY
            for (int i = planets.size() - 1; i >= 0; i--) {
                Planet currentPlanet = planets.get(i);
                Vector a = new Vector((currentPlanet.getAppliedFg().getMagnitude()) / currentPlanet.getMass(), currentPlanet.getAppliedFg().getTheta(), true);
                currentPlanet.setVelo(new Vector(currentPlanet.getVelo().clone().getX() + a.getX() * timestep, currentPlanet.getVelo().clone().getY() + a.getY() * timestep));
                if (updateGraph) {
                    currentPlanet.visSetXY(currentPlanet.getX() + currentPlanet.getVelo().getX() * timestep, currentPlanet.getY() + currentPlanet.getVelo().getY() * timestep);
                } else {
                    currentPlanet.setXY(currentPlanet.getX() + currentPlanet.getVelo().getX() * timestep, currentPlanet.getY() + currentPlanet.getVelo().getY() * timestep);
                }

                if (planets.get(i).getCircle().pixRadius == 0.0) {
                    planets.remove(i);
                }
                maxFg = 1;
            }
        }
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
