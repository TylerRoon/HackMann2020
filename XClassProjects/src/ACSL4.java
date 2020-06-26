import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;

public class ACSL4 {
    public static void main(String[] args) {
        Boolean[] occupied = new Boolean[52];
        for (int i = 0; i < 52; i++) { occupied[i] = false; }

//        Scanner scan2 = new Scanner(System.in);
//        String input = scan2.nextLine();
//        String input = "4 14 24 1 8 12 6 6 3 5 1 5 6";
//        String input = "14 28 31 10 20 24 7 6 6 5 5 6 2 4";
//        String input = "5 30 33 3 20 24 8 6 6 6 5 6 3 4 6";
//        String input = "4 11 15 2 8 20 5 5 2 5 1 6";
        String input = "45 50 48 42 46 40 6 3 2 6 5 4 1";

        String[] entries1 = input.split(" ");
        int[] entries = new int[entries1.length];
        for (int i = 0; i < entries1.length; i++) {
            entries[i] = Integer.parseInt(entries1[i]);
        }
        System.out.println(input);

        int[] opploc = new int[3];
        opploc[0] = entries[0];
        opploc[1] = entries[1];
        opploc[2] = entries[2];
        System.out.println("Opponents at: " + opploc[0] + ", " + opploc[1] + ", " + opploc[2]);

        int[] marker = new int[3];
        marker[0] = entries[3];
        marker[1] = entries[4];
        marker[2] = entries[5];
        System.out.println("Mine at: " + marker[0] + ", " + marker[1] + ", " + marker[2]);

        occupied[opploc[0]] = true;
        occupied[opploc[1]] = true;
        occupied[opploc[2]] = true;
        occupied[marker[0]] = true;
        occupied[marker[1]] = true;
        occupied[marker[2]] = true;

        int rolls = entries[6];
        System.out.println("Number of rolls: " + rolls);

        int[] therolls = new int[rolls];
        int counter = 7;
        System.out.print("Rolls: ");
        for (int i = 0; i < therolls.length; i++) {
            therolls[i] = entries[counter];
            System.out.print(therolls[i] + " ");
            counter++;
        }

        boolean primeCheck = false;
        /////////CODE
        System.out.println("");
        for (int i = 0; i < therolls.length; i++) {
            loop:
            {
                marker = sort(marker);
                System.out.print("Markers: " + marker[0] + ", " + marker[1] + ", " + marker[2]);
                int mymove = therolls[i];
                int themarker = marker[0];
                int originalplace = themarker;
                System.out.print("      " + themarker + " ---> ");
//                themarker = themarker + mymove;
                String[] directions = new String[mymove];
                for (int j = 0; j < mymove; j++) {
                    int before = themarker;
                    if (j == 52) {
                        if (j < mymove - 1) {
                            marker[0] = originalplace;
                            marker = sort(marker);
                            break loop;
                        }
                        if (j == mymove - 1) {
                            marker[0] = 52;
                            marker = sort(marker);
                            break loop;
                        }
                    }
                    themarker = themarker + 1;
                    if ((before == 7) || (before == 12) || (before == 17) || (before == 22) ||
                            (before == 23) || (before == 24) || (before == 25) || (before == 27)
                            || (before == 28) || (before == 29) || (before == 30) || (before == 35)
                            || (before == 40) || (before == 45) || (before == 50) || (before == 51)) {
                        directions[j] = "Vertical";
                    } else {
                        directions[j] = "Horizontal";
                    }
                }
                ////rule 6.1:
//                if (themarker == 52)


                boolean iChanged = true;
                ////rule 4: already occupied
                if (occupied[themarker ]) {
                    System.out.println(" + " + mymove + " Occupied at " + themarker);
                    break loop;
                }
                System.out.print("  I changed! + " + mymove);
                occupied[originalplace] = false;
                occupied[themarker] = true;
                primeCheck = isPrime(themarker);
                System.out.print("  Primecheck: (" + themarker + ")" + primeCheck + "   ");

                ////rule 7: prime
                if (primeCheck) {
                    int counter1 = themarker;
                    int counter2 = 0;
                    for (int j = 0; j < 6; j++) { // try and move 6 forward
                        counter1++;
                        if (counter1 == 52) {
                            if (j < 5) {
                                marker[0] = originalplace;
                                marker = sort(marker);
                                break loop;
                            }
                            if (j == 5) {
                                marker[0] = 52;
                                marker = sort(marker);
                                break loop;
                            }
                        }
                        if (occupied[counter1]) {
                            System.out.println(" + " + counter2 + " Occupied at " + counter1);
                            occupied[themarker] = false;
                            occupied[counter1 - 1] = true;
                            marker[0] = counter1 - 1;
                            break loop;
                        }
                        counter2++;
                    }
                }
                ////rule 8: squared
                boolean isSquare = false;
                if ((themarker == 9) || (themarker == 16) || (themarker == 25) || (themarker == 36) || (themarker == 49)) {
                    isSquare = true;
                }
                if (isSquare) {
                    System.out.print("  Landed on Square: (" + themarker + ")" + isSquare + "   ");
                    int counter1 = themarker;
                    int counter2 = 0;
                    for (int j = 6; j > 0; j--) { // try and move 6 backward
                        counter1--;
                        if (occupied[counter1]) {
                            System.out.println(" + " + counter2 + " Occupied at " + counter1);
                            occupied[themarker] = false;
                            occupied[counter1 + 1] = true;
                            marker[0] = counter1+1;
                            break loop;
                        }
                        counter2--;
                    }
                }
                ////rule 9 verticals and horizontals
                if (!isSquare && !primeCheck) {
                    directions[0] = "C.Horizontal";
//                    System.out.println("");
                    for (int j = 0; j < mymove; j++) {
//                        System.out.println("Directions: " + directions[j]);
                    }
//                    System.out.println("");
                }
                boolean rule9 = false;
                otherloop:{
                    for (int j = 0; j < directions.length; j++) {
                        if (directions[j].contains("Vertical")) {
//                            System.out.println("Apply Rule 9");
                            rule9 = true;
                            break otherloop;
                        }
                    }
                }
                if (rule9) {
                    System.out.print("    Rule9 Approved ");
                    int counter1 = originalplace;
                    int counter2 = 0;
                    System.out.print("(" + originalplace + ", " + themarker + ")");
                    for (int j = originalplace; j < themarker; j++) { // try and move 6 forward
                        miniloop: {
                            counter1++;
                            if (counter1 % mymove != 0) {
                                System.out.print("no" + counter1 + ", ");
                                if (counter1 == themarker) {
                                    System.out.print("here");
                                    occupied[themarker] = false;
                                    occupied[originalplace] = true;
                                    marker[0] = originalplace;
                                    break loop;
                                }

                                break miniloop;
                            }
                            if (occupied[counter1]) {
//                            System.out.println(" + " + counter2 + " Occupied at " + counter1);
                                System.out.print("oq, ");
//                            occupied[themarker] = false;
//                            occupied[counter1 - 1] = true;
//                            marker[0] = counter1 - 1;
                                break miniloop;
                            }
//                            System.out.print("!!!(" + counter1 + "," + mymove + ")!!!");

//                            System.out.print(counter1 % mymove);
                            if (counter1 % mymove == 0) {
                                System.out.println(" + " + (counter2 + 1) + " Final position " + counter1);
                                occupied[themarker] = false;
                                occupied[counter1] = true;
                                marker[0] = counter1;
                                break loop;
                            }
                            counter2++;
                        }
                    }
                }

                System.out.println(" " + themarker);
                marker[0] = themarker;
                marker = sort(marker);
            }
        }
        System.out.println("");
        marker = sort(marker);
        System.out.println("Final Markers: " + marker[0] +", "+ marker[1] + ", "+ marker[2]);


        //////////END
    }
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
    public static boolean isPrime (int n) {
        if (n<=1) { return false; }
        for (int i = 2; i < n; i++) {
            if (n%i == 0) {
                return false;
            }
        }
        return true;
    }
}
