//*******************************************************************
//  TESTING ASSADFUNCTIONS
//
// Simplify java functions that require many lines of code.
// RECOMMENDED for new java programmers (some lack efficiency).
//
// author: Will Assad, email: willassadcode@gmail.com
//*******************************************************************

//inherit AssadFunctions
public class Main extends AssadFunctions{

    public static void main(String[] args) {

        //test string input from user
        String var = scanString("Enter string: ");
        //test integer input between 1 and 10
        int x = scanInteger("Enter integer: ",1,10);
        //test double input between 3 and 6
        double y = scanDouble("Enter double: ",3,6);

        //Test format printing
        print("var is %s, x is %s, y is %s.", var,x,y);

        //test remove substring from string
        String someString = "hello there!";
        someString = remove(someString, "there!", "\\s");
        print(someString);

        //test random integer
        int random = randint(20,30);
        print(random);

        //test rounding
        double decimal = 5.93827434;
        double rounded = round(decimal,2);
        print(rounded);

        //check if string is a palindrome
        print(palindrome("never, odd, or even!"));

        //test combining arrays
        int[] array1 = {1,2,3,4};
        int[] array2 = {5,6,7,8};
        print(combineArrays(array1,array2));

        //test arrayContains method
        String[] array = {"hello","bye"};
        print(arrayContains(array, "hello"));
        
    }
}
