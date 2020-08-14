package string;

/**
 * @author 47un
 * 
 * Given a string, convert it to the equivalent numbers (key strokes) on a Telephone keypad
 * 
 * =========
 * METHOD 1
 * =========
 * For each character in the string
 *  Get the key
 *  Append it to the result
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Telephone_Keypad_Reverse {

    public static void main(String...args){
        String word1 = "Douche";			
        System.out.println(findKeyStrokes(word1));
    }

    private static String findKeyStrokes(String string){
        StringBuilder keyStrokes = new StringBuilder();
        for(int i = 0; i < string.length(); i++)
            keyStrokes.append(findKey(Character.toLowerCase(string.charAt(i))));
        return keyStrokes.toString();
    }

    private static int findKey(char alphabet){
        switch(alphabet){
        case 'a':
        case 'b':
        case 'c': return 2;
        case 'd':
        case 'e':
        case 'f': return 3;
        case 'g':
        case 'h':
        case 'i': return 4;
        case 'j':
        case 'k':
        case 'l': return 5;
        case 'm':
        case 'n':
        case 'o': return 6;
        case 'p':
        case 'q':
        case 'r':
        case 's': return 7;
        case 't':
        case 'u':
        case 'v': return 8;
        case 'w':
        case 'x':
        case 'y':		
        case 'z': return 9;
        default : return 0;
        }
    }
}
