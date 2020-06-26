import com.tylerroonprapunt.P.P;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

public class Golf extends AbstractSimulation {
    PlotFrame frame = new PlotFrame("x","y", "");
    Circle golfball = new Circle();
    P particle = new P(4.593,0,0,0,0,0,0,0);
    Circle golfball2 = new Circle();
    P particle2 = new P(4.593,0,0,0,0,0,0,0);
    Circle golfball3 = new Circle();
    P particle3 = new P(4.593,0,0,0,0,0,0,0);
    Circle golfball4 = new Circle();
    P particle4 = new P(4.593,0,0,0,0,0,0,0);
    Trail trail = new Trail();
    Trail trail2 = new Trail();
    Trail trail3 = new Trail();
    Trail trail4 = new Trail();
    DrawableShape grass = DrawableShape.createRectangle(275, -0.5, 550, 1);
    DrawableShape pole = DrawableShape.createRectangle(400,4,1,8);
    DrawableShape flag = DrawableShape.createRectangle(415,6.75, 30,2.5);
    DrawableShape lake = DrawableShape.createRectangle(350,-0.125,60,0.25);

    boolean hitlake;
    boolean friction;
    boolean hitonce;
    boolean stroke1;
    boolean stroke2;
    boolean stroke3;
    boolean stroke4;
    boolean delay;
    boolean delay2;
    boolean delay3;
    boolean delay4;


    double restitution = 0.231;
    double initV2 = 0;
    double initV3 = 0;
    int timer = 0;

    @Override
    public void reset() {
        control.setValue("Time Step", 0.05);
        control.setValue("Club 1", "driver");
        control.setValue("Club 2", "4 iron");
        control.setValue("Club 3", "driver");
        control.setValue("Theta 1", 11);
        control.setValue("Theta 2", 8);
        control.setValue("Theta 3", 11);
        control.setValue("Putt Velocity", 20);
//
//        control.setValue("Time Step", 0.05);
//        control.setValue("Club 1", "choose club");
//        control.setValue("Club 2", "choose club");
//        control.setValue("Club 3", "choose club");
//        control.setValue("Theta 1", "choose theta");
//        control.setValue("Theta 2", "choose theta");
//        control.setValue("Theta 3", "choose theta");
//        control.setValue("Putt Velocity", "choose velocity");

        control.println("There are 5 different kinds of clubs:");
        control.println("Driver: 68 m/s");
        control.println("5 wood: 62 m/s");
        control.println("Hybrid: 60 m/s");
        control.println("4 iron: 55 m/s");
        control.println("7 iron: 48 m/s");
        control.println("");
        control.println("For reference, professional golfers hit the ball at around 10 degrees");

        friction = false;
        hitonce = false;
        stroke1 = true;
        stroke2 = false;
        stroke3 = false;
        stroke4 = false;
        delay = false;
        delay2 = false;
        hitlake = false;
        delay3 = false;
        delay4 = false;
    }

