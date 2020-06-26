package XClassProjects.src.com.tylerroonprapunt.Planet;

public class Planet {
    private double mass;
    private double x;
    private double y;
    private Vector v;
    private Vector appliedFg;

    public Planet (double mass, double x, double y, Vector v) {
        this.mass = mass;
        this.x = x;
        this.y = y;
        this.v = v;
    }

    public double getMass() { return mass; }
    public void setMass(double mass) { this.mass = mass; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public Vector getV() { return v; }
    public void setV(Vector v) { this.v = v.clone(); }
    public Vector getAppliedFg() { return appliedFg; }
    public void setAppliedFg(Vector appliedFg) { this.appliedFg = appliedFg.clone(); }


}
