package com.tylerroonprapunt.P;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;
import org.w3c.dom.ls.LSInput;

import java.awt.*;

public class PApp extends AbstractSimulation {
    PlotFrame OneD = new PlotFrame("x", "y", "Falling Ball Simulation");
    Trail trail1 = new Trail();
    Circle circle1 = new Circle();
    P particle1 = new P(5,0,100,0,0,0,0,0);
    Circle circle1b = new Circle();
    P particle1b = new P(5, -10, 100, 0,0,0,0,0);
    Circle circle1c = new Circle();
    P particle1c = new P(5, 10, 100, 0,0,0,0,0);

    PlotFrame AT = new PlotFrame("seconds", "acc (m/s^2)", "Acceleration over Time");
    Trail AT1 = new Trail();
    Trail AT2 = new Trail();
    Trail AT3 = new Trail();

//    PlotFrame XT = new PlotFrame("seconds", "meters from ground", "Position over Time");
//    Trail trailxt = new Trail();

    PlotFrame VT = new PlotFrame("seconds", "meters from ground", "Velocity over Time");
    Trail trailvt = new Trail();

    PlotFrame TwoD = new PlotFrame("x","y", "2D motion");
    Trail trail2 = new Trail();
    Circle circle2 = new Circle();
    P particle2 = new P(5,0,0,0,0,0,0,0);

    Trail trail2b = new Trail();
    Trail VT2 = new Trail();
    Circle circle2b = new Circle();
    P particle2b = new P(5,0,0,0,0,0,0,0);

    Trail trail2c = new Trail();
    Trail VT3 = new Trail();
    Circle circle2c = new Circle();
    P particle2c = new P(5,0,0,0,0,0,0,0);


    double totalTime;

    @Override
    public void reset() {
        control.setValue("Delta Time", 0.1);
        control.setValue("x", 0);
        control.setValue("y", 100);
        control.setValue("x2", 0);
        control.setValue("y2", 0);
        control.setValue("theta", 45);
        control.setValue("initial velocity", 20);
        control.setValue("beta", 0.5);
    }

    @Override
    public void initialize() {
        double startingY1 = control.getDouble("y");
        circle1.setY(startingY1);
        double startingX1 = control.getDouble("x");
        circle1.setX(startingX1);

        double initV = control.getDouble("initial velocity");
        double theta = control.getDouble("theta");
        double yv =  initV*Math.sin(Math.toRadians(theta));
        double xv = initV*Math.cos(Math.toRadians(theta));
        particle2.setYv(yv);
        particle2.setXv(xv);
        particle2b.setYv(yv);
        particle2b.setXv(xv);
        particle2c.setYv(yv);
        particle2c.setXv(xv);

        circle1b.setX(particle1b.getX());
        circle1b.setY(particle1b.getY());
        circle1c.setX(particle1c.getX());
        circle1c.setY(particle1c.getY());

        double startingY2 = control.getDouble("y2");
        circle2.setY(startingY2);
        particle2.setY(circle2.getY());
        double startingX2 = control.getDouble("x2");
        circle2.setX(startingX2);
        particle2.setX(circle2.getX());

        circle2b.setY(0);
        particle2b.setY(circle2b.getY());
        circle2b.setX(0);
        particle2b.setX(circle2b.getX());
        VT2.color = Color.BLUE;
        VT.addDrawable(VT2);

        circle2c.setY(0);
        particle2c.setY(circle2c.getY());
        circle2c.setY(0);
        particle2c.setX(0);
        VT3.color = Color.GREEN;
        VT.addDrawable(VT3);

//        circle2c.setY(startingY2);
//        particle2c.setY(circle2c.getY());
//        circle2c.setX(startingX2);
//        particle2c.setX(circle2c.getX());

        ////////
        trail1.color = Color.RED;
        OneD.addDrawable(circle1);
        OneD.addDrawable(trail1);
        circle1b.color = Color.blue;
        OneD.addDrawable(circle1b);
        circle1c.color = Color.GREEN;
        OneD.addDrawable(circle1c);
        OneD.setPreferredMinMax(-25, 25, 0, startingY1); // Scale of graph.
        OneD.setDefaultCloseOperation(3); // Make it so x'ing out of the graph stops the program.
        OneD.setVisible(true); // Required to show plot frame.

        ////////
//        trailxt.color = Color.RED;
//        XT.addDrawable(trailxt);
//        XT.setPreferredMinMax(0,5,0,100);
//        XT.setDefaultCloseOperation(3);
//        XT.setVisible(true);

        ////////
        trailvt.color = Color.RED;
        VT.addDrawable(trailvt);
        VT.setPreferredMinMax(0,5,-25,25);
        VT.setDefaultCloseOperation(3);
        VT.setVisible(true);

        ///////
        trail2.color = Color.RED;
        TwoD.addDrawable(trail2);
        TwoD.addDrawable(circle2);
        trail2b.color = Color.BLUE;
        TwoD.addDrawable(trail2b);
        TwoD.addDrawable(circle2b);
        TwoD.addDrawable(trail2c);

        TwoD.addDrawable(circle2c);
        trail2c.color = Color.GREEN;
        TwoD.addDrawable(trail2c);
        TwoD.addDrawable(circle2c);

        TwoD.setPreferredMinMax(0,100,0,30);
//        TwoD.setPreferredMinMax(0,3,0,10);
        TwoD.setDefaultCloseOperation(3);
        TwoD.setVisible(true);

        /////
        AT1.color = Color.RED;
        AT.addDrawable(AT1);
        AT2.color = Color.BLUE;
        AT.addDrawable(AT2);
        AT3.color = Color.GREEN;
//        AT.addDrawable(AT3);
        AT.setPreferredMinMax(0,4,0,12);
        AT.setDefaultCloseOperation(3);
        AT.setVisible(true);
    }

