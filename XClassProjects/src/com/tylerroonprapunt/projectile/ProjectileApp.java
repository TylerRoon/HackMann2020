package com.tylerroonprapunt.projectile;

import org.hamcrest.Condition;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

public class ProjectileApp extends AbstractSimulation {
    PlotFrame plotFrame = new PlotFrame("x", "y", "Projectile App");
    PlotFrame posovert = new PlotFrame("seconds","meters from ground", "Position Over Time");
    PlotFrame vovert = new PlotFrame("seconds", "velocity (m/s)", "Velocity Over Time");
    PlotFrame aovert = new PlotFrame("seconds", "acceleration (m/s^2)", "Acceleration Over Time");
    PlotFrame TwoDMotion = new PlotFrame("y","x","2D Motion");

    Trail trail = new Trail();
    Trail trail2 = new Trail();
    Trail trail3 = new Trail();
    Trail trail4 = new Trail();
    Circle circle1 = new Circle();
    Circle circle2 = new Circle();
    Circle circle3 = new Circle();
    Circle circle4 = new Circle();
    Particle particle1 = new Particle();
    double g = 9.81;
    double beta = 5;
    Particle particle2 = new Particle(5, -10, 100,0,0,g,beta);
    Particle particle3 = new Particle(5, 10, 100,0,0,g,beta);
    Particle particle4 = new Particle(5, 0, 0,0,0,g,beta);
    double totalTime;

    @Override
    public void reset() {
        control.setValue("Starting X1 position", 0);
        control.setValue("Starting Y1 position", 100);
        control.setValue("Starting X2 position", 10);
        control.setValue("Starting Y2 position", 100);
        control.setValue("Starting X3 position", -10);
        control.setValue("Starting Y3 position", 100);
        control.setValue("Beta", 0.5);
        control.setValue("Theta", 45);
        control.setValue("Initial Velocity", 1);
    }

    @Override
    public void initialize() {
        double startingY1 = control.getDouble("Starting Y1 position");
        circle1.setY(startingY1);
        double startingX1 = control.getDouble("Starting X1 position");
        circle1.setX(startingX1);
        plotFrame.addDrawable(circle1);
        circle4.setX(0);
        circle4.setX(0);
        TwoDMotion.addDrawable(circle4);


        trail.color = Color.RED;
        trail2.color = Color.RED;
        trail3.color = Color.RED;
        trail4.color = Color.BLACK;
        posovert.setPreferredMinMax(0,5,0,100);
        posovert.addDrawable(trail);
        vovert.setPreferredMinMax(0,5,0,50);
        vovert.addDrawable(trail2);
        aovert.setPreferredMinMax(0,10,-15,15);
        aovert.addDrawable(trail3);
        TwoDMotion.setPreferredMinMax(-1,40,-1,20);
        TwoDMotion.addDrawable(trail4);

//        double startingY2 = control.getDouble("Starting Y2 position");
//        circle2.setY(startingY2);
//        double startingX2 = control.getDouble("Starting X2 position");
//        circle2.setX(startingX2);
//        plotFrame.addDrawable(circle2);
//
//        double startingY3 = control.getDouble("Starting Y3 position");
//        circle3.setY(startingY3);
//        double startingX3 = control.getDouble("Starting X3 position");
//        circle3.setX(startingX3);
//        plotFrame.addDrawable(circle3);

        plotFrame.setPreferredMinMax(-25, 25, 0, startingY1); // Scale of graph.
        plotFrame.setDefaultCloseOperation(3); // Make it so x'ing out of the graph stops the program.
        plotFrame.setVisible(true); // Required to show plot frame.
    }

    public void doStep() {
        // Change y. (It will re-draw itself.)
        particle1.step(0.1);
        circle1.setY(particle1.getY());

        trail.addPoint(totalTime, circle1.getY());
        trail2.addPoint(totalTime, -particle1.getYvelocity());

        particle4.twoStep(0.1, 45, 1);
        circle4.setX(particle4.getX());
        circle4.setY(particle4.getY());
        trail4.addPoint(circle4.getX(),circle4.getY());
        System.out.println("this point is (" + circle4.getX() + ", " + circle4.getY() + ")");
        trail4.color = Color.BLACK;

        totalTime=totalTime+0.1;
    }

    @Override
    public void stop(){
        System.out.println(totalTime/10 + " secs to travel " + Math.abs(
                control.getDouble("Starting Y1 position") - circle1.getY())+ " meters.");
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new ProjectileApp());
    }

}
