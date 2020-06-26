package com.tylerroonprapunt.P;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

public class BaseballSimulationApp extends AbstractSimulation {

    PlotFrame Green = new PlotFrame("x", "y", "Green Monster Simulation");
    DrawableShape rect = DrawableShape.createRectangle(95.5, 5.665, 3, 11.33);

    Circle circle = new Circle();
    P particle = new P(5, 0, 0, 0, 0, 0, 0, 0);

    double totalTime;
    double addTheta = 1;
    double addVelocity = 0;
    double counter = 0;

    @Override
    public void reset() {
        control.setValue("time", 0.1);
        control.setValue("x", 0);
        control.setValue("y", 1);
        control.setValue("initial velocity", 36);
        control.setValue("theta", 29);
        control.setValue("beta", 0.01);

        //// DATA:

        ////regular initial velocity 33;
        ////regular theta 43)
        ////regular fail stops around 60

        ////air resistance baseball beta = 0.3

        ////air resistance initial velocity 36
        ////air resistance theta 41
        ////air resistance fail stops around 58

        ////velocity squared initial velocity
        ////velocity squared theta
        ////velocity squared fail stops around
    }

    @Override
    public void initialize() {
        addTheta = 1;
        addVelocity = 0;
        Green.setPreferredMinMax(0, 110, 0, 80); // Scale of graph.
        Green.addDrawable(rect);
        Green.addDrawable(circle);
        Green.addDrawable(trail);

        double initV = control.getDouble("initial velocity");
        double theta = control.getDouble("theta") - 1;
        double xv = initV * Math.cos(Math.toRadians(theta));
        double yv = initV * Math.sin(Math.toRadians(theta));
        particle.setXv(xv);
        particle.setYv(yv);

        circle.setX(control.getDouble("x"));
        particle.setX(circle.getX());
        circle.setY(control.getDouble("y"));
        particle.setY(circle.getY());
    }

    boolean firsttime = true;
    boolean hitwall = false;
    boolean lasttime = false;
    boolean newtrail = true;
    Trail trail = new Trail();
    Trail trailf = new Trail();
    boolean delay = false;
    double timer = 0;

    public void doStep() {
        if (newtrail) {
            trail = new Trail();
            trail.color = Color.BLACK;
            trail.addPoint(0, 1);
            Green.addDrawable(trail);
            newtrail = false;
        }
        double time = control.getDouble("time");
        double theta = control.getDouble("theta") - 1;
        double beta = control.getDouble("beta");
        if (!delay) {
//            particle.twoStep(time);
//            particle.twoStepVelocity(time,beta);
            particle.twoStepVelocitySquared(time,beta);
            circle.setX(particle.getX());
            circle.setY(particle.getY());
            trail.addPoint(circle.getX(), circle.getY());
        }

        double y = particle.getY();
        double x = particle.getX();

        ///doesn't hit wall, no home run
        if ((y < 0) && (x < 94)) {
            newtrail = true;
            circle.setX(control.getDouble("x"));
            circle.setY(control.getDouble("y"));
            particle.setX(control.getDouble("x"));
            particle.setY(control.getDouble("y"));
            double initV = control.getDouble("initial velocity");
            initV = initV + addVelocity;
            if (firsttime && !lasttime) {
                control.println("Initial Velocity: " + initV);
                firsttime = false;
            }
            theta = control.getDouble("theta") - 1;
            theta = theta + addTheta;
            control.println("Theta1: " + theta + " degrees failed");
            addTheta++;
            double xv = initV * Math.cos(Math.toRadians(theta));
            double yv = initV * Math.sin(Math.toRadians(theta));
            particle.setXv(xv);
            particle.setYv(yv);
        }

        ////hits wall
        else if ((y <= 11.33) && (x >= 94)) {
            newtrail = true;
            hitwall = true;
            circle.setX(control.getDouble("x"));
            circle.setY(control.getDouble("y"));
            particle.setX(control.getDouble("x"));
            particle.setY(control.getDouble("y"));
            double initV = control.getDouble("initial velocity");
            initV = initV + addVelocity;
            if (firsttime && !lasttime) {
                control.println("Initial Velocity: " + initV);
                firsttime = false;
            }
            theta = control.getDouble("theta") - 1;
            theta = theta + addTheta;
            control.println("Theta2: " + theta + " degrees failed");
            addTheta++;
            double xv = initV * Math.cos(Math.toRadians(theta));
            double yv = initV * Math.sin(Math.toRadians(theta));
            particle.setXv(xv);
            particle.setYv(yv);
        }

        //////STOP PROGRAM
        if ((hitwall) && (y < 0) && (x < 94)) {
            newtrail = true;
            double initV = control.getDouble("initial velocity");
            initV = initV + addVelocity + 1;
            double holder = initV;
            control.println("");
            control.println("Home run is not possible at this velocity");
            control.println("Therefore the minimum possible velocity is " + holder);
            control.println("");

            delay = true;

            addVelocity = addVelocity + 1;
            addTheta = 1;
            lasttime = true;
        }

        if (delay) {
            timer++;
        }
        if ((timer > 100) && delay) {
            timer = 0;
            stopSimulation();
        }

        ////you just hit a home run
        else if ((y >= 11.33) && (x >= 95.5)) {
            Green.clearDrawables();
            Green.addDrawable(trail);
            trailf = trail;
            trail.color = Color.GREEN;
            Green.addDrawable(rect);
            newtrail = true;
            circle.setX(0);
            circle.setY(1);
            particle.setX(0);
            particle.setY(1);
            double initV = control.getDouble("theta");
            initV = initV + addVelocity;
            theta = control.getDouble("theta") - 1;
            double holder = theta + addTheta;
            addTheta = 1;
            control.println("HOME RUN, the minimum theta for a home run was " + holder);
            control.println("");
            addVelocity = addVelocity - 1;

            hitwall = false;
            firsttime = true;
            counter++;
        }
        totalTime = totalTime + 0.1;
    }

    @Override
    public void stop() {
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new BaseballSimulationApp());
    }
}

