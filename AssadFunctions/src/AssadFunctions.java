//*******************************************************************
//  AssadFunctions
//
// Simplify java functions that require many lines of code.
// RECOMMENDED for simple java programs (some lack efficiency).
//
//*******************************************************************

import com.sun.istack.internal.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

@Author(
        name = "Will Assad",
        date = "12/21/2018",
        email = "willassadcode@gmail.com",
)


/* HOW TO USE AssadFunctions:

 public class Main extends AssadFunctions {

    public static void main(String[] args) {
        //call any functions here
    }

 }
 */


/**
  DataManipulation class is used to manipulate
  various data types at ease. Some methods will
  convert data types from one to another, and
  others will manipulate existing data.
 */

class DataManipulation {

    @Simplifier(efficient = false) //convert a string to an integer
    public static int toInt(String integer) {
        return Integer.parseInt(integer);
    }

    @Simplifier(efficient = false)  //convert a string to a double
    public static double toDouble(String decimal) {
        return Double.parseDouble(decimal);
    }

    @Simplifier(efficient = false)  //convert an object to a string
    public static String toString(Object data) {
        return String.valueOf(data);
    }

    @Simplifier(efficient = false)  //convert a number to a char array
    public static char[] numToArray(int num) {
        return String.valueOf(num).toCharArray();
    }

    @Simplifier(efficient = false)  //remove substring(s) from a string
    public static String remove(String string, String... args) {
        for (String arg: args) {
            string = string.replaceAll(arg, "");
        }
        return string;
    }

    //round a double to p amount of places
    public static double round(double x, int p) {
        String pattern = "#.";
        for (int i=1; i<=p; i++) {pattern += "#";}
        DecimalFormat df = new DecimalFormat(pattern);
        return toDouble(df.format(x));
    }

    //generate a random integer between two values
    public static int randint(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @NotNull //return the reversed string of a string
    public static String reverseString(String string) {
        StringBuilder sb = new StringBuilder(string);
        return sb.reverse().toString();
    }
}

/**
 * Conditions class is used to check several conditions.
 * It can shorten if statements used in programs.
 */

class Conditions extends DataManipulation{

    @Simplifier(efficient = false) //check if an integer is between two values
    public static boolean between(int x, int l, int u) {
        return l <= x && x <= u;
    }

    @Simplifier(efficient = false) //check if a double is between two values
    public static boolean between(double x, double l, double u) {
        return l <= x && x <= u;
    }

    @Simplifier(efficient = true) //check if an object is a palindrome
    public static boolean palindrome(Object object) {
        String string = toString(object);
        string = remove(string, "[^a-zA-Z0-9]","\\s").toLowerCase();

        if (string.equals(reverseString(string))) return true;
        return false;
    }
}


/**
 * PrintFunctions class is used to print anything
 * at ease. It can make diagnostic print statements
 * easier to write when checking for logic errors.
 */

class PrintFunctions extends Conditions {

    @OutputVoid //print any data type, ArrayList, and format strings
    public static void print(Object m, Object... args) {
        String type = m.getClass().getName();

        if (type.equals("java.lang.String")) {
            System.out.printf(m + "\n", args);
        } else if (type.equals("java.util.ArrayList")) {
            ArrayList list = (ArrayList) m;
            System.out.println(Arrays.toString(list.toArray()));
        } else {
            System.out.println(m);
        }
    }

    @OutputVoid //print the elements in a double array
    public static void print(double[] array) {
        System.out.println(Arrays.toString(array));
    }

    @OutputVoid //print the elements in a string array
    public static void print(String[] array) {
        System.out.println(Arrays.toString(array));
    }

    @OutputVoid //print the elements in an integer array
    public static void print(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    @OutputVoid //print the elements in a char array
    public static void print(char[] array) {
        System.out.println(Arrays.toString(array));
    }

    @OutputVoid //print the elements in a boolean array
    public static void print(boolean[] array) {
        System.out.println(Arrays.toString(array));
    }

    @OutputVoid //print the elements in a byte array
    public static void print(byte[] array) {
        System.out.println(Arrays.toString(array));
    }

    @OutputVoid //print the elements in a long array
    public static void print(long[] array) {
        System.out.println(Arrays.toString(array));
    }

    @OutputVoid //print the elements in a float array
    public static void print(float[] array) {
        System.out.println(Arrays.toString(array));
    }

    @OutputVoid //print a blank line using system
    public static void print() {
        System.out.println();
    }

    @OutputVoid //print the type of any object
    public static void printType(Object data) {
        print(data.getClass().getName());
    }
}


/**
 * UserInput class is used to take user input
 * with incredible ease. Strings, double, and
 * int can all be scanned from the user in
 * just one line of code.
 */

class UserInput extends PrintFunctions {

