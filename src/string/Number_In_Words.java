package string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 47un
 * 
 * Print a given number in words
 * 
 * =========
 * METHOD 1
 * =========
 * Maintain a map of the possible unique numbers
 * Find the number of digits
 *  If the number of digits is 4, place is 1000 (As 59000 is 59 thousand) 
 * Scan each digit
 *  Break out if the digit is 0
 *  If the number of digits is 4, look up digit (first two digits here) in the map
 *   If found, append to the word (Say 50 thousand, 50 is in the map!)
 *   Else append the map look ups of first digit * 10, then the second digit (59 thousand is fifty nine thousand) 
 *   Append the place to the word
 *   Decrease the digits by 1, again. As we have considered the first two digits here.
 *  If the number of digits is 3 or 2, look up digit, append, append with the place
 *  If the number of digits is 1, look up digit * place
 *  Else append digit
 * Update number and number of digits
 * 
 * TIME     : O(digits)
 * SPACE    : O(1)
 *
 * 
 */

public class Number_In_Words {

    private static Map<Integer, String> map = new HashMap<>();

    public static void main(String[] args) {
        int number1 = 68952;
        int number2 = 8952;
        int number3 = 952;
        int number4 = 717;
        int number5 = 2;
        System.out.println(number1 + " : " + convertToWords(number1));
        System.out.println(number2 + " : " + convertToWords(number2));
        System.out.println(number3 + " : " + convertToWords(number3));
        System.out.println(number4 + " : " + convertToWords(number4));
        System.out.println(number5 + " : " + convertToWords(number5));
    }

    private static String convertToWords(int number) {
        fillMap();
        StringBuilder words;
        int numberOfDigits = findNumberOfDigits(number);
        if(map.containsKey(number)) {
            words = new StringBuilder(map.get(number));
            words.setCharAt(0, Character.toUpperCase(words.charAt(0)));
            return words.toString();
        }
        words = new StringBuilder();
        while(numberOfDigits >= 0) {
            int place = (int) Math.pow(10, numberOfDigits == 4? numberOfDigits - 1 : numberOfDigits);
            int digit = number / place;
            if(digit != 0) {
                switch(numberOfDigits) {
                case 4 :
                    if(map.get(digit) != null)
                        words.append(map.get(digit));
                    else
                        words.append(map.get((digit / 10) * 10)).append(map.get(digit % 10));
                    words.append(map.get(place));
                    numberOfDigits--;
                    break;
                case 3 :
                case 2 :
                    words.append(map.get(digit)).append(map.get(place));
                    break;
                case 1 :
                    if(map.containsKey(number)) {
                        words.append(map.get(number));
                        numberOfDigits = 0;
                        break;
                    }
                    words.append(map.get(digit * place));
                    break;
                case 0 :
                    words.append(map.get(digit));
                }
            }
            number %= place;
            numberOfDigits--;
        }
        words.setCharAt(0, Character.toUpperCase(words.charAt(0)));
        return words.toString();
    }

    private static int findNumberOfDigits(int number) {
        int digits = -1;
        while(number > Math.pow(10, ++digits));
        return digits - 1;
    }

    private static void fillMap() {
        map.put(0, "zero ");   
        map.put(1, "one ");   
        map.put(2, "two ");   
        map.put(3, "three ");   
        map.put(4, "four");   
        map.put(5, "five ");   
        map.put(6, "six ");   
        map.put(7, "seven ");   
        map.put(8, "eight ");   
        map.put(9, "nine ");   
        map.put(10, "ten ");   
        map.put(11, "eleven ");   
        map.put(12, "twelve ");   
        map.put(13, "thirteen ");   
        map.put(14, "fourteen ");   
        map.put(15, "fifteen ");   
        map.put(16, "sixteen ");   
        map.put(17, "seventeen ");   
        map.put(18, "eighteen ");   
        map.put(19, "nineteen ");   
        map.put(20, "twenty ");   
        map.put(30, "thirty ");   
        map.put(40, "forty ");   
        map.put(50, "fifty ");   
        map.put(60, "sixty ");   
        map.put(70, "seventy ");   
        map.put(80, "eighty ");   
        map.put(90, "ninety ");   
        map.put(100, "hundred ");   
        map.put(1000, "thousand ");   
    }
}
