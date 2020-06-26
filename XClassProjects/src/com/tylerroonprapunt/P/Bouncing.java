package com.tylerroonprapunt.P;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

public class Bouncing extends AbstractSimulation {
    PlotFrame frame = new PlotFrame("x","y","Bouncing Ball");
    Trail bouncetrail = new Trail();
    Circle ball = new Circle();
    P particle = new P(1,0,0,0,0,0,0,0);

    @Override
    public void reset() {
        control.setValue("Time Step", 0.1);
        control.setValue("Initial Velocity", 50);
        control.setValue("theta", 45);
    }
    @Override
    public void initialize() {
        double initV = control.getDouble("Initial Velocity");
        double theta = control.getDouble("theta");
        double initXV = initV * Math.cos(Math.toRadians(theta));
        double initYV = initV * Math.sin(Math.toRadians(theta));

        particle.setXv(initXV);
        particle.setYv(initYV);

        ball.setX(particle.getX());
        ball.setY(particle.getY());

        frame.addDrawable(bouncetrail);
        frame.addDrawable(ball);
        frame.setPreferredMinMax(0,500,0,100);
        frame.setVisible(true);
    }

    public void doStep(){
        ////Bouncing Ball
        ball.setY(particle.getY());
        ball.setX(particle.getX());
        bouncetrail.addPoint(ball.getX(),ball.getY());
//        particle.bounce(control.getDouble("Time Step"),30,60, 5,100);
        particle.setXv(10);
        particle.friction2(0.1,particle.getMass());
    }
    @Override
    public void stop(){

    }

    public static void main(String[] args) {
        SimulationControl.createApp(new Bouncing());
    }
}
