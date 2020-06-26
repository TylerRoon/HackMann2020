package com.tylerroonprapunt.matrix;

import org.dalton.polyfun.Polynomial;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private int[] coef;   // coefficients p(x) = sum { coef[i] * x^i }
    private int degree;
    private double[][] matrix;

    /**
     *
     * @param rows
     * @param cols
     */
    public Matrix(int rows, int cols) {
        this.matrix = new double[rows][cols];
    }

    /**
     *
     * @param rownum - the row of the targeted spot
     * @param colnum - the column of the targeted spot
     * @param value - the new value of the spot in the matrix
     */
    public void setEntry(int rownum, int colnum, double value) {
        matrix[rownum][colnum] = value;
    }

    /**
     *
     * @param rowNumber - the row of the targeted spot
     * @param colNumber - the column of the targeted spot
     * @return - the value at the given spot
     */
    public double getEntry​(int rowNumber, int colNumber) {
        return matrix[rowNumber][colNumber];
    }

    public Matrix print() {
        int rows = this.matrix.length;
        int cols = this.matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String text = Double.toString(Math.abs(this.getEntry​(i, j)));
                int integerPlaces = text.indexOf('.');
                int decimalPlaces = text.length() - integerPlaces;
                if (this.getEntry​(i, j) < 0) {
                    decimalPlaces = decimalPlaces - 1;
                }
                System.out.print(this.getEntry​(i, j));
                for (int k = 0; k < 20 - decimalPlaces; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
        return this;
    }

    /**
     *
     * @return - Matrix represented by a String
     */
    public String toString() {
        String returnstring = "";
        int rows = this.matrix.length;
        int cols = this.matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double term = this.getEntry​(i, j);
                String ofterm = Double.toString(term);
                String[] splitter = ofterm.toString().split("\\.");
                double digits = splitter[0].length() + splitter[1].length();
                double repetitions = 25;
                returnstring = returnstring + term;
                if (term < 0) {
                    repetitions--;
                }
                repetitions = repetitions - digits;
                for (double k = repetitions; k > 0; k--) {
                    returnstring = returnstring + " ";
                }
            }
            returnstring = returnstring + "\n";
        }
