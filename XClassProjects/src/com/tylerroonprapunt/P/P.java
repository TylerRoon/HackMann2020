package com.tylerroonprapunt.P;

public class P {
    private double mass;
    private double x;
    private double y;
    private double xv;
    private double yv;
    private double theta;
    private double initV;
    private double a;

    public P(double mass, double x, double y, double xv, double yv, double theta, double initV, double a) {
        this.mass = mass;
        this.x = x;
        this.y = y;
        this.xv = xv;
        this.yv = yv;
        this.theta = theta;
        this.initV = initV;
        this.a = a;
    }

    public double getMass() { return mass; }
    public void setMass(double mass) { this.mass = mass; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getXv() { return xv; }
    public void setXv(double xv) { this.xv = xv; }
    public double getYv() { return yv; }
    public void setYv(double yv) { this.yv = yv; }
    public double getTheta() { return theta; }
    public void setTheta(double theta) { this.theta = theta; }
    public double getInitV() { return initV; }
    public void setInitV(double initV) { this.initV = initV; }
    public double getA() { return a; }
    public void setA(double a) { this.a = a; }

    double gravity = 9.81;
    public void bounce (double timestep, double initV, double theta, double mass, double height) {
        gravity = 9.81;
        a = -gravity;
        y = y + (yv * timestep) + (0.5 * a * timestep * timestep);
        yv = yv + (a * timestep);
        x = x + (xv * timestep);

        if (y < 0.75) {
            y = 0.75;
            ////Golf Ball Coefficient of Restitution = 0.8
            double earth = 5.972 * Math.pow(10,24);
            double CR = 1;
            yv = -yv * CR;
        }
    }
    public void golf (double timestep, double Beta) {
        double CR = 0.231; ///Golf Ball Coefficient fo Restitution
        gravity = -9.81;

        y = y + (yv * timestep) + (0.5 * a * timestep * timestep);
        a = (((mass * gravity) - (Beta * yv))/mass);
        yv = yv + a*(timestep);

        x = x + (xv * timestep) + (0.5 * ax * timestep * timestep);
        ax = (-Beta * xv)/mass;
        xv = xv + ax*(timestep);

        if (y < 0) {
            y = 0;
            yv = -yv * CR;
        }
    }
    public void friction (double timestep, double mass) {
        double frictioncoefficient = 0.3;
        double gravity = 9.81;
        x = x + (xv * timestep) + (0.5 * a * timestep * timestep);
        a = -(frictioncoefficient*mass*gravity) / mass;
        xv = xv + (timestep*a);
    }
    public void friction2 (double timestep, double mass){
        double frictioncoefficient = 0.3;
        x = x + (xv * timestep) + (0.5 * a * timestep * timestep);
        a = (frictioncoefficient*mass*gravity) / mass;
        xv = xv + (timestep*a);
    }
    public void friction3 (double timestep, double mass){
        double frictioncoefficient = 0.3;
        x = x + (xv * timestep) + (0.5 * a * timestep * timestep);
        a = -(frictioncoefficient*mass*gravity) / mass;
        xv = xv + (timestep*a);
    }

    public void oneStep (double timestep) {
        a = gravity;
        y = y + (-yv * timestep) - (0.5 * a * timestep * timestep);
        yv = yv + (a * timestep);
    }
    public void oneStepVelocity (double timestep, double Beta) {
        a = gravity - ((Beta * yv)/mass);
        y = y + (-yv * timestep) - (0.5 * a * timestep * timestep);
        yv = yv + (a * timestep);
    }
    public void oneStepVelocitySquared (double timestep, double Beta) {
        a = gravity - ((Beta * yv * Math.abs(yv))/mass);
        y = y + (-yv * timestep) - (0.5 * a * timestep * timestep);
        yv = yv + (a * timestep);
    }
    public void twoStep (double timestep) {
        gravity = -9.81;
        a = gravity;
        y = y + (yv * timestep) + (0.5 * a * timestep * timestep);
        x = x + (xv * (timestep));
        yv = yv + a*timestep;
    }
    double ax = 0;
    public void twoStepVelocity (double timestep, double Beta) {
        gravity = -9.81;

        y = y + (yv * timestep) + (0.5 * a * timestep * timestep);
        a = (((mass * gravity) - (Beta * yv))/mass);
        yv = yv + a*(timestep);

        x = x + (xv * timestep) + (0.5 * ax * timestep * timestep);
        ax = (-Beta * xv)/mass;
        xv = xv + ax*(timestep);

    }
    public void twoStepVelocitySquared (double timestep, double Beta) {
        gravity = -9.81;

        y = y + (yv * timestep) + (0.5 * a * timestep * timestep);
        a = (((mass * gravity) - (Beta * yv*Math.abs(yv)))/mass);
        yv = yv + a*(timestep);

        x = x + (xv * timestep) + (0.5 * ax * timestep * timestep);
        ax = (-Beta * xv * Math.abs(xv))/mass;
        xv = xv + ax*(timestep);
    }
    public void forward (double timestep, double xv) {
        x = x + (xv * timestep);
    }
}
