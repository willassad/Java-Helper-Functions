//*******************************************************************
//  TESTING ASSADFUNCTIONS
//
// Simplify java functions that require many lines of code.
// RECOMMENDED for new java programmers (some lack efficiency).
//
// author: Will Assad, email: willassadcode@gmail.com
//*******************************************************************

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Random;

public class AssadFunctions {
    public static void print(Object m, Object... args) {
        String type = m.getClass().getName();

        if (type.equals("java.lang.String")) {
            System.out.printf(m + "\n", args);
        }  else {
            System.out.println(m);
        }
    }

    public static String scanstring(String message){
        Scanner scan = new Scanner(System.in);
        System.out.print(message);
        return scan.nextLine();
    }

    public static int scaninteger(String message, int... args) {
        while(true) {
            try {
                int i = toint(scanstring(message));
                if (args.length == 0) {return i;}

                int min = args[0]; int max = args[1];
                if (between(i, min, max)) { return i; }

               print("Must be between %s and %s.", min, max);

            } catch(NumberFormatException e) {print("Must enter an integer."); }
        }
    }

    public static double scandouble(String message, int... args) {
        while(true) {
            try {
                double i = todouble(scanstring(message));
                if (args.length == 0) {return i;}

                int min = args[0]; int max = args[1];
                if (between(i, min, max)) { return i; }

                print("Must be between %s and %s.", min, max);

            } catch(NumberFormatException e) {print("Must enter a double."); }
        }
    }

    public static int toint(String integer) {
        return Integer.parseInt(integer);
    }

    public static double todouble(String decimal) {
        return Double.parseDouble(decimal);
    }

    public static String tostring(Object data) {
        return String.valueOf(data);
    }

    public static boolean between(int x, int l, int u) { return l <= x && x <= u; }
    public static boolean between(double x, int l, int u) { return l <= x && x <= u; }

    public static double round(double x, int p) {
        String pattern = "#.";
        for (int i=1; i<=p; i++) {pattern += "#";}
        DecimalFormat df = new DecimalFormat(pattern);
        return todouble(df.format(x));
    }

    public static int randint(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


}
