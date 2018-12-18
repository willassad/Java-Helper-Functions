//*******************************************************************
//  TESTING ASSADFUNCTIONS
//
// Simplify java functions that require many lines of code.
// RECOMMENDED for new java programmers (some lack efficiency).
//
// author: Will Assad, email: willassadcode@gmail.com
//*******************************************************************

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.IntStream;

public class AssadFunctions {
    public static void print(Object m, Object... args) {
        String type = m.getClass().getName();
        //System.out.println(type);

        if (type.equals("java.lang.String")) {
            System.out.printf(m + "\n", args);
        } else if (type.equals("java.util.ArrayList")) {
            ArrayList list = (ArrayList) m;
            System.out.println(Arrays.toString(list.toArray()));
        } else {
            System.out.println(m);
        }
    }
    public static void print(double[] array) { System.out.println(Arrays.toString(array)); }
    public static void print(String[] array) { System.out.println(Arrays.toString(array)); }
    public static void print(int[] array)    { System.out.println(Arrays.toString(array)); }
    public static void print(char[] array)   { System.out.println(Arrays.toString(array)); }
    public static void print(boolean[] array){ System.out.println(Arrays.toString(array)); }
    public static void print(byte[] array)   { System.out.println(Arrays.toString(array)); }
    public static void print(long[] array)   { System.out.println(Arrays.toString(array)); }
    public static void print(float[] array)  { System.out.println(Arrays.toString(array)); }
    public static void print() { System.out.println(); }

    public static String scanString(String message, String... args){
        Scanner scan = new Scanner(System.in);
        System.out.print(message);
        String string = scan.nextLine();

        if (args.length > 0) { string = remove(string, args); }

        return string;
    }

    public static int scanInteger(String message, int... args) {
        while(true) {
            try {
                int i = toint(scanString(message));
                if (args.length == 0) { return i; }
                int min = args[0];

                if (args.length == 1) {
                    if (i >= min) {
                        return i;
                    } else {
                        print("Must be greater than or equal to %s.", min);
                    }
                } else if (args.length == 2) {
                    int max = args[1];
                    if (between(i, min, max)) { return i; }
                    print("Must be between %s and %s.", min, max);
                }

            } catch(NumberFormatException e) {
                print("Must enter an integer.");
            }
        }
    }

    public static double scanDouble(String message, double... args) {
        while(true) {
            try {
                double d = todouble(scanString(message));
                if (args.length == 0) { return d; }
                double min = args[0];

                if (args.length == 1) {
                    if (d >= min) {
                        return d;
                    } else {
                        print("Must be greater than or equal to %s.", min);
                    }
                } else if (args.length == 2) {
                    double max = args[1];
                    if (between(d, min, max)) { return d; }
                    print("Must be between %s and %s.", min, max);
                }

            } catch(NumberFormatException e) {
                print("Must enter a decimal.");
            }
        }
    }

    public static int toint(String integer) { return Integer.parseInt(integer); }
    public static double todouble(String decimal) { return Double.parseDouble(decimal); }
    public static String tostring(Object data) { return String.valueOf(data); }
    public static char[] numToArray(int num) {
        return String.valueOf(num).toCharArray();
    }

    public static String remove(String string, String... args) {
        for (String arg: args) {
            string = string.replaceAll(arg, "");
        }
        return string;
    }


    public static boolean between(int x, int l, int u) { return l <= x && x <= u; }
    public static boolean between(double x, double l, double u) { return l <= x && x <= u; }

    public static boolean arrayContains(Object[] array, Object value) {
        return Arrays.asList(array).contains(value);
    }

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

    public static int[] combineArrays(int[] first, int[] second) {
        int[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static double[] combineArrays(double[] first, double[] second) {
        double[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static String[] combineArrays(String[] first, String[] second) {
        String[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static int[] reverseArray(int[] array) {
        int[] reversed = new int[array.length];
        int a = 0;
        for (int i=array.length-1; i>=0; i--) {
            reversed[a] = array[i]; a++;
        }
        return reversed;
    }

    public static String[] reverseArray(String[] array) {
        String[] reversed = new String[array.length];
        int a = 0;
        for (int i=array.length-1; i>=0; i--) {
            reversed[a] = array[i]; a++;
        }
        return reversed;
    }

    public static boolean arrayContains(int[] array, int value) {
        if (!IntStream.of(array).anyMatch(x -> x == value)) {
            return true;
        }
        return false;
    }
}
