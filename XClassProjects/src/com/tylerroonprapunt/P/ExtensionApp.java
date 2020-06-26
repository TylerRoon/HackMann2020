package com.tylerroonprapunt.P;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import javax.swing.*;
import java.awt.*;

public class ExtensionApp extends AbstractSimulation {
    PlotFrame frame = new PlotFrame("x","y", "");
    ////draw a truck
    Circle wheel1 = new Circle();
    P particle = new P(5,0,0,0,0,0,0,0);
    Circle wheel2 = new Circle();
    DrawableShape rect = DrawableShape.createRectangle(5, 0.3, 10, 0.6);
    DrawableShape rect2 = DrawableShape.createRectangle(9, 1, 2, 2);
    DrawableShape rect3 = DrawableShape.createRectangle(10.5, 0.6, 1, 1.2);
    DrawableShape rect4 = DrawableShape.createRectangle(0.25,0.75,0.5,1.5);
    DrawableShape rect5 = DrawableShape.createRectangle(1.6,1.6,3.2,0.3);
    DrawableShape trampoline = DrawableShape.createRectangle(4.5, 0.45, 4, 0.3);

    Circle ball = new Circle();
    P particle2 = new P(5,3.3,1.75,0,0,0,0,0);

    Trail trailball = new Trail();
    Trail trailcar = new Trail();

    @Override
    public void reset() {
        control.setValue("Car Velocity", 3);
    }

    @Override
    public void initialize() {
        wheel1.pixRadius = 7;
        wheel2.pixRadius = 7;
        wheel1.setX(particle.getX());
        wheel2.setX(particle.getX()+10);
        frame.addDrawable(wheel1);
        frame.addDrawable(wheel2);
        rect.setMarkerColor(Color.BLACK,Color.BLACK);
        frame.addDrawable(rect);
        rect2.setMarkerColor(Color.BLACK,Color.BLACK);
        frame.addDrawable(rect2);
        rect3.setMarkerColor(Color.BLACK,Color.BLACK);
        frame.addDrawable(rect3);
        rect4.setMarkerColor(Color.BLACK,Color.BLACK);
        frame.addDrawable(rect4);
        rect5.setMarkerColor(Color.BLACK,Color.BLACK);
        frame.addDrawable(rect5);
        trampoline.setMarkerColor(Color.YELLOW,Color.YELLOW);
        frame.addDrawable(trampoline);

        trailball.color = Color.BLUE;
        frame.addDrawable(trailball);
        trailcar.color = Color.BLACK;
        frame.addDrawable(trailcar);

        ball.color = Color.BLUE;
        ball.pixRadius = 4;
        ball.setXY(particle2.getX(),particle2.getY());
        frame.addDrawable(ball);

        frame.setPreferredMinMax(-5,100,-1,10);
        frame.setVisible(true);
    }
    public void doStep() {
        trailcar.addPoint(particle.getX(),0);
        trailball.addPoint(particle2.getX(),particle2.getY());
        double velocity = control.getDouble("Car Velocity");

        particle.forward(0.1, velocity);
        wheel1.setX(particle.getX());
        wheel2.setX(particle.getX()+10);
        rect.setX(particle.getX()+5);
        rect2.setX(particle.getX()+9);
        rect3.setX(particle.getX()+10.5);
        rect4.setX(particle.getX()+0.25);
        rect5.setX(particle.getX()+1.6);
        trampoline.setX(particle.getX()+4.5);

        if (particle2.getX() < 10) {
            particle2.forward(0.1,velocity);
            ball.setX(particle2.getX());
        }
        else {
            rect5.setMarkerColor(Color.WHITE,Color.WHITE);
            particle2.setXv(velocity);
            particle2.bounce(0.1,0,0,particle2.getMass(),1.75);
            ball.setX(particle2.getX());
            ball.setY(particle2.getY());
        }

    }
    @Override
    public void stop(){ }
    public static void main(String[] args) {
        SimulationControl.createApp(new ExtensionApp());
    }
}
