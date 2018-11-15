//*******************************************************************
//  TESTING ASSADFUNCTIONS
//
// Simplify java functions that require many lines of code.
// RECOMMENDED for new java programmers (some lack efficiency).
//
// author: Will Assad, email: willassadcode@gmail.com
//*******************************************************************

public class Main {

    public static void main(String[] args) {
        //use to get helper functions
        AssadFunctions f = new AssadFunctions();

        //test string input from user
        String var = f.scanstring("Enter string: ");
        //test integer input between 1 and 10
        int x = f.scaninteger("Enter integer: ",1,10);
        //test double input between 3 and 6
        double y = f.scandouble("Enter double: ",3,6);

        //Test format printing
        f.print("var is %s, x is %s, y is %s.", var,x,y);

        //test random integer
        int random = f.randint(20,30);
        f.print(random);

        //test rounding
        double decimal = 5.93827434;
        double rounded = f.round(decimal,2);
        f.print(rounded);


    }
}
