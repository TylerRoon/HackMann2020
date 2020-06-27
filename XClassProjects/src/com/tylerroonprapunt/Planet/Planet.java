package XClassProjects.src.com.tylerroonprapunt.Planet;
import org.opensourcephysics.display.*;



public class Planet {
    private double mass;
    private double x;
    private double y;
    public Vector velo;
    private Vector appliedFg;
    private Circle circle;

    public Planet (double mass, double x, double y, Vector v, Circle circle) {
        this.mass = mass;
        this.x = x;
        this.y = y;
        this.velo = v;
        this.circle = circle;
    }

    public double getMass() { return mass; }
    public void setMass(double mass) { this.mass = mass; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
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


}
