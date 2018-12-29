//*******************************************************************
// AssadFunctions
//
// Simplify java functions that require many lines of code.
// RECOMMENDED for simple java programs (some lack efficiency).
//
//*******************************************************************


import annotations.*;
import com.sun.istack.internal.NotNull;

import java.io.*;
import java.util.*;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.stream.IntStream;
import java.util.stream.DoubleStream;
import java.util.regex.PatternSyntaxException;

@Author(
        name = "Will Assad",
        date = "12/21/2018",
        email = "willassadcode@gmail.com",
        status = "In Progress"
)


/*
 DataManipulation class is used to manipulate
 various data types at ease. Some methods will
 convert data types from one to another, and
 others will manipulate existing data.
 */

class DataManipulation {

    /**
     * Converts a {@code String} value to an integer value.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    Integer.parseInt(integer)
     * </pre>
     *
     * @param integer the {@code String} value to convert to an int.
     * @throws NumberFormatException if integer value is not a digit.
     * @return the {@code int} value of the string.
     */
    @Simplifier(efficient = false)
    static int toInt(String integer) throws NumberFormatException {
        //parse the string using parseInt
        return Integer.parseInt(integer);
    }

    /**
     * Converts a {@code String} value to a double value.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    Double.parseDouble(decimal)
     * </pre>
     *
     * @param decimal the {@code String} value to convert to a double.
     * @throws NumberFormatException if double value is not a digit.
     * @return the {@code double} value of a string.
     */
    @Simplifier(efficient = false)
    static double toDouble(String decimal) throws NumberFormatException {
        //parse the string using parseDouble
        return Double.parseDouble(decimal);
    }

    /**
     * Returns a string representation of an {@code Object}.
     * The {@code toString} method returns a string that
     * "textually represents" this object.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    String.valueOf(data)
     * </pre>
     *
     * @param data the {@code Object} to be converted to string.
     * @throws IndexOutOfBoundsException if data throws exception.
     * @return the {@code String} representation of the object.
     */
    @Simplifier(efficient = false)
    static String toString(Object data) throws IndexOutOfBoundsException {
        //return the value of the data
        return String.valueOf(data);
    }

    /**
     * Returns the {@code char} value at the first index.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    string.charAt(0)
     * </pre>
     *
     * @param string the {@code String} value to convert to a char.
     * @return the {@code char} value at first index of this string.
     *
     */
    @Simplifier(efficient = false)
    static char toChar(String string) {
        //return the first character
        return string.charAt(0);
    }

    /**
     * Converts an {@code String} to a new character array.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    string.toCharArray()
     * </pre>
     *
     * @param string the {@code String} to be converted to a char array
     * @return a newly allocated character array whose length is the length
     *         of this string and whose contents are initialized to contain
     *         the character sequence represented by this string.
     */
    @Simplifier(efficient = false)
    static char[] toArray(String string) {
        //return string using toCharArray
        return string.toCharArray();
    }

    /**
     * Converts an {@code int} to a new character array.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    String.valueOf(num).toCharArray()
     * </pre>
     *
     * @param num the {@code int} to be converted to a char array
     * @return a newly allocated character array whose length is the length
     *         of this int and whose contents are initialized to contain
     *         the character sequence represented by this int.
     */
    @Simplifier(efficient = false)
    static char[] toArray(int num) {
        //take the value of the int and return array
        return String.valueOf(num).toCharArray();
    }

    /**
     * Converts an {@code String[]} to an integer array.
     *
     * @param stringArray the {@code String[]} to be converted to an int array.
     * @throws NumberFormatException if any values contained in stringArray
     *         are not of type integer as Integer.parseInt throws exception.
     * @return an integer array representing the values in the string array.
     */
    @Simplifier(efficient = true)
    static int[] stringToIntArray(String[] stringArray) throws NumberFormatException {
        //initiate the array that will contain the integers
        int[] integers = new int[stringArray.length];

        //loop through each string and add as an integer to the array
        for (int i = 0; i < integers.length; i++){
            String formatValue = stringArray[i].replaceAll("\\s","");
            integers[i] = Integer.parseInt(formatValue);
        }

        //return the new array
        return integers;
    }

