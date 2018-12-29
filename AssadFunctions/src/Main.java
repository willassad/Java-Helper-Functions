//*******************************************************************
//  TESTING ASSADFUNCTIONS
//
// Simplify java functions that require many lines of code.
// RECOMMENDED for new java programmers (some lack efficiency).
//
// author: Will Assad, email: willassadcode@gmail.com
//*******************************************************************

import java.io.IOException;
import java.util.ArrayList;

//inherit AssadFunctions
public class Main extends AssadFunctions{

    private static void dataManipulationTest() {

        //test conversions
        int integer = toInt("2");
        double decimal = toDouble("4.32");
        String intString = toString(54);
        char character = toChar("a");

        String test = "hello";
        char[] strArray = toArray(test);
        char[] intArray = toArray(integer);

        String[] stringInts = {"12","24","36"};
        int[] integers = stringToIntArray(stringInts);

        //test remove substring from string
        String someString = "hello there!";
        someString = remove(someString, "there!", "\\s");
        print(someString);

        //test random integer
        int random = randInt(integers[0],integers[1]);
        print(random);

        //test rounding
        double d = 5.93827434;
        double rounded = round(d,integer);
        print(rounded);

    }

    private static void conditionsTest() {
        //check if value between 2 other values
        int num1 = 2, num2 = 9, num3 = 4;
        print(between(num3,num1,num2));

        //check if string is a palindrome
        String possiblePalindrome = "never, odd, or even!";
        print(palindrome(possiblePalindrome));

        //check if a number is even
        print(isEven(num2));

    }

    private static void printFunctionsTest() {
        //print a blank line
        print();

        //print a formatted string
        int x = 5;
        print("The values of x is %s.", x);

        //print all types of arrays
        print(new double[] {1.23, 4.56, 7.89});
        print(new String[] {"hello", "bye"});
        print(new int[] {1, 2, 3, 4, 5, 6, 7});
        print(new char[] {'a','b','c','d','e'});
        print(new boolean[] {true, false});
        print(new byte[] {1, 2, 3 , 4, 5});
        print(new long[] {1234L,5678L});
        print(new float[] {3.6f, 4.8f});

        //print the type of some data
        int test = 1;
        printType(test);
    }

    private static void userInputTest() {
        String message = "Enter value(s): ";

        //test string input from user
        String var = scanString(message);

        //test integer input between 1 and 10
        int x = scanInteger(message, 1, 10);

        //test double input between 3 and 6
        double y = scanDouble(message, 3, 6);

        //test character input from the user
        char c = scanCharacter(message);

        print("var is %s, x is %s, y is %s, c is %s", var,x,y,c);

        //scan an array of strings of length 4
        String[] strings = scanStringArray(message, 4);
        print(strings);

        //scan an array of integers of length 2
        int[] integers = scanIntegerArray(message, 2);
        print(integers);
    }

    private static void assadArraysTest() {
        //test combining integer arrays
        int[] array1 = {1,2,3,4};
        int[] array2 = {5,6,7,8};
        print(combineArrays(array1,array2));

        //test combining double arrays
        double[] arr1 = {1.2,2.4,3.5,4.1};
        double[] arr2 = {5.2,6.6,7.1,8.9};
        print(combineArrays(arr1,arr2));

        //test combining string arrays
        String[] a1 = {"words","hello"};
        String[] a2 = {"testing", "hey"};
        print(combineArrays(a1, a2));

        //test reversing arrays
        print(reverseArray(array1));
        print(reverseArray(a1));
        print(reverseArray(new char[] {'a'}));

        //test arrayContains method
        String[] array = {"hello","bye"};
        String stringTest = "hello";
        print(arrayContains(array, stringTest));

        Object[] arr = {1, "hey"};
        print(arrayContains(arr, array1[3]));

        int testInteger = 1;
        print(arrayContains(array1, testInteger));

        double testDouble = 2.34;
        print(arrayContains(arr1, testDouble));

        //find the max value in an array
        int[] integers = {1,5,9,3,2};
        print(maxValue(integers));

        //find the sum of an array
        print(sumArray(integers));

        //find the sum of an ArrayList
        ArrayList<Integer> integers1 = new ArrayList<>();
        print(sumArray(integers1));

    }

    private static void assadFilesTest() throws IOException {
        //read the file words.txt
        AssadFiles words = new AssadFiles("files/words.txt");

        //print a string of the content in the file
        print(words.contentString);

        //print an ArrayList of the content in the file
        print(words.contentList);

        //write to a new file
        AssadFiles newFile = new AssadFiles("files/new.txt");
        newFile.write("Hello World!");
        newFile.write(words.contentList);

        //append to a new file
        words.append("another line");
        words.append(words.contentList);
    }


    public static void main(String[] args) throws IOException {
        dataManipulationTest();
        conditionsTest();
        printFunctionsTest();
        userInputTest();
        assadArraysTest();
        assadFilesTest();
    }


}
