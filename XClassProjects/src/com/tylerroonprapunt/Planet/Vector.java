package XClassProjects.src.com.tylerroonprapunt.Planet;

public class Vector {
    private double magnitude;
    private double theta;
    public Vector (double x, double y) {
        this.setMagnitude(Math.sqrt(Math.pow(x,2) + Math.pow(y,2)));
        if (x != 0){
            if (x<0){
                this.setTheta(simplifyAngle(Math.atan(y/x))+Math.PI);
            }
            else {
                this.setTheta(simplifyAngle(Math.atan(y/x)));
            }
        }else if(y >0) {
            this.setTheta(Math.PI/2);
        }else {
            this.setTheta(-Math.PI/2);
        }
    }
    public Vector (double magnitude, double theta, boolean angle) {
        if (!angle) {
            System.exit(0);
        }
        this.magnitude = magnitude;
        if(theta !=0){
            this.theta = simplifyAngle(theta);
        }
        else {
            this.theta = theta;
        }
    }

    public double getMagnitude() { return magnitude; }
    public void setMagnitude(double magnitude) { this.magnitude = magnitude;}
    public double getTheta() { return theta; }
    public void setTheta(double theta) { this.theta = simplifyAngle(theta); }

    public double getX () {
        return this.getMagnitude()*Math.cos(this.getTheta());
    }
    public double getY () {
        return this.getMagnitude()*Math.sin(this.getTheta());
    }
    public Vector plus (Vector vector1) {
        return new Vector (vector1.getX() + this.getX(), vector1.getY() + this.getY());
    }
    public Vector clone () {
        return new Vector(this.getMagnitude(), this.getTheta(),true);
    }
    public static double simplifyAngle(double angle){
        double sum = 0;
        if (angle > 0){
            while (sum < angle) {
                sum += 2*Math.PI;
            }
            sum = sum - 2*Math.PI;
            return angle - sum;
        }
        else {
            while (Math.abs(sum) < Math.abs(angle)) {
                sum -= 2*Math.PI;
            }
            sum = sum + 2*Math.PI;
            return angle + sum;
        }
    }

    @Override
    public String toString() {
        String info = "";
        info = info + "Mag: " + this.getMagnitude() + "\n";
        info = info + "The: " + this.getTheta() + "\n";
        info = info + "X: " + this.getX() + "\n";
        info = info + "Y: " + this.getY() + "\n";
        return info;
    }
}