    /**
     * Removes {@code args.length} substrings from a string.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    string.replaceAll(arg, "")
     * </pre>
     *
     * @param string the base {@code String} to remove substrings from.
     * @param args the {@code String[]} of substrings to remove.
     * @throws PatternSyntaxException if regex is not recognized.
     * @return the new string with all substrings removed.
     */
    @Simplifier(efficient = false)
    static String remove(String string, String... args) throws PatternSyntaxException {
        //loop through each value and remove it from string
        for (String arg: args) {
            string = string.replaceAll(arg, "");
        }

        //return the new string
        return string;
    }

    /**
     * Rounds a {@code double} to p amount of decimal places.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    DecimalFormat df = new DecimalFormat(pattern)
     *    Double.parseDouble(df.format(x))
     * </pre>
     *
     * @param x the base {@code double} to round.
     * @param p the {@code int} amount of places to round to.
     * @throws ArithmeticException if rounding on round mode.
     * @return the rounded double value.
     */
    static double round(double x, int p) throws ArithmeticException {
        //initiate the patter for DecimalFormat
        StringBuilder pattern = new StringBuilder("#.");

        //add to placeholder depending on amount of places
        for (int i = 1; i <= p; i++) pattern.append("#");

        //create instance of DecimalFormat class using pattern
        DecimalFormat df = new DecimalFormat(pattern.toString());

        //round and return the result as a double
        return toDouble(df.format(x));
    }

    /**
     * Generates a {@code int} between two values (inclusive).
     * The value returned is identical to what would be returned by:
     * <pre>
     *    Random r = new Random()
     *    r.nextInt((max - min) + 1) + min
     * </pre>
     *
     * @param min the minimum {@code int} range.
     * @param max the maximum {@code int} range.
     * @throws IllegalArgumentException if bound is negative.
     * @return a pseudo-random number between max and min.
     */
    static int randInt(@Range int min, int max) throws IllegalArgumentException {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Reverses a {@code String} value using StringBuilder.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    StringBuilder sb = new StringBuilder(string)
     *    sb.reverse().toString()
     * </pre>
     *
     * @param string the {@code String} value to reverse.
     * @return the reversed string value.
     */
    @NotNull
    static String reverseString(String string) {
        //create instance of StringBuilder, return reverse
        StringBuilder sb = new StringBuilder(string);
        return sb.reverse().toString();
    }
}

/**
 * Conditions class is used to check several conditions.
 * It can shorten if statements used in programs.
 */

class Conditions extends DataManipulation {

    /**
     * Check if an {@code int} value is between two integers (inclusive).
     * The value returned is identical to what would be returned by:
     * <pre>
     *    l <= x && x <= u
     * </pre>
     *
     * @param x the {@code int} value to check if between two values.
     * @param l the minimum {@code int} value x must be >= to.
     * @param u the maximum {@code int} value x must be <= to.
     * @return true if between the two values.
     */
    @Simplifier(efficient = false)
    static boolean between(int x, int l, int u) {
        //return true if x is between l and u
        return l <= x && x <= u;
    }

    /**
     * Check if an {@code double} value is between two doubles (inclusive).
     * The value returned is identical to what would be returned by:
     * <pre>
     *    l <= x && x <= u
     * </pre>
     *
     * @param x the {@code double} value to check if between two values.
     * @param l the minimum {@code double} value x must be >= to.
     * @param u the maximum {@code double} value x must be <= to.
     * @return true if between the two values.
     */
    @Simplifier(efficient = false)
    static boolean between(double x, double l, double u) {
        //return true if x is between l and u
        return l <= x && x <= u;
    }

