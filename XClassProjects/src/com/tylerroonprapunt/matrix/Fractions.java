package com.tylerroonprapunt.matrix;

public class Fractions {
    int numerator;
    int denominator;

    public Fractions () {
        numerator = 0;
        denominator = 0;
    }
    public Fractions (int num, int den) {
        numerator = num;
        denominator = den;
    }
    public int getNumerator() { return numerator; }
    public void setNumerator(int num) { numerator = num; }
    public int getDenominator() { return denominator; }
    public void setDenominator(int den) { denominator = den; }

    public Fractions add (Fractions a, Fractions b) {
        Fractions sum = new Fractions();
        if ((denominator == 0)||(b.denominator == 0)) {
            System.out.println("denominator is 0");
            return null;
        }
        else if (numerator == 0) { sum = b; }
//        else if (b.numerator == 0) { sum = }

        return sum;
    }

    private int lcd (int den1, int den2) {
        int factor1 = den1;
        while ((den1 % den2) != 0) {
            den1 += factor1;
        }
        return den1;
    }

    private int gcd (int den1, int den2) {
        int factor = den2;
        while (den2 != 0) {
            factor = den2;
            den2 = den1 % den2;
            den1 = factor;
        }
        return den1;
    }

    //reduce fraction by least common denominator
    private Fractions convert(int LCD) {
        Fractions result = new Fractions();
        int factor = LCD / denominator;
        result.numerator = numerator * factor;
        result.denominator = LCD;
        return result;
    }

    public static void main(String[] args) {
        Fractions test = new Fractions(2, 4);
        System.out.println(test.lcd(2, 4));
    }

}