    public void doStep() {
        double DeltaTime = control.getDouble("Delta Time");
        double beta = control.getDouble("beta");

        ////1D Motion no air resistance//////
        particle1.oneStep(DeltaTime);
        circle1.setY(particle1.getY());
        AT1.addPoint(totalTime,particle1.getA());

        /////1D Motion air resistance 1//////
        particle1b.oneStepVelocity(DeltaTime, beta);
        circle1b.setY(particle1b.getY());
        ///HERE HERE
        AT2.addPoint(totalTime,particle1b.getA());

        /////1D Motion air resistance 2//////
        particle1c.oneStepVelocitySquared(DeltaTime, beta);
        circle1c.setY(particle1c.getY());
        AT3.addPoint(totalTime,particle2c.getA());

        /////2D Motion////
        circle2.setY(particle2.getY());
        circle2.setX(particle2.getX());
        trail2.addPoint(circle2.getX(),circle2.getY());
        particle2.twoStep(DeltaTime);

        beta = control.getDouble("beta");

        /////2D Motion Velocity////
        circle2b.setY(particle2b.getY());
        circle2b.setX(particle2b.getX());
        trail2b.addPoint(circle2b.getX(),circle2b.getY());
        particle2b.twoStepVelocity(DeltaTime,beta);
        VT2.addPoint(totalTime,particle2b.getYv());

        ////2D Motion Velocity Squared/////
        circle2c.setY(particle2c.getY());
        circle2c.setX(particle2c.getX());
        trail2c.addPoint(circle2c.getX(),circle2c.getY());
        particle2c.twoStepVelocitySquared(DeltaTime,beta);
//        VT3.addPoint(totalTime,particle2c.getYv());
//
//        circle2b.setY(particle2b.getY());
//        circle2b.setX(particle2b.getX());
//        trail2b.addPoint(circle2b.getX(),circle2b.getY());
//        particle2.twoStepVelocity(DeltaTime, theta, initV,0.5);
//
//        circle2c.setY(particle2c.getY());
//        circle2c.setX(particle2c.getX());
//        trail2c.addPoint(circle2c.getX(),circle2c.getY());
//        particle2.twoStepVelocitySquared(DeltaTime, theta, initV);

//      trailxt.addPoint(totalTime, circle2.getY());
        trailvt.addPoint(totalTime, particle2.getYv());
        /////////
        totalTime=totalTime+0.1;
    }

    @Override
    public void stop(){
        System.out.println(totalTime/10 + " secs to travel " + Math.abs(
                control.getDouble("Starting Y1 position") - circle1.getY())+ " meters.");
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new PApp());
    }
}