    /**
     * Check if a {@code String} value is a palindrome.
     * The value returned is identical to what would be returned by:
     * <pre>
     *     String string = toString(object)
     *     string = remove(string, "[^a-zA-Z0-9]","\\s").toLowerCase()
     *     StringBuilder sb = new StringBuilder(string)
     *     String reverse = sb.reverse().toString()
     *     string.equals(reverse)
     * </pre>
     *
     * @param object the {@code Object} value to check if palindrome.
     * @throws IndexOutOfBoundsException if object throws exception.
     * @return true if is a palindrome.
     */
    @Simplifier(efficient = true)
    static boolean palindrome(Object object) throws IndexOutOfBoundsException {
        //convert to string to check if palindrome
        String string = toString(object);

        //remove all punctuation, whitespace, and make lowercase
        string = remove(string, "[^a-zA-Z0-9]","\\s").toLowerCase();

        //if the string is the same forwards and backwards, return true
        return string.equals(reverseString(string));
    }

    /**
     * Check if an {@code int} value is even.
     * The value returned is identical to what would be returned by:
     * <pre>
     *     number % 2 == 0
     * </pre>
     *
     * @param number the {@code int} value to check if even.
     * @throws IllegalArgumentException if number is zero as
     *         zero is neither odd or even
     * @return true if is even.
     */
    @Simplifier(efficient = false) //check if a number is even
    static boolean isEven(int number) throws IllegalArgumentException {
        //check if even using modulus
        if (number == 0) throw new IllegalArgumentException();
        return number % 2 == 0;
    }
}


/**
 * PrintFunctions class is used to print anything
 * at ease. It can make diagnostic print statements
 * easier to write when checking for logic errors.
 */

class PrintFunctions extends Conditions {

    /**
     * Print an {@code Object} to the console.
     *
     * @param m the {@code Object} value to print.
     * @param args the {@code Object[]} to format to a string.
     * @throws NullPointerException if the <tt>format</tt> is
     *         <tt>null</tt> depending on what is being printed.
     */
    @OutputVoid //print any data type, ArrayList, and format strings
    static void print(Object m, Object... args) throws NullPointerException {
        //get the type of the object to be printed
        String type = m.getClass().getName();

        switch (type) {
            //format the object with placeholders if string
            case "java.lang.String":
                System.out.printf(m + "\n", args);
                break;

            //convert to string and print if ArrayList
            case "java.util.ArrayList":
                ArrayList list = (ArrayList) m;
                System.out.println(Arrays.toString(list.toArray()));
                break;

            //print the object
            default:
                System.out.println(m);
                break;
        }
    }

    /**
     * Print an {@code double[]} as a string to the console.
     *The value printed is identical to what would be printed by:
     * <pre>
     *     System.out.println(Arrays.toString(array))
     * </pre>
     *
     * @param array the {@code double[]} array to print.
     */
    @OutputVoid
    static void print(double[] array) {
        System.out.println(Arrays.toString(array));
    }

    /**
     * Print an {@code String[]} as a string to the console.
     *The value printed is identical to what would be printed by:
     * <pre>
     *     System.out.println(Arrays.toString(array))
     * </pre>
     *
     * @param array the {@code String[]} array to print.
     */
    @OutputVoid
    static void print(String[] array) {
        System.out.println(Arrays.toString(array));
    }

