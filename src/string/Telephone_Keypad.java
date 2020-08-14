package string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 47un
 * 
 * Given a combination of key strokes, find out all the words that can be generated on a telephone keypad
 * 
 * If input number is 234, possible words which can be formed are (Alphabetical order):
 * adg adh adi aeg aeh aei afg afh afi bdg bdh bdi beg beh bei bfg bfh bfi cdg cdh cdi ceg ceh cei cfg cfh cfi
 * 
 * =========
 * METHOD 1
 * =========
 * The method gives one key's contribution to the overall word. 
 *  If all keys have been pressed, then print the word and return
 *  Backup the current word
 *  Get possible characters for this key from our keypad
 *  For each possible character
 *   If the current key is not 0 or 1, 
 *    Append a character to the word
 *    Update the word
 *    Recurse for the next key i.e. currentKey+1 with the new word
 *   Restore the backup word
 * 
 * TIME     : O(3^n), O(4^n), O(number of combinations)
 * SPACE    : O(n)
 *
 * 
 */

public class Telephone_Keypad {

    public static void main(String...strings){
        int[] keysPressed = {7, 3, 7, 0, 8};
        findAllWords(keysPressed);
    }

    private static void findAllWords(int[] keysPressed){
        Map<Integer,String> keypad = getKeypad();
        findAllWords(keysPressed, 0, "", keypad);		
    }

    private static void findAllWords(int[] keysPressed, int currentKey, String word, Map<Integer, String> keypad) {
        if(currentKey >= keysPressed.length){
            System.out.println(word);
            return;
        }
        String backup = new String(word);
        String possibleCharacters = keypad.get(keysPressed[currentKey]);
        for(int i = 0; i < possibleCharacters.length(); i++){
            if(keysPressed[currentKey] != 0 && keysPressed[currentKey] != 1)
                word += possibleCharacters.charAt(i);
            findAllWords(keysPressed, currentKey + 1, word, keypad);
            word = backup;
        }
    }

    private static Map<Integer, String> getKeypad() {
        Map<Integer,String> keyMapping = new HashMap<>();
        keyMapping.put(0, " ");
        keyMapping.put(1, " ");
        keyMapping.put(2, "abc");
        keyMapping.put(3, "def");
        keyMapping.put(4, "ghi");
        keyMapping.put(5, "jkl");
        keyMapping.put(6, "mno");
        keyMapping.put(7, "pqrs");
        keyMapping.put(8, "tuv");
        keyMapping.put(9, "wxyz");						
        return keyMapping;
    }	
}