    @Override
    public void initialize() {
        String club1 = control.getString("Club 1").toLowerCase();
        double initV = 0;
        double theta = control.getDouble("Theta 1");
        //////////////////////////
        if (club1.equals("driver")) { initV = 68; }
        if (club1.equals("5 Wood") || club1.equals("5 wood")) { initV = 62; }
        if (club1.equals("Hybrid")) { initV = 60; }
        if (club1.equals("4 Iron") || club1.equals("4 iron")) { initV = 55; }
        if (club1.equals("7 Iron") || club1.equals("7 iron")) { initV = 48; }
        double xv = initV * Math.cos(Math.toRadians(theta));
        double yv = initV * Math.sin(Math.toRadians(theta));
        particle.setXv(xv);
        particle.setYv(yv);
        golfball.setX(particle.getX());
        golfball.setY(particle.getY());
        //////////////////////////
        String club2 = control.getString("Club 2").toLowerCase();
        if (club2.equals("driver")) { initV2 = 68; }
        if (club2.equals("5 Wood") || club2.equals("5 wood")) { initV2 = 62; }
        if (club2.equals("hybrid")) { initV2 = 60; }
        if (club2.equals("4 Iron") || club2.equals("4 iron")) { initV2 = 55; }
        if (club2.equals("7 Iron") || club2.equals("7 iron")) { initV2 = 48; }
        //////////////////////////
        String club3 = control.getString("Club 3").toLowerCase();
        if (club3.equals("driver")) { initV3 = 68; }
        if (club3.equals("5 Wood") || club3.equals("5 wood")) { initV3 = 62; }
        if (club3.equals("hybrid")) { initV3 = 60; }
        if (club3.equals("4 Iron") || club3.equals("4 iron")) { initV3 = 55; }
        if (club3.equals("7 Iron") || club3.equals("7 iron")) { initV3 = 48; }
        //////////////////////////
        golfball.color = Color.gray;
        golfball2.color = Color.gray;
        golfball3.color = Color.gray;
        golfball4.color = Color.gray;
        grass.setMarkerColor(Color.GREEN,Color.GREEN);
        pole.setMarkerColor(Color.gray,Color.gray);
        flag.setMarkerColor(Color.RED,Color.RED);
        lake.setMarkerColor(Color.BLUE, Color.BLUE);
        trail2.color = Color.RED;
        trail3.color = Color.ORANGE;
        trail4.color = Color.MAGENTA;

        frame.addDrawable(golfball);
        frame.addDrawable(pole);
        frame.addDrawable(grass);
        frame.addDrawable(flag);
        frame.addDrawable(lake);
        frame.addDrawable(trail);
        frame.addDrawable(trail2);
        frame.addDrawable(trail3);
        frame.addDrawable(trail4);

        frame.setPreferredMinMax(-2,600,-10,30);
    }
    public void doStep() {
        double timestep = control.getDouble("Time Step");
        golfball.setX(particle.getX());
        golfball.setY(particle.getY());
        trail.addPoint(particle.getX(), particle.getY());
        /////hit lake
        if ((particle.getX() > 320)&&(particle.getX()<380)&&(particle.getY()<=0)){
            System.out.println("You hit the lake!");
            hitlake = true;
            stroke1 = false;
            stroke2 = false;
            stroke3 = false;
            stroke4 = false;
            delay = true;
        }
        if ((particle2.getX() > 320)&&(particle2.getX()<380)&&(particle2.getY()<=0)){
            System.out.println("You hit the lake!");
            hitlake = true;
            stroke1 = false;
            stroke2 = false;
            stroke3 = false;
            stroke4 = false;
            delay = true;
        }
        if ((particle3.getX() > 320)&&(particle3.getX()<380)&&(particle3.getY()<=0)){
            System.out.println("You hit the lake!");
            hitlake = true;
            stroke1 = false;
            stroke2 = false;
            stroke3 = false;
            stroke4 = false;
            delay = true;
        }
        if ((particle4.getX() > 320)&&(particle4.getX()<380)&&(particle4.getY()<=0)){
            System.out.println("You hit the lake!");
            hitlake = true;
            stroke1 = false;
            stroke2 = false;
            stroke3 = false;
            stroke4 = false;
            delay = true;
        }
        if (stroke1 && !friction) {
            if (!friction) { particle.golf(timestep, 0.5); }
            int test = 0;
//            System.out.println(particle.getY());
            if (particle.getY() < 0) {
                double holder = -particle.getY();
                test = (int) (holder * 100);
            }
            if (particle.getY() > 0) { test = (int) (particle.getY() * 100); }
            if ((test == 0) && hitonce) {
                particle.setXv(particle.getXv() * restitution * restitution);

                friction = true;
                hitonce = false;
            }
            if (test == 0) { hitonce = true; }
            if (test != 0) { hitonce = false; }
        }
        if (stroke1 && friction) {
            particle.friction(timestep, particle.getMass());
            if (particle.getXv() < 0) {

                particle2.setX(particle.getX());
                particle2.setY(0);

                double theta2 = control.getDouble("Theta 2");
                double xv2 = initV2 * Math.cos(Math.toRadians(theta2));
                double yv2 = initV2 * Math.sin(Math.toRadians(theta2));
                particle2.setXv(xv2);
                particle2.setYv(yv2);

                friction = false;
                stroke1 = false;
                hitonce = false;
                delay = true;
            }
        }
        if (delay) { timer++; }
        if ((timer >30)&&delay) {
            timer = 0;
            stroke2 = true;
            delay = false;
            if (hitlake) {
                System.exit(0);
            }
        }

        /////
        if (stroke2 && !friction) {
            golfball2.setXY(particle2.getX(),particle2.getY());
            frame.addDrawable(golfball2);
            trail2.addPoint(particle2.getX(),particle2.getY());
            particle2.golf(timestep/2, 0.5);
            if (particle2.getY() == 0.0 && hitonce){
                particle2.setXv(particle2.getXv() * restitution * restitution);
                friction = true;
            }
            if (particle2.getY() == 0.0) { hitonce = true; }
            if (particle2.getY() != 0.0) { hitonce = false; }
        }
        if (friction && stroke2) {
            particle2.friction(timestep, particle2.getMass());
            if (particle2.getXv() < 0) {
                particle3.setX(particle2.getX());
                particle3.setY(0);

                double theta3 = control.getDouble("Theta 3");
                double xv3 = initV3 * Math.cos(Math.toRadians(theta3));
                double yv3 = initV3 * Math.sin(Math.toRadians(theta3));
                particle3.setXv(xv3);
                particle3.setYv(yv3);

                friction = false;
                stroke2 = false;
                hitonce = false;
                delay2 = true;
            }
        }
        if (delay2) { timer++; }
        if ((timer >30)&&delay2) {
            timer = 0;
            stroke3 = true;
            delay2 = false;
            hitonce = false;
        }
        //////
        if (stroke3 && !friction) {
            golfball3.setXY(particle3.getX(),particle3.getY());
            frame.addDrawable(golfball3);
            trail3.addPoint(particle3.getX(),particle3.getY());
            particle3.golf(timestep, 0.5);
            if (particle3.getY() == 0.0 && hitonce){
                particle3.setXv(particle3.getXv() * restitution * restitution);
                friction = true;
            }
            if (particle3.getY() == 0.0) { hitonce = true; }
            if (particle3.getY() != 0.0) { hitonce = false; }
        }
        if (friction && stroke3) {
            golfball3.setXY(particle3.getX(),particle3.getY());
            trail3.addPoint(particle3.getX(),particle3.getY());
            particle3.friction(timestep, particle3.getMass());
            if (particle3.getXv() < 0) {
                friction = false;
                stroke3 = false;
                delay3 = true;
            }
        }
        if (delay3) { timer++; }
        if ((timer >0)&&delay3) {
            double initV4 = control.getDouble("Putt Velocity");
            double xv4 = initV4 * Math.cos(Math.toRadians(0));
            particle4.setX(particle3.getX());
            particle4.setY(0);
            particle4.setYv(0);
            if (particle4.getX()>400) {
                particle4.setXv(-xv4);
            }
            else if (particle4.getX()<400){
                System.out.println("hi");
                particle4.setXv(xv4);
            }
            timer = 0;
            frame.addDrawable(golfball4);
            frame.addDrawable(trail4);
            delay3 = false;
            stroke4 = true;
        }
        if (stroke4) {
            golfball4.setXY(particle4.getX(),particle4.getY());
            trail4.addPoint(particle4.getX(),particle4.getY());

            if (particle4.getX()>400) {
                particle4.friction2(timestep, particle4.getMass());

                if (particle4.getXv() > 0) {
                    delay4 = true;
                    stroke4 = false;
                }
            }
            else{
                particle4.friction3(timestep, particle4.getMass());
                if (particle4.getXv() < 0) {
                    delay4 = true;
                    stroke4 = false;
                }
            }
        }
        if (delay4) { timer++; }
    }
    @Override
    public void stop(){ }
    public static void main(String[] args) { SimulationControl.createApp(new Golf()); }

}