    /**
     * Print an {@code int[]} as a string to the console.
     *The value printed is identical to what would be printed by:
     * <pre>
     *     System.out.println(Arrays.toString(array))
     * </pre>
     *
     * @param array the {@code int[]} array to print.
     */
    @OutputVoid
    static void print(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    /**
     * Print an {@code char[]} as a string to the console.
     *The value printed is identical to what would be printed by:
     * <pre>
     *     System.out.println(Arrays.toString(array))
     * </pre>
     *
     * @param array the {@code char[]} array to print.
     */
    @OutputVoid
    static void print(char[] array) {
        System.out.println(Arrays.toString(array));
    }

    /**
     * Print an {@code boolean[]} as a string to the console.
     *The value printed is identical to what would be printed by:
     * <pre>
     *     System.out.println(Arrays.toString(array))
     * </pre>
     *
     * @param array the {@code boolean[]} array to print.
     */
    @OutputVoid
    static void print(boolean[] array) {
        System.out.println(Arrays.toString(array));
    }

    /**
     * Print an {@code byte[]} as a string to the console.
     *The value printed is identical to what would be printed by:
     * <pre>
     *     System.out.println(Arrays.toString(array))
     * </pre>
     *
     * @param array the {@code byte[]} array to print.
     */
    @OutputVoid
    static void print(byte[] array) {
        System.out.println(Arrays.toString(array));
    }

    /**
     * Print an {@code long[]} as a string to the console.
     *The value printed is identical to what would be printed by:
     * <pre>
     *     System.out.println(Arrays.toString(array))
     * </pre>
     *
     * @param array the {@code long[]} array to print.
     */
    @OutputVoid
    static void print(long[] array) {
        System.out.println(Arrays.toString(array));
    }

    /**
     * Print an {@code float[]} as a string to the console.
     *The value printed is identical to what would be printed by:
     * <pre>
     *     System.out.println(Arrays.toString(array))
     * </pre>
     *
     * @param array the {@code float[]} array to print.
     */
    @OutputVoid
    static void print(float[] array) {
        System.out.println(Arrays.toString(array));
    }

    /**
     * Print an blank line to the console.
     *The value printed is identical to what would be printed by:
     * <pre>
     *     System.out.println()
     * </pre>
     *
     */
    @OutputVoid
    static void print() {
        System.out.println();
    }

    /**
     * Print the {@code String} type of an object
     *The value printed is identical to what would be printed by:
     * <pre>
     *     System.out.println(data.getClass().getName())
     * </pre>
     *
     * @param data the {@code Object} to print the type of/
     */
    @OutputVoid
    static void printType(Object data) {
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

    /**
     * Take the user input of a {@code String} and strip substrings.
     *
     * @param message the {@code String} message to display for input.
     * @param args the {@code String[]} of substrings to strip from input.
     * @throws PatternSyntaxException if regex is not recognized.
     * @return the user input as a string.
     */
    @RequiresUserInput(type = "String")
    static String scanString(String message, String... args) throws PatternSyntaxException {
        //create an instance of the scanner class
        Scanner scan = new Scanner(System.in);

        //check if <new> tag in message to print new line after input
        boolean newLine = false;

        if (message.contains("<new>")) {
            message = remove(message, "<new>");
            newLine = true;
        }

        //print the message and scan user input
        System.out.print(message);
        String string = scan.nextLine();

        //remove all substrings in args
        if (args.length > 0) string = remove(string, args);
        if (newLine) print();

        return string;
    }

    /**
     * Take the user input of a {@code char} and repeat input until
     * character value properly entered from the user.
     *
     * @param message the {@code String} message to display for input.
     * @return the user input as a character.
     */
    @RequiresUserInput(type = "char") //take user input of a char
    static char scanCharacter(String message) {
        //loop until character properly entered
        while (true) {
            //scan input as a string and check if character
            String input = scanString(message);
            if (input.length() == 1) return toChar(input);
            print("Must enter a character.");
        }
    }

    /**
     * Take the user input of an {@code int} and repeat until integer
     * value properly entered from the user and the integer value is
     * between the values provided in the @Range args.
     *
     * @param message the {@code String} message to display for input.
     * @param args the {@code int[]} values used as a range the input
     *        must be between. If one value is provided, the input
     *        must be greater than this value. If two values are
     *        provided, the input must be between the two values.
     * @return the user input as an integer.
     */
    @RequiresUserInput(type = "int")
    static int scanInteger(String message, @Range int... args) {
        //loop until integer properly entered
        while(true) {
            //attempt to parse the input to an integer
            try {
                //get the integer entered
                int i = toInt(scanString(message));

                //if no range provided, return the integer
                if (args.length == 0) { return i; }
                int min = args[0];

                //if min value given, check if int is greater than min
                if (args.length == 1) {
                    if (i >= min) {
                        return i;
                    } else {
                        print("Must be greater than or equal to %s.", min);
                    }
                }

                //if min and max values given, check if int between them
                else if (args.length == 2) {
                    int max = args[1];
                    if (between(i, min, max)) { return i; }
                    print("Must be between %s and %s.", min, max);
                }

            //user did not enter an integer
            } catch(NumberFormatException e) {
                print("Must enter an integer.");
            }
        }
    }

    /**
     * Take the user input of a {@code double} and repeat until double
     * value properly entered from the user and the double value is
     * between the values provided in the @Range args.
     *
     * @param message the {@code String} message to display for input.
     * @param args the {@code double[]} values used as a range the input
     *        must be between. If one value is provided, the input
     *        must be greater than this value. If two values are
     *        provided, the input must be between the two values.
     * @return the user input as a double.
     */
    @RequiresUserInput(type = "double")
    static double scanDouble(String message, @Range double... args) {
        //loop until double properly entered
        while(true) {
            //attempt to parse the input to a double
            try {
                //get the double that was entered
                double d = toDouble(scanString(message));

                //if no range provided, return the double
                if (args.length == 0) { return d; }
                double min = args[0];

                //if min value given, check if double is greater than min
                if (args.length == 1) {
                    if (d >= min) {
                        return d;
                    } else {
                        print("Must be greater than or equal to %s.", min);
                    }
                }

                //if min and max values given, check if double between them
                else if (args.length == 2) {
                    double max = args[1];
                    if (between(d, min, max)) { return d; }
                    print("Must be between %s and %s.", min, max);
                }

                //user did not enter a double
            } catch(NumberFormatException e) {
                print("Must enter a decimal.");
            }
        }
    }

    /**
     * Take the user input of a {@code String[]} and repeat until
     * {@code args[0]} amount of values provided if {@code args.length}
     * is greater than zero.
     *
     * @param message the {@code String} message to display for input.
     * @param args the {@code int[]} value used if a certain amount of
     *        inputs are required from the user.
     * @throws PatternSyntaxException if the expression's syntax is invalid
     * @return the user input as a string array.
     */
    @RequiresUserInput(type = "String[]")
    static String[] scanStringArray(String message, @Size int... args) throws PatternSyntaxException {
        while(true) {
            //get input as string and remove whitespace
            String string = scanString(message, "\\s");

            //split the string into an array
            String[] array = string.split(",");

            //if size of array provided, check if input valid
            if (args.length > 0) {
                if (array.length == args[0]) return array;
                print("Must enter %s values.", args[0]);
            } else return array;
        }
    }

    /**
     * Take the user input of a {@code int[]} and repeat until
     * {@code args[0]} amount of values provided if {@code args.length}
     * is greater than zero, and repeat until integer values properly
     * entered from the user.
     *
     * @param message the {@code String} message to display for input.
     * @param args the {@code int[]} value used if a certain amount of
     *        inputs are required from the user.
     * @return the user input as an integer array.
     */
    @RequiresUserInput(type = "int[]")
    static int[] scanIntegerArray(String message, @Size int... args) {
        //loop until proper integer values entered
        while(true) {
            try {
                //take input as string array and convert to int array
                return stringToIntArray(scanStringArray(message, args));
            } catch(NumberFormatException e) {
                print("Must enter integer values.");
            }
        }
    }

}


/**
 * AssadArrays class is used to make easy
 * changes to arrays and ArrayLists when
 * programming.
 */

class AssadArrays extends UserInput {

    /**
     * Combine two {@code int[]} arrays together to form a new array.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    int[] result = new int[first.length + second.length]
     *    System.arraycopy(first, 0, result, 0, first.length)
     *    System.arraycopy(second, 0, result, first.length, second.length)
     *    result
     * </pre>
     *
     * @param first the first {@code int[]} to be copied to the result.
     * @param second the second {@code int[]} to be copied to the result.
     * @throws NegativeArraySizeException if <tt>newLength</tt> is negative
     * @throws NullPointerException if <tt>original</tt> is null
     * @return the resulting array from combining first and second.
     */
    @Simplifier(efficient = false)
    static int[] combineArrays(int[] first, int[] second) throws NullPointerException {
        //make the final array contain the values of the first array
        //and also the empty values for the length of the second array
        int[] result = Arrays.copyOf(first, first.length + second.length);

        //copy the values of the second array to the spaces in the resulting array
        System.arraycopy(second, 0, result, first.length, second.length);

        return result;
    }

    /**
     * Combine two {@code double[]} arrays together to form a new array.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    double[] result = new double[first.length + second.length]
     *    System.arraycopy(first, 0, result, 0, first.length)
     *    System.arraycopy(second, 0, result, first.length, second.length)
     *    result
     * </pre>
     *
     * @param first the first {@code double[]} to be copied to the result.
     * @param second the second {@code double[]} to be copied to the result.
     * @throws NegativeArraySizeException if <tt>newLength</tt> is negative
     * @throws NullPointerException if <tt>original</tt> is null
     * @return the resulting array from combining first and second.
     */
    @Simplifier(efficient = false)
    static double[] combineArrays(double[] first, double[] second) throws NullPointerException {
        //make the final array contain the values of the first array
        //and also the empty values for the length of the second array
        double[] result = Arrays.copyOf(first, first.length + second.length);

        //copy the values of the second array to the spaces in the resulting array
        System.arraycopy(second, 0, result, first.length, second.length);

        return result;
    }

    /**
     * Combine two {@code String[]} arrays together to form a new array.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    String[] result = new String[first.length + second.length]
     *    System.arraycopy(first, 0, result, 0, first.length)
     *    System.arraycopy(second, 0, result, first.length, second.length)
     * </pre>
     *
     * @param first the first {@code String[]} to be copied to the result.
     * @param second the second {@code String[]} to be copied to the result.
     * @throws NegativeArraySizeException if <tt>newLength</tt> is negative
     * @throws NullPointerException if <tt>original</tt> is null
     * @return the resulting array from combining first and second.
     */
    @Simplifier(efficient = false)
    static String[] combineArrays(String[] first, String[] second) throws NullPointerException {
        //make the final array contain the values of the first array
        //and also the empty values for the length of the second array
        String[] result = Arrays.copyOf(first, first.length + second.length);

        //copy the values of the second array to the spaces in the resulting array
        System.arraycopy(second, 0, result, first.length, second.length);

        return result;
    }

    /**
     * Reverse an {@code int[]} array using a for loop.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    int[] reversed = new int[array.length]
     *    for (int a = 0, i = array.length - 1; i >= 0; a++, i--) {
     *        reversed[a] = array[i]
     *    }
     * </pre>
     *
     * @param array the {@code int[]} array to reverse.
     * @return the new reversed array.
     */
    @Contract(pure = true)
    static int[] reverseArray(int[] array) {
        //initialize the reversed array
        int[] reversed = new int[array.length];

        //use two counters to add the elements in reverse
        for (int a = 0, i = array.length - 1; i >= 0; a++, i--) {
            reversed[a] = array[i];
        }
        return reversed;
    }

    /**
     * Reverse an {@code String[]} array using a for loop.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    String[] reversed = new String[array.length]
     *    for (int a = 0, i = array.length - 1; i >= 0; a++, i--) {
     *        reversed[a] = array[i]
     *    }
     * </pre>
     *
     * @param array the {@code String[]} array to reverse.
     * @return the new reversed array.
     */
    @Contract(pure = true)
    static String[] reverseArray(@NotNull String[] array) {
        //initialize the reversed array
        String[] reversed = new String[array.length];

        //use two counters to add the elements in reverse
        for (int a = 0, i = array.length - 1; i >= 0; a++, i--) {
            reversed[a] = array[i];
        }
        return reversed;
    }

    /**
     * Reverse an {@code char[]} array using a for loop.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    char[] reversed = new char[array.length]
     *    for (int a = 0, i = array.length - 1; i >= 0; a++, i--) {
     *        reversed[a] = array[i]
     *    }
     * </pre>
     *
     * @param array the {@code char[]} array to reverse.
     * @return the new reversed array.
     */
    @Contract(pure = true)
    static char[] reverseArray(@NotNull char[] array) {
        //initialize the reversed array
        char[] reversed = new char[array.length];

        //use two counters to add the elements in reverse
        for (int a = 0, i = array.length - 1; i >= 0; a++, i--) {
            reversed[a] = array[i];
        }
        return reversed;
    }

    /**
     * Check if an {@code Object[]} array contains a value.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    Arrays.asList(array).contains(value)
     * </pre>
     *
     * @param array the {@code Object[]} array to check if contains value.
     * @param value the {@code Object} to check if contained by array.
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     * @return true if the array contains the value.
     */
    @Simplifier(efficient = false)
    static boolean arrayContains(Object[] array, Object value) throws ClassCastException {
        //convert to list and check if contains value
        return Arrays.asList(array).contains(value);
    }

    /**
     * Check if an {@code int[]} array contains a value.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    IntStream.of(array).anyMatch(x -> x == value)
     * </pre>
     *
     * @param array the {@code int[]} array to check if contains value.
     * @param value the {@code int} to check if contained by array.
     * @return true if the array contains the value.
     */
    @Simplifier(efficient = false)
    static boolean arrayContains(int[] array, int value) {
        //use lambda anyMatch to check if value in array
        return IntStream.of(array).anyMatch(x -> x == value);
    }

    /**
     * Check if a {@code double[]} array contains a value.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    DoubleStream.of(array).anyMatch(x -> x == value)
     * </pre>
     *
     * @param array the {@code double[]} array to check if contains value.
     * @param value the {@code double} to check if contained by array.
     * @return true if the array contains the value.
     */
    @Simplifier(efficient = false)
    static boolean arrayContains(double[] array, double value) {
        //use lambda anyMatch to check if value in array
        return DoubleStream.of(array).anyMatch(x -> x == value);
    }

    /**
     * Check if a {@code String[]} array contains a value.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    Arrays.asList(array).contains(value)
     * </pre>
     *
     * @param array the {@code String[]} array to check if contains value.
     * @param value the {@code String} to check if contained by array.
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     * @return true if the array contains the value.
     */
    @Simplifier(efficient = false)
    static boolean arrayContains(String[] array, String value) throws ClassCastException {
        //use lambda anyMatch to check if value in array
        return Arrays.asList(array).contains(value);
    }

    /**
     * Find the max value of an {@code int[]} array.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    Arrays.stream(array).max().getAsInt()
     * </pre>
     *
     * @param array the {@code int[]} array to find the max value of.
     * @throws NoSuchElementException if there is no value present.
     * @return the max value as an integer.
     */
    @Simplifier(efficient = false)
    static int maxValue(int[] array) throws NoSuchElementException {
        return Arrays.stream(array).max().getAsInt();
    }

    /**
     * Find the sum of an {@code int[]} array.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    int sum = 0
     *    for (int item : array) {
     *        sum += item
     *    }
     * </pre>
     *
     * @param array the {@code int[]} array to find the sum of.
     * @return the sum as an integer.
     */
    @Simplifier(efficient = true) //get the sum of all elements in an integer array
    static int sumArray(int[] array) {
        int sum = 0;

        //loop through each item and add to the sum
        for (int item : array) {
            sum += item;
        }

        return sum;
    }

    /**
     * Find the sum of an {@code ArrayList<Integer>} ArrayList.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    int sum = 0
     *    for (int item : array) {
     *        sum += item
     *    }
     * </pre>
     *
     * @param array the {@code ArrayList<Integer>} to find the sum of.
     * @return the sum as an integer.
     */
    @Simplifier(efficient = true)
    static int sumArray(ArrayList<Integer> array) {
        int sum = 0;

        //loop through each item and add to the sum
        for (int item : array) {
            sum += item;
        }

        return sum;
    }
}


/** AssadFiles class is used for easy file access.
 * Simply create an instance of the class and
 * specify the file path and then call
 * methods to read and write the file!
 */

class AssadFiles extends UserInput {

    /** Variables set when an instance of AssadFiles is
     * created. The path of the file as a {@code String},
     * the {@code boolean} true if the file exists at the
     * provided path, the {@code String} containing the
     * content in the file, and the {@code ArrayList<String>}
     * containing each index as each line of the file.
     */
    private String path;
    private boolean exists;
    String contentString = "";
    ArrayList<String> contentList = new ArrayList<>();

    //declare file path when instance of AssadFiles created
    AssadFiles(String filePath) {
        //get the current working directory of the project
        String cwd = Paths.get("").toAbsolutePath().toString();

        //get the exact path of the file
        this.path = cwd + "/src/" + filePath;

        //read the file
        readFile();
    }

    /** Read the file when instance created and update
     * the contentString and contentList.
     */
    @Simplifier(efficient = true) //read the file
    private void readFile() {
        // create a new File object
        File textFile = new File(path);

        //create a FileReader object
        FileReader in;

        //create a BufferedReader object
        BufferedReader readFile;

        //keep track of each line in the file
        String lineOfText;

        //attempt to read the file assuming it exists
        try {
            //read the file object
            in = new FileReader(textFile);
            readFile = new BufferedReader(in);

            //loop through each line in the file
            while ((lineOfText = readFile.readLine()) != null) {
                //update the string containing the files content
                contentString += lineOfText + "\n";
                //update the ArrayList containing the content
                contentList.add(lineOfText.trim());
            }

            //close the file and the reader
            readFile.close();
            in.close();
            exists = true;

            //if an error occurs as the file does not exist
        } catch (IOException e) {
            contentString = String.format("File '%s' does not exist.", path);
            exists = false;
        }
    }

    /**
     * Write text {@code String} to the file. Creates the
     * file if {@code !exists}.
     *
     * @param text the {@code String} to write to the file
     */
    @Simplifier(efficient = true) //write a string to file
    void write(String text) {
        //create a new file (or replace old one)
        File dataFile = new File(path);

        //declare the FileWriter and BufferedWriter objects
        FileWriter out;
        BufferedWriter writeFile;

        //attempt to write the text
        try {
            out = new FileWriter(dataFile);
            writeFile = new BufferedWriter(out);

            //write the text to the file
            writeFile.write(text);
            writeFile.newLine();
            writeFile.close();

            //close the file
            out.close();

            //if an error occurs when writing to the file
        } catch (IOException ignored) {}
    }

    /**
     * Write text in the {@code ArrayList<String>} to the file.
     *
     * @param text the {@code ArrayList<String>} of data to add
     * line by line to the file.
     */
    @Simplifier(efficient = true)
    void write(ArrayList<String> text) {
        //create a new file (or replace old one)
        File dataFile = new File(path);

        //declare the FileWriter and BufferedWriter objects
        FileWriter out;
        BufferedWriter writeFile;

        //attempt to write the text
        try {
            out = new FileWriter(dataFile);
            writeFile = new BufferedWriter(out);

            //loop through ArrayList and write each item
            for (String item : text) {
                writeFile.write(item);
                writeFile.newLine();
            }

            writeFile.close();
            out.close();

            //if an error occurs when writing to the file
        } catch (IOException ignored) {}
    }

    /**
     * Append the {@code String} to the file.
     *
     * @param text the {@code String} of data to add to the file.
     */
    @Simplifier(efficient = true)
    void append(String text) throws IOException {
        //if the file does not exist, end program
        if (!exists) {
            print(contentString);
            return;
        }

        //write the file in append mode
        FileWriter fw = new FileWriter(path, true);
        fw.write(text + "\n");
        fw.close();

    }

    /**
     * Append text in the {@code ArrayList<String>} to the file.
     *
     * @param text the {@code ArrayList<String>} of data to add
     * line by line to the end of the file.
     */
    @Simplifier(efficient = true) //append an ArrayList to file
    void append(ArrayList<String> text) throws IOException {
        //if the file does not exist, end  program
        if (!exists) {
            print(contentString);
            return;
        }

        FileWriter fw = new FileWriter(path, true);

        //loop through each item and append to file
        for (String line : text) {
            fw.write(line + "\n");
        }

        fw.close();

    }
}


/* HOW TO USE AssadFunctions:

 public class Main extends AssadFunctions {

    public static void main(String[] args) {
        //call any functions here
    }

 }
 */
@ExtendsAll
public class AssadFunctions extends AssadArrays {

    /**
     * AssadFunctions class should not be run as a main but rather
     * inherited by another class.
     *
     * @throws IOException if run as main and not inherited.
     */
    @Warning(danger = false)
    public static void main(String[] args) throws IOException {
        print("AssadFunctions class should be inherited by another Java main class \uD83D\uDE00");
        throw new IOException();
    }
}