//        System.out.println(returnstring);
        return returnstring;
    }

    /**
     *
     * @param thatmatrix - Matrix being added to this Matrix
     * @return - sum of the two matrices
     */
    public Matrix plus(Matrix thatmatrix) {
        int rows = thatmatrix.matrix.length;
        int cols = thatmatrix.matrix[0].length;
        Matrix sum = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double entry = this.matrix[i][j] + thatmatrix.matrix[i][j];
                sum.setEntry(i, j, entry);
            }
        }
        return sum;
    }

    /**
     *
     * @param thatmatrix - Matrix being multiplied by this Matrix
     * @return - product of the two matrices
     */
    public Matrix times(Matrix thatmatrix) {
        double sum = 0;
        double matrix1holder;
        double matrix2holder;
        int row1 = this.matrix.length;
        int col1 = this.matrix[0].length;
        int row2 = thatmatrix.matrix.length;
        int col2 = thatmatrix.matrix[0].length;
        if (col1 == row2) {
            Matrix product = new Matrix(row1, col2);
            for (int i = 0; i < row1; i++) {
                for (int j = 0; j < col2; j++) {
                    for (int k = 0; k < col1; k++) {
                        matrix1holder = this.matrix[i][k];
                        matrix2holder = thatmatrix.matrix[k][j];
                        sum += matrix1holder * matrix2holder;
                    }
                    product.setEntry(i, j, sum);
                    sum = 0;
                }
            }
            return product;
        } else {
            return null;
        }
    }

    /**
     *
     * @param scalar - number by which each entry in the specified row will be multiplied
     * @param rowNumber - row being multiplied by a scalar
     * @return - Matrix with the modified row
     */
    public Matrix scalarTimesRow​(double scalar, int rowNumber) {
        Matrix returnmatrix = new Matrix(this.matrix.length, this.matrix[0].length);
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                returnmatrix.setEntry(i, j, this.matrix[i][j]);
            }
        }
        for (int i = 0; i < this.matrix[0].length; i++) {
            returnmatrix.matrix[rowNumber][i] = this.matrix[rowNumber][i] * scalar;
        }
        return returnmatrix;
    }

    /**
     *
     * @param firstRow - first row being switched
     * @param secondRow - second row being switched
     * @return - Matrix with the switched rows
     */
    public Matrix switchrows(int firstRow, int secondRow) {
        Matrix returnmatrix = new Matrix(this.matrix.length, this.matrix[0].length);
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                returnmatrix.setEntry(i, j, this.matrix[i][j]);
            }
        }
        for (int i = 0; i < returnmatrix.matrix[0].length; i++) {
            returnmatrix.matrix[secondRow][i] = this.matrix[firstRow][i];
        }
        for (int i = 0; i < returnmatrix.matrix[0].length; i++) {
            returnmatrix.matrix[firstRow][i] = this.matrix[secondRow][i];
        }

        return returnmatrix;
    }

    /**
     *
     * @param scalar - number by which each entry in the first row will be multiplied
     * @param firstRow - row being multiplied by a scalar and added to the second row
     * @param secondRow - row to which the first row is added
     * @return - Matrix with the modified secondRow
     */
    public Matrix linearComboRows​(double scalar, int firstRow, int secondRow) {
        Matrix returnmatrix = new Matrix(this.matrix.length, this.matrix[0].length);
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                returnmatrix.setEntry(i, j, this.matrix[i][j]);
            }
        }
        for (int i = 0; i < this.matrix[0].length; i++) {
            double holder = returnmatrix.matrix[firstRow][i] * scalar
                    + returnmatrix.matrix[secondRow][i];
            returnmatrix.setEntry(secondRow, i, holder);
        }
        return returnmatrix;
    }

    /**
     *
     * @param row - row at which zero down begins (pivot)
     * @param col - column at which zero down begins (pivot)
     * @return - Matrix with all zeros in a column below a pivot
     */
    public Matrix zeroDown(int row, int col) {
        Matrix returnmatrix = new Matrix(this.matrix.length, this.matrix[0].length);
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                returnmatrix.setEntry(i, j, this.matrix[i][j]);
            }
        }
        double repetitions = this.matrix.length - row - 1;
        if (repetitions > 0) {
            for (int i = 0; i < repetitions; i++) {
                double row0holder = returnmatrix.getEntry​(row, col);
                double row1holder = returnmatrix.getEntry​(row + i + 1, col);
                double scalar = -(row1holder / row0holder);
                returnmatrix = returnmatrix.linearComboRows​(scalar, row, row + i + 1);
            }
        }
        return returnmatrix;
    }

    /**
     *
     * @param row - row at which zero up begins (pivot)
     * @param col - column at which zero up begins (pivot)
     * @return - Matrix with all zeros in a column above a pivot
     */
    public Matrix zeroUp(int row, int col) {
        Matrix returnmatrix = new Matrix(this.matrix.length, this.matrix[0].length);
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                returnmatrix.setEntry(i, j, this.matrix[i][j]);
            }
        }
//        System.out.println(returnmatrix.matrix.length);
//        System.out.println(row);
//        System.out.println(col);
//        double repetitions = returnmatrix.matrix.length - (row - 1);
        double repetitions = row;
