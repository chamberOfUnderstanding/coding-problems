package math;

/**
 * @author 47un
 * 
 * Implement atoi (Converts a valid string to an integer)
 * 
 * http://www.geeksforgeeks.org/write-your-own-atoi/
 * 
 * ========
 * METHOD 1
 * ========
 * String is null or empty => 0
 * Has whitespaces at start/end => trim() it
 * Starts with a sign => Maintain a flag and * with -1 if necessary
 * Scan the string, if any non digit it seen, quit
 * Else find the number
 * If the number is > MAX_VALUE or < MIN_VALUE => bring it back down
 *   To be able to do this, store the number as a double, then return it as an int
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Atoi {

    public static void main(String[] args) {
        String case1 = "1993";
        String case2 = "-224";
        String case3 = "33-2";
        String case4 = "993aaffg";
        String case5 = "+56";
        String case6 = " 999";
        print(case1, atoi(case1));
        print(case2, atoi(case2));
        print(case3, atoi(case3));
        print(case4, atoi(case4));
        print(case5, atoi(case5));
        print(case6, atoi(case6));
    }

    private static Integer atoi(String string)
    {
        if(string == null || string.isEmpty())
            return 0;
        string = string.trim();
        double number = 0;
        int i = 0;
        boolean isNegative = false;
        if(string.charAt(i) == '-') {
            isNegative = true;
            i++;
        }
        else if(string.charAt(i) == '+')
            i++;
        while(i < string.length()) {
            if(!isDigit(string.charAt(i)))
                return null;
            number = number * 10 + string.charAt(i++) - '0';
        }
        number = (isNegative? -1 : 1) * number;
        number = number > Integer.MAX_VALUE ? Integer.MAX_VALUE : number < Integer.MIN_VALUE? Integer.MIN_VALUE : number;            
        return (int)number;
    }

    private static boolean isDigit(char current) {
        return current >= '0' && current <= '9';
    }

    private static void print(String string, Integer number) {
        System.out.println(string + " : " + (number == null? "Invalid input" : number));
    }
}
