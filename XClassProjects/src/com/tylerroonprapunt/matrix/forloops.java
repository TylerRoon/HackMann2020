package com.tylerroonprapunt.matrix;

public class forloops {
    public static void main(String[] args) {
        int x = 1;
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < x; j++) {
                System.out.println(i + ", " + j);
            }
            x++;
        }
    }
}