//        System.out.println("reps" + repetitions);
        if (repetitions > 0) {
            for (int i = 0; i < repetitions; i++) {
                double row0holder = returnmatrix.getEntry​(row, col);
                double row1holder = returnmatrix.getEntry​(row - i - 1, col);
                double scalar = -(row1holder / row0holder);
                returnmatrix = returnmatrix.linearComboRows​(scalar, row, row - i - 1);
            }
        }
        return returnmatrix;
    }

    /**
     *
     * @param thatmatrix - Matrix being altred
     * @param rowstart - Row at which pivot is altered
     * @param colstart - Column at which pivot is altered
     * @return - A matrix where all pivots are all 1, zero up and zero down at all pivots.
     */
    public Matrix nonZeroFirst(Matrix thatmatrix, int rowstart, int colstart) {
        Matrix returnmatrix = new Matrix(this.matrix.length, this.matrix[0].length);
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                returnmatrix.setEntry(i, j, this.matrix[i][j]);
            }
        }
        int rows = thatmatrix.matrix.length;
        int cols = thatmatrix.matrix[0].length;
        loop:
        {
            for (int i = rowstart; i < rows; i++) {
                double holder = thatmatrix.getEntry​(i, colstart);
                if (rowstart == rows - 1) {
                    //zeroup
//                    System.out.println("here");
                    returnmatrix = thatmatrix.scalarTimesRow​((1 / holder), rowstart);
                    returnmatrix = returnmatrix.zeroUp(rowstart, colstart);
//                    System.out.println("rowstart: " + rowstart);
                    break loop;
                }
                if ((holder != 0) && (i == rowstart)) {
//                    System.out.println("bp1");
                    returnmatrix = thatmatrix.scalarTimesRow​((1 / holder), rowstart);
                    returnmatrix = returnmatrix.zeroDown(rowstart, colstart);
                    if (rowstart != 0) {
                        returnmatrix = returnmatrix.zeroUp(rowstart, colstart);
//                        returnmatrix.print(returnmatrix);
                    }
                    break loop;
                }
                if ((holder != 0) && (i != rowstart)) {
//                    System.out.println("bp2");
                    returnmatrix = thatmatrix.switchrows(rowstart, i);
                    returnmatrix = returnmatrix.scalarTimesRow​((1 / holder), 0);
                    returnmatrix = returnmatrix.zeroUp(rowstart, colstart);
                    returnmatrix = returnmatrix.zeroDown(rowstart, colstart);
//                    returnmatrix.print(returnmatrix);

                    break loop;
                }
            }
        }
        return returnmatrix;
    }

    /**
     *
     * @param thatmatrix - matrix being altered
     * @return - rowreduced matrix
     */
    public Matrix reducerowechelon(Matrix thatmatrix) {
        Matrix returnmatrix = new Matrix(this.matrix.length, this.matrix[0].length);
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                returnmatrix.setEntry(i, j, this.matrix[i][j]);
            }
        }
        int rows = thatmatrix.matrix.length;
        int cols = thatmatrix.matrix[0].length;

        int rowstart = 0;
        int colstart = 0;

        loop:
        {
            for (int i = 0; rowstart < rows - 1; i++) {
//                System.out.println("Values: I: " + i + ", rowstart: " + rowstart + " colstart: " + colstart);
                Matrix holdermatrix = returnmatrix.clone();

                if (i == 0) {
                    returnmatrix = thatmatrix.nonZeroFirst(returnmatrix, rowstart, colstart);
//                    returnmatrix.print(returnmatrix);
//                    System.out.println("");
                    rowstart++;
                    colstart++;
                }
                if (i != 0) {

                    returnmatrix = thatmatrix.nonZeroFirst(returnmatrix, rowstart, colstart);
//                    returnmatrix.print(returnmatrix);
//                    System.out.println("");
                    boolean isEqual = compare(holdermatrix, returnmatrix);
//                    if (!isEqual) {
                    rowstart++;
//                    }
                    colstart++;
                }
                if ((rows == cols) && (colstart == cols - 1)) {

//                    System.out.println("here1");
                    returnmatrix = returnmatrix.zeroUp(rowstart, colstart);
                    break loop;
                }
                if ((rows != cols) && (2 * (colstart + 1) == (cols))) {

//                    System.out.println("here2");
                    returnmatrix = returnmatrix.zeroUp(rowstart, colstart);
                    break loop;
                }
//                thatmatrix = returnmatrix.clone();
            }

        }

        double holder = returnmatrix.getEntry​(rowstart,colstart);
        returnmatrix = returnmatrix.scalarTimesRow​((1 / holder), rowstart);

        return returnmatrix;
    }

    /**
     *
     * @return - an identical matrix
     */
    public Matrix clone() {
        Matrix result = new Matrix(this.matrix.length, this.matrix[0].length);
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                result.setEntry(i, j, this.getEntry​(i, j));
            }
        }
        return result;
    }

    /**
     *
     * @param M1 - first matrix
     * @param M2 - second matrix
     * @return - a boolean stating if the matrices are identical
     */
    public boolean compare(Matrix M1, Matrix M2) {
        boolean isEqual = true;
        int rows1 = M1.matrix.length;
        int rows2 = M2.matrix.length;
        int cols1 = M1.matrix[0].length;
        int cols2 = M2.matrix[0].length;

        if ((rows1 != rows2) || (cols1 != cols2)) {
            isEqual = false;
            return isEqual;
        }
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols1; j++) {
                if (M1.getEntry​(i, j) != M2.getEntry​(i, j)) {
                    isEqual = false;
                }
            }
        }
        return isEqual;
    }

    /**
     *
     * @param thatmatrix - the given matrix
     * @return - take the given matrix and add the identity matrix to the right of it
     */
    public Matrix addIdentityMatrix(Matrix thatmatrix) {
        int rows = thatmatrix.matrix.length;
        int cols = thatmatrix.matrix[0].length;

        if (rows != cols) {
            System.out.println("non invertible");
            return null;
        }
        Matrix returnmatrix = new Matrix(rows, (2 * cols));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                returnmatrix.setEntry(i, j, thatmatrix.getEntry​(i, j));
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = cols; j < (2 * cols); j++) {
                if (j == (i + rows)) {
                    returnmatrix.setEntry(i, j, 1);
                }
            }
        }

        return returnmatrix;
    }

    /**
     *
     * @return - inversion of this Matrix
     */
    public Matrix inverse() {
        Matrix clone = this.clone();
        clone = clone.addIdentityMatrix(clone);
//        clone.print(clone);
        clone = clone.reducerowechelon(clone);
        int row = this.matrix.length;
        int col = this.matrix[0].length;
        Matrix returnmatrix = new Matrix(this.matrix.length, this.matrix[0].length);
        for (int i = 0; i < row; i++) {
            for (int j = col; j < (2 * col); j++) {
                returnmatrix.setEntry(i, (j - row), clone.getEntry​(i, j));
            }
        }
        return returnmatrix;
    }

    /**
     *
     * @param degreeP - degree of the polynomial
     * @param dAT -  derivative at what x value
     * @return - a coefficient Matrix essential in differentiation
     */
    public Matrix aForm(int degreeP, int dAT) {
        int rowscol = degreeP + 1;
        Matrix returnmatrix = new Matrix(rowscol, rowscol);
        for (int i = 0; i < rowscol; i++) {
            returnmatrix.setEntry(i, i, 1);
        }
        int diag1rowstart = 0;
        int diag1colstart = 1;
        boolean firsttime1 = true;
        for (int i = diag1rowstart; i < degreeP; i++) {
            if (!firsttime1) {
                double holder = -2 * dAT;
                returnmatrix.setEntry(diag1rowstart, diag1colstart, holder);
            }
            firsttime1 = false;
            diag1colstart++;
            diag1rowstart++;
        }

        int diag2rowstart = 0;
        int diag2colstart = 2;
        for (int i = diag2rowstart; i < degreeP - 1; i++) {
            double holder = dAT * dAT;
            returnmatrix.setEntry(diag2rowstart, diag2colstart, holder);
            diag2colstart++;
            diag2rowstart++;
        }
        return returnmatrix;
    }

    /**
     *
     * @return - a flipped Matrix
     */
    public Matrix flip() {
        Matrix returnmatrix = this.clone();
        int counter = 0;
        for (int i = 0; i < returnmatrix.matrix.length; i++) {
            double holder = this.getEntry​((returnmatrix.matrix.length - 1 - counter), 0);
            returnmatrix.setEntry(i, 0, holder);
            counter++;
        }
        return returnmatrix;
    }

    /**
     *
     * @return - the determinant of a Matrix
     */
    public double determinant() {
        double det;
        int row = this.matrix.length;
        int col = this.matrix[0].length;
        if (row != col) {
            return Double.parseDouble(null);
        }
        if (row == 2) {
            double a = this.getEntry​(0, 0);
            double b = this.getEntry​(0, 1);
            double c = this.getEntry​(1, 0);
            double d = this.getEntry​(1, 1);
            det = a * d - b * c;
            return det;
        }
        else {
            det = 1;
            Matrix holder = this.clone();
            for (int i = 0; i < col; i++) {
                holder = holder.zeroDown(i,i);
                det = det * holder.getEntry​(i,i);
            }
            return det;
        }
    }

    /**
     *
     * @param exponent - what power the Matrix should be risen to
     * @return - a Matrix raised to a power
     */
    public Matrix toPower(int exponent) {
        Matrix returnmatrix = this.clone();
        Matrix original = returnmatrix.clone();
        for (int i = 1; i < exponent; i++) {
            returnmatrix = returnmatrix.times(original);
        }
        return returnmatrix;
    }

}