    @RequiresUserInput(type = "String") //take input of a string and remove substrings
    public static String scanString(String message, String... args){
        Scanner scan = new Scanner(System.in);
        boolean newLine = false;

        if (message.contains("<new>")) {
            message = remove(message, "<new>");
            newLine = true;
        }

        System.out.print(message);
        String string = scan.nextLine();

        if (args.length > 0) string = remove(string, args);
        if (newLine) print();

        return string;
    }

    @RequiresUserInput(type = "int") //take input of an integer (between two values)
    public static int scanInteger(String message, int... args) {
        while(true) {
            try {
                int i = toInt(scanString(message));
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

    @RequiresUserInput(type = "double") //take input of a double (between two values)
    public static double scanDouble(String message, double... args) {
        while(true) {
            try {
                double d = toDouble(scanString(message));
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
}


/**
 * AssadArrays class is used to make easy
 * changes to arrays and ArrayLists when
 * programming.
 */

//This class is used for easy use of arrays
class AssadArrays extends UserInput {

    @Simplifier(efficient = false) //combine two integer arrays together using array copy
    public static int[] combineArrays(int[] first, int[] second) {
        int[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    @Simplifier(efficient = false) //combine two double arrays together using array copy
    public static double[] combineArrays(double[] first, double[] second) {
        double[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    @Simplifier(efficient = false) //combine two string arrays together using array copy
    public static String[] combineArrays(String[] first, String[] second) {
        String[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    @Contract(pure = true) //reverse an integer array
    public static int[] reverseArray(int[] array) {
        int[] reversed = new int[array.length];
        int a = 0;
        for (int i=array.length-1; i>=0; i--) {
            reversed[a] = array[i]; a++;
        }
        return reversed;
    }

    @Contract(pure = true) //reverse a string array
    public static String[] reverseArray(@NotNull String[] array) {
        String[] reversed = new String[array.length];
        int a = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            reversed[a] = array[i]; a++;
        }
        return reversed;
    }

    @Contract(pure = true) //reverse a char array
    public static char[] reverseArray(@NotNull char[] array) {
        char[] reversed = new char[array.length];
        for (int a = 0, i = array.length - 1; i >= 0; a++, i--) {
            reversed[a] = array[i];
        }
        return reversed;
    }

    @Simplifier(efficient = false) //check if an object array contains a value
    public static boolean arrayContains(Object[] array, Object value) {
        return Arrays.asList(array).contains(value);
    }

    @Simplifier(efficient = false) //check if an integer array contains a value
    public static boolean arrayContains(int[] array, int value) {
        if (!IntStream.of(array).anyMatch(x -> x == value)) {
            return true;
        }
        return false;
    }

    @Simplifier(efficient = false) //check if a double array contains a value
    public static boolean arrayContains(double[] array, double value) {
        if (!DoubleStream.of(array).anyMatch(x -> x == value)) {
            return true;
        }
        return false;
    }

    @Simplifier(efficient = false) //check if a string array contains a value
    public static boolean arrayContains(String[] array, String value) {
        return Arrays.stream(array).anyMatch(value::equals);
    }

    @Simplifier(efficient = false) //get the max amount of times a value appears in an integer array
    public static int maxValue(int[] array) {
        return Arrays.stream(array).max().getAsInt();
    }


    @Simplifier(efficient = true) //get the sum of all elements in an integer array
    public static int sumArray(int[] array) {
        int sum = 0;
        for (int item : array) {
            sum += item;
        }
        return sum;
    }

    @Simplifier(efficient = true) //get the sum of all elements in an integer ArrayList
    public static int sumArray(ArrayList<Integer> array) {
        int sum = 0;
        for (int item : array) {
            sum += item;
        }
        return sum;
    }
}


@ExtendsAll
public class AssadFunctions extends AssadArrays {}
