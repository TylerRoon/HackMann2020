package com.tylerroonprapunt.projectile;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

public class Particle {
    private double mass;
    private double x;
    private double y;
    private double xvelocity;
    private double yvelocity;
    private double time = 0;

    //constructor
    public Particle() {
        mass = 10;
        x = 0;
        y = 100;
        xvelocity = 0;
        yvelocity = 0;
    }

    public Particle(double mass, double x, double y, double xvelocity, double yvelocity, double g, double beta) {
        this.mass = mass;
        this.x = x;
        this.y = y;
        this.xvelocity = xvelocity;
        this.yvelocity = yvelocity;
        this.g = g;
        this.beta = beta;
        this.acceleration = acceleration;
    }
    //    PlotFrame plotFrame = new PlotFrame("x", "meters from ground", "Moving Ball");

    public double getMass() { return mass; }
    public void setMass(double mass) { this.mass = mass; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getXvelocity() { return xvelocity; }
    public void setXvelocity(double xvelocity) { this.xvelocity = xvelocity; }
    public double getYvelocity() { return yvelocity; }

    public void setYvelocity(double yvelocity) { this.yvelocity = yvelocity; }

    double g = -9.81;
    double beta = 0.1;

    //step
    public void step(double deltaTime){
        double a = g;
        y = y + yvelocity*deltaTime + 0.5 * a * deltaTime*deltaTime;
        x = x + xvelocity*deltaTime;
        yvelocity =  yvelocity + a*deltaTime;
        time = time + deltaTime;
    }

    //step2D
    public void twoStep(double deltaTime, double theta, double initV) {
        y = y + (-initV*Math.sin(theta)*deltaTime) + 0.5*g*deltaTime*deltaTime;
        x = x + initV * Math.cos(theta)*deltaTime;
        yvelocity = initV*Math.sin(theta) + g*deltaTime;
    }

    double acceleration = ((mass * g) - (0.1 * this.yvelocity)) / mass;



}
