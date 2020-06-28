package XClassProjects.src.com.tylerroonprapunt.Planet;
import org.opensourcephysics.display.*;

import java.awt.*;
import java.util.ArrayList;

public class Planet {
    private double mass;
    private double x;
    private double y;
    private double rad;
    public Vector velo;
    private Vector appliedFg;
    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    private Circle circle;

    public Planet (double mass, double x, double y, Vector v, Circle circle, double rad) {
        this.mass = mass;
        this.x = x;
        this.y = y;
        this.velo = v;
        this.circle = circle;
        this.rad = rad;
    }

    public double getMass() { return mass; }
    public void setMass(double mass) { this.mass = mass; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getRad() {return rad;}
    public void setRad(double rad) {this.rad = rad;}
    public void setXY(double x, double y){
        this.x = x;
        this.y = y;
    }
    public void visSetXY(double x, double y){
        this.setXY(x,y);
        circle.setXY(x,y);
    }
    public Vector getVelo() { return velo; }
    public void setVelo(Vector velo) { this.velo = velo.clone(); }
    public Vector getAppliedFg() { return appliedFg; }
    public void setAppliedFg(Vector appliedFg) { this.appliedFg = appliedFg.clone(); }

    @Override
    public String toString() {
        String info = "";
        info = info + "x: " + this.getX() + "\n";
        info = info + "y: " + this.getY() + "\n";
        info = info + "m: " + this.getMass() + "\n";
        info = info + "Velo:";
        info = info + this.getVelo().toString();
        info = info + "Fg: ";
        info = info + this.getAppliedFg().toString() + "\n";
        return info;
    }
}
