package com.tylerroonprapunt;

public class Quadratic {
    private double a;
    private double b;
    private double c;

    public Quadratic(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public boolean hasRealRoots() {
        if ((b*b)-(4*a*c) >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int numberOfRoots() {
        if ((b*b)-(4*a*c) > 0) {
            return 2;
        }
        else if ((b*b)-(4*a*c) == 0){
            return 1;
        }
        else {
            return 0;
        }
    }

    public double[] getRootArray() {
        if ((b*b)-(4*a*c) > 0) {
            //2 roots
            double root1 = (-b+Math.sqrt(b*b-4*a*c))/(2*a);
            double root2 = (-b-Math.sqrt(b*b-4*a*c))/(2*a);
            double roots[] = new double[2];
            roots[0] = root1;
            roots[1] = root2;
            return roots;
        }
        else if ((b*b)-(4*a*c) == 0){
            double root1 = (-b+Math.sqrt(b*b-4*a*c))/(2*a);
            double root2 = (-b+Math.sqrt(b*b-4*a*c))/(2*a);
            double roots[] = new double[2];
            roots[0] = root1;
            roots[1] = root2;
            return roots;
        }
        else {
            double roots[] = new double[0];
            return roots;
        }
    }
    public double getAxisOfSymmetry() {
        double axis = -b/(2*a);
        return axis;
    }
    public double getExtremeValue(){
        double axis = -b/(2*a);
        double extreme = a*Math.pow(axis,2) + (b*axis) + c;
        return extreme;
    }
    public boolean isMax() {
        boolean isMax = true;
        if(a>0){
            isMax = false;
            return isMax;
        }
        else{
            isMax = true;
            return isMax;
        }
    }
    public double evaluateWith(double x){
        double evaluation = a*Math.pow(x,2) + b*x+c;
        return evaluation;
    }
}
