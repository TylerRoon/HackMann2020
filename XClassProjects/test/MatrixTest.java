import com.tylerroonprapunt.matrix.Matrix;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import java.util.Scanner;

public class MatrixTest {
    public static void main(String[] args) {
        boolean toPlay = true;
        while (toPlay) {
            loop:
            {
                System.out.println("There are 4 different programs: Differentiation (1), Area of a Triangle (2), Fibonacci Numbers (3), and Pythagorean Triples (4)");
                System.out.println("Type a number 1-4");
                Scanner que = new Scanner(System.in);
                int choice = que.nextInt();
                if (choice == 1) {
                    System.out.println("Build a Polynomial");
                    System.out.println("Degree?");
                    Scanner scan = new Scanner(System.in);
                    int degree = Integer.parseInt(scan.nextLine());
                    Matrix coefficients = new Matrix(degree + 1, 1);
                    for (int i = 0; i < degree + 1; i++) {
                        System.out.println("Enter Coefficient for x^" + (degree - i));
                        Scanner scan3 = new Scanner(System.in);
                        int holder = Integer.parseInt(scan3.nextLine());
                        coefficients.setEntry(i, 0, holder);
                    }
                    System.out.print("This is your polynomial: ");
                    String thepolynomial = "";
                    for (int i = 0; i < degree + 1; i++) {
                        double holder = coefficients.getEntry​(i, 0);
                        if (i < degree) {
                            System.out.print(holder + "x^" + (degree - i) + " + ");
                            thepolynomial = thepolynomial + holder + "x^" + (degree - i) + " + ";
                        } else {
                            System.out.println(holder);
                            thepolynomial = thepolynomial + holder;
                        }
                    }
                    coefficients = coefficients.flip();
//        coefficients.toString(coefficients);
//
//        System.out.println("");
                    System.out.println("Derivative at what x?");
                    Scanner scan2 = new Scanner(System.in);
                    int a = Integer.parseInt(scan2.next());
                    System.out.println("");
                    System.out.println("Degree: " + degree + ", Derivative Location: " + a);
                    System.out.println("This is the matrix you get when you compare coefficients:");

                    Matrix leftMatrix = new Matrix(degree + 1, degree + 1);
                    leftMatrix = leftMatrix.aForm(degree, a);
                    System.out.println(leftMatrix);
                    System.out.println("");
                    Matrix inverse = leftMatrix.inverse();
                    System.out.println("Invert the above matrix to get:");
                    inverse.print();
                    System.out.println("Multiply the inverted matrix by the coefficient matrix. You get:");
                    Matrix multiplied = inverse.times(coefficients);
                    multiplied.print();
                    double answer = multiplied.getEntry​(1, 0);
                    System.out.println("Answer:");
                    System.out.println("f(x) = " + thepolynomial);
                    System.out.println("f'(" + a + ") = " + answer);
                    System.out.println("");
                }
                if (choice == 2) {
                    System.out.println("We can find the area of a triangle given three points");
                    System.out.println("Call the three points A (a, b), B (c, d), and C (e, f)");
                    System.out.println("Enter data as a,b,c,d,e,f (no spaces)");
                    Scanner data = new Scanner(System.in);
                    String entry = data.nextLine();
                    String[] entries = entry.split(",");
                    int[] n = new int[6];
                    for (int i = 0; i < entries.length; i++) {
                        int holder = Integer.parseInt(entries[i]);
                        n[i] = holder;
                    }
                    int a = n[0];
                    int b = n[1];
                    int c = n[2];
                    int d = n[3];
                    int e = n[4];
                    int f = n[5];

                    double area = AreaTriangle(a,b,c,d,e,f);
                    System.out.println("Area of Triangle: " + area);
                    System.out.println(" ");
                }
                if (choice == 3) {
                    System.out.println("We can find the nth fibonacci number using matrices");
                    Scanner input = new Scanner(System.in);
                    int n = input.nextInt();
                    double myfibonacci = fibonnaci(n);
                    System.out.println("The " + n + " fibonacci number is: " + myfibonacci);
                    System.out.println("");
                }
                if (choice == 4) {
                    System.out.println("Given a primitive pythagorean triple (e.g. 3, 4, 5), we can find 3 others");
                    System.out.println("Enter a triple and the program will return 3 others");
                    Scanner input = new Scanner(System.in);
                    String entry = input.nextLine();
                    String[] entries = entry.split(",");
                    int[] n = new int[entries.length];
                    for (int i = 0; i < entries.length; i++) {
                        int holder = Integer.parseInt(entries[i]);
                        n[i] = holder;
                    }
                    int a = n[0];
                    int b = n[1];
                    int c = n[2];
                    find3basic(a, b, c);
                    System.out.println("");
                }
                else {
                    break loop;
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////

    @Test
    public void getEntry() {
        Matrix m = new Matrix(3, 3);
        m.setEntry(0, 0, 1);
        m.setEntry(0, 1, 2);
        m.setEntry(1, 0, 3);
        m.setEntry(1, 1, 4);
        m.print();
        assertThat(m.getEntry​(1, 1), is(4.0));
    }

    @Test
    public void plus() {
        Matrix m = new Matrix(2, 2);
        m.setEntry(0, 0, 1);
        m.setEntry(0, 1, 2);
        m.setEntry(1, 0, 3);
        m.setEntry(1, 1, 4);

        Matrix sum = m.plus(m);
        sum.print();
        assertThat(sum.getEntry​(0, 0), is(2.0));
        assertThat(sum.getEntry​(0, 1), is(4.0));
        assertThat(sum.getEntry​(1, 0), is(6.0));
        assertThat(sum.getEntry​(1, 1), is(8.0));
    }

    @Test
    public void times() {
        Matrix m = new Matrix(2, 2);
        m.setEntry(0, 0, 1);
        m.setEntry(0, 1, 2);
        m.setEntry(1, 0, 3);
        m.setEntry(1, 1, 4);

        Matrix z = new Matrix(2, 2);
        z.setEntry(0, 0, 2);
        z.setEntry(0, 1, 3);
        z.setEntry(1, 0, 4);
        z.setEntry(1, 1, 5);

        Matrix product = m.times(z);
        product.print();
        assertThat(product.getEntry​(0, 0), is(10.0));
        assertThat(product.getEntry​(0, 1), is(13.0));
        assertThat(product.getEntry​(1, 0), is(22.0));
        assertThat(product.getEntry​(1, 1), is(29.0));
    }

    @Test
    public void scalar() {
        Matrix m = new Matrix(2, 2);
        m.setEntry(0, 0, 1);
        m.setEntry(0, 1, 2);
        m.setEntry(1, 0, 3);
        m.setEntry(1, 1, 4);
        Matrix scalar = m.scalarTimesRow​(5, 1);
        scalar.print();
        assertThat(scalar.getEntry​(0, 0), is(1.0));
        assertThat(scalar.getEntry​(0, 1), is(2.0));
        assertThat(scalar.getEntry​(1, 0), is(15.0));
        assertThat(scalar.getEntry​(1, 1), is(20.0));
    }

    @Test
    public void linearcombo() {
        Matrix m = new Matrix(3, 3);
        m.setEntry(0, 0, 1);
        m.setEntry(0, 1, 5);
        m.setEntry(0, 2, 3);
        m.setEntry(1, 0, 2);
        m.setEntry(1, 1, 4);
        m.setEntry(1, 2, 5);
        m.setEntry(2, 0, 4);
        m.setEntry(2, 1, 6);
        m.setEntry(2, 2, 8);

        Matrix linearcombo = m.linearComboRows​(-2, 0, 1);
        linearcombo.print();
        assertThat(linearcombo.getEntry​(1,1), is(-6.0));
        assertThat(linearcombo.getEntry​(1,0), is(0.0));

    }

    @Test
    public void switchrow() {
        Matrix m = new Matrix(2, 3);
        m.setEntry(0, 0, 1);
        m.setEntry(0, 1, 2);
        m.setEntry(0, 2, 3);
        m.setEntry(1, 0, 3);
        m.setEntry(1, 1, 4);
        m.setEntry(1, 2, 5);

        Matrix switched = m.switchrows(0, 1);
        switched.print();
        assertThat(switched.getEntry​(0,0), is(3.0));
        assertThat(switched.getEntry​(1,0), is(1.0));
    }

    @Test
    public void zerodown() {
        Matrix m = new Matrix(3, 3);
        m.setEntry(0, 0, 1);
        m.setEntry(0, 1, 5);
        m.setEntry(0, 2, 3);
        m.setEntry(1, 0, 2);
        m.setEntry(1, 1, 4);
        m.setEntry(1, 2, 5);
        m.setEntry(2, 0, 4);
        m.setEntry(2, 1, 6);
        m.setEntry(2, 2, 8);

        Matrix zeroDown = m.zeroDown(0,0);
        zeroDown.print();
        assertThat(zeroDown.getEntry​(0,0), is(1.0));
        assertThat(zeroDown.getEntry​(1,0), is(0.0));
        assertThat(zeroDown.getEntry​(2,0), is(0.0));
        assertThat(zeroDown.getEntry​(2,1), is(-14.0));
    }

    @Test
    public void reducerow() {
        Matrix m = new Matrix(3, 3);
        m.setEntry(0, 0, 0.5);
        m.setEntry(0, 1, 6);
        m.setEntry(0, 2, 3);
        m.setEntry(1, 0, 3);
        m.setEntry(1, 1, 2);
        m.setEntry(1, 2, 8);
        m.setEntry(2, 0, 4);
        m.setEntry(2, 1, 7);
        m.setEntry(2, 2, 1);
        m.print();
        System.out.println("");

        Matrix reduce = m.reducerowechelon(m);
        reduce.print();
        assertThat(reduce.getEntry​(0,0), is(1.0));
        assertThat(reduce.getEntry​(1,1), is(1.0));
        assertThat(reduce.getEntry​(2,2), is(1.0));
        assertThat(reduce.getEntry​(0,2), is(0.0));

    }

    @Test
    public void inverse(){
        int a = 1;
        Matrix m = new Matrix(4, 4);
        m.setEntry(0,0,1);
        m.setEntry(1,1,1);
        m.setEntry(0,2, a * a);
        m.setEntry(1,2,-2 * a);
        m.setEntry(2,2,1);
        m.setEntry(1,3,a * a);
        m.setEntry(2,3,-2 * a);
        m.setEntry(3,3,1);
        m.print();
        System.out.println("");
        Matrix inverse = m.inverse();
        inverse.print();
        assertThat(inverse.getEntry​(0,3), is(-2.0));
        assertThat(inverse.getEntry​(1,3), is(3.0));
        assertThat(inverse.getEntry​(2,2), is(1.0));
        assertThat(inverse.getEntry​(0,2), is(-1.0));
    }

    @Test
    public void aForm(){
        Matrix m = new Matrix(6, 6);
        m = m.aForm(5,3);
        m.print();
        assertThat(m.getEntry​(0,1), is(0.0));
        assertThat(m.getEntry​(1,2), is(-6.0));
        assertThat(m.getEntry​(1,3), is(9.0));
        assertThat(m.getEntry​(4,5), is(-6.0));
    }
    @Test
    public void flip(){
        Matrix m = new Matrix(6, 1);
        m.setEntry(0,0,1);
        m.setEntry(1,0,3);
        m.setEntry(2,0,5);
        m.setEntry(3,0,6);
        m.setEntry(4,0,7);
        m.setEntry(5,0,9);
        m = m.flip();
        m.print();
        assertThat(m.getEntry​(0,0), is(9.0));
        assertThat(m.getEntry​(1,0), is(7.0));
        assertThat(m.getEntry​(2,0), is(6.0));
        assertThat(m.getEntry​(3,0), is(5.0));
        assertThat(m.getEntry​(4,0), is(3.0));
        assertThat(m.getEntry​(5,0), is(1.0));
    }

    @Test
    public void determinant(){
        Matrix m = new Matrix (4,4);
        m.setEntry(0,0,5);
        m.setEntry(0,1,8);
        m.setEntry(0,2,4);
        m.setEntry(0,3,8);
        m.setEntry(1,0,3);
        m.setEntry(1,1,2);
        m.setEntry(1,2,5);
        m.setEntry(1,3,9);
        m.setEntry(2,0,5);
        m.setEntry(2,1,1);
        m.setEntry(2,2,3);
        m.setEntry(2,3,6);
        m.setEntry(3,0,3);
        m.setEntry(3,1,6);
        m.setEntry(3,2,1);
        m.setEntry(3,3,2);
        m.print();
        double determinant = Math.round(m.determinant());
        System.out.println("Determinant (4x4): " + determinant);
        assertThat(determinant, is(-55.0));
    }

    @Test
    public void trianglearea() {
        double area = Math.round(AreaTriangle(8,10,-5, 20,3, 14));
        System.out.println("Area: " + area);
        assertThat(area, is(1.0));
    }

    @Test
    public void raisetoPower() {
        Matrix m = new Matrix(2,2);
        m.setEntry(0,0, 5);
        m.setEntry(0,1, 2);
        m.setEntry(1,0, 3);
        m.setEntry(1,1, 6);
        m.print();
        System.out.println("");
        m = m.toPower(2);
        m.print();
        assertThat(m.getEntry​(0,0), is(31.0));
        assertThat(m.getEntry​(0,1), is(22.0));
        assertThat(m.getEntry​(1,0), is(33.0));
        assertThat(m.getEntry​(1,1), is(42.0));
    }

    @Test
    public void findNthFibonacci() {
        double myfibonacci = fibonnaci(8);
        System.out.println("My Fibonacci: " + myfibonacci);
        assertThat(myfibonacci, is(21.0));
    }

    @Test
    public void primitiveTripples() { find3basic(3, 4, 5); }

    ////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param a - x value for point 1
     * @param b - y value for point 1
     * @param c - x value for point 2
     * @param d - y value for point 2
     * @param e - x value for point 3
     * @param f - y value for point 3
     * @return - area of a triangle
     */
    private static double AreaTriangle (int a, int b, int c, int d, int e, int f) {
        int[] initial = new int[] {a, b, c, d, e, f};
        int[] holder = new int[6];
        for (int i = 0; i < 6; i++) {
            holder[i] = initial[i];
        }
        sort(holder);
        int lowestvalue = holder[0];
        if (lowestvalue == 0) {
            for (int i = 0; i < 6; i++) {
                initial[i] = initial[i] + 2;
            }
        }
        if (lowestvalue < 0) {
            int temp = Math.abs(lowestvalue) + 3;
            for (int i = 0; i < 6; i++) {
                initial[i] = initial[i] + temp;
            }
        }
        Matrix m = new Matrix(3, 3);
        m.setEntry(0,0,initial[0]);
        m.setEntry(0,1,initial[1]);
        m.setEntry(0,2,1);
        m.setEntry(1,0,initial[2]);
        m.setEntry(1,1,initial[3]);
        m.setEntry(1,2,1);
        m.setEntry(2,0,initial[4]);
        m.setEntry(2,1,initial[5]);
        m.setEntry(2,2,1);
        m.toString();
        System.out.println("");
        System.out.println("Rounded Determinant: " + Math.round(m.determinant()));
        double area = 0.5 * (m.determinant());
        if (area < 0) { area = -area; }
        if (area == 0) { System.out.println("Points are colinear, no triangle"); }
        return area;
    }

    /**
     *
     * @param n - the sought for fibonacci number
     * @return - the nth fibonacci number
     */
    private static double fibonnaci (int n) {
        Matrix m = new Matrix (2,2);
        m.setEntry(0,0,1);
        m.setEntry(0,1,1);
        m.setEntry(1,0,1);
        m.setEntry(1,1,0);
        m.print();
        m = m.toPower(n);
        System.out.println("");
        m.print();
        System.out.println("");
        return m.getEntry​(0,1);
    }

    /**
     * The parameters a, b, c, form a pythagorean triple (a^2 + b^2 = c^2)
     * @param a
     * @param b
     * @param c
     */
    //Prints 3 other triples
    private static void find3basic (int a, int b, int c) {
        if ((a*a) + (b*b) == (c*c)) {
            Matrix original = new Matrix(3, 1);
            original.setEntry(0, 0, a);
            original.setEntry(1, 0, b);
            original.setEntry(2, 0, c);

            Matrix A = new Matrix(3, 3);
            A.setEntry(0, 0, 1);
            A.setEntry(0, 1, -2);
            A.setEntry(0, 2, 2);
            A.setEntry(1, 0, 2);
            A.setEntry(1, 1, -1);
            A.setEntry(1, 2, 2);
            A.setEntry(2, 0, 2);
            A.setEntry(2, 1, -2);
            A.setEntry(2, 2, 3);
            A.print();
            System.out.println("");

            Matrix B = new Matrix(3, 3);
            B.setEntry(0, 0, 1);
            B.setEntry(0, 1, 2);
            B.setEntry(0, 2, 2);
            B.setEntry(1, 0, 2);
            B.setEntry(1, 1, 1);
            B.setEntry(1, 2, 2);
            B.setEntry(2, 0, 2);
            B.setEntry(2, 1, 2);
            B.setEntry(2, 2, 3);
            B.print();
            System.out.println("");

            Matrix C = new Matrix(3, 3);
            C.setEntry(0, 0, -1);
            C.setEntry(0, 1, 2);
            C.setEntry(0, 2, 2);
            C.setEntry(1, 0, -2);
            C.setEntry(1, 1, 1);
            C.setEntry(1, 2, 2);
            C.setEntry(2, 0, -2);
            C.setEntry(2, 1, 2);
            C.setEntry(2, 2, 3);
            C.print();
            System.out.println("");

            Matrix number1 = A.times(original);
            System.out.print("(");
            for (int i = 0; i < 3; i++) {
                System.out.print(number1.getEntry​(i, 0));
                if (i < 2) {
                    System.out.print(", ");
                }
            }
            System.out.println(")");

            Matrix number2 = B.times(original);
            System.out.print("(");
            for (int i = 0; i < 3; i++) {
                System.out.print(number2.getEntry​(i, 0));
                if (i < 2) {
                    System.out.print(", ");
                }
            }
            System.out.println(")");

            Matrix number3 = C.times(original);
            System.out.print("(");
            for (int i = 0; i < 3; i++) {
                System.out.print(number3.getEntry​(i, 0));
                if (i < 2) {
                    System.out.print(", ");
                }
            }
            System.out.println(")");
        }
        else {
            System.out.println("not a triple");
        }
    }

    /**
     *
     * @param a - int array
     * @return - the array sorted by size
     */
    public static int[] sort (int[] a) {
        int holder = 0;
        int length = a.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (a[i] > a[j]) {
                    holder = a[i];
                    a[i] = a[j];
                    a[j] = holder;
                }
            }
        }
        return a;
    }
}