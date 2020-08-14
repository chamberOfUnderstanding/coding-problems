package string;

/**
 * @author 47un
 * 
 * Count words in a given string
 *  
 * http://www.geeksforgeeks.org/count-words-in-a-given-string/
 * 
 * =========
 * METHOD 1
 * =========
 * Maintain a boolean variable, isWord.
 *  isWord is true if it has spotted a word (i.e. \n or \t)
 *  Initialize isWord to true
 *   If it was set as false, then the output will be one word count less for sentences that do not begin with a ' ' or \t or \n
 *   Even if it is set as true, it is not considered in the count unless a character is seen
 * Scan each letter of the sentence.
 *  If a \n or \t or ' ' is seen, then there's a word, set isWord
 *  If anything else is seen
 *   If isWord is true, then increase count
 *   Reset word to false
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Word_Count {

    public static void main(String...strings){
        String sentence = "The is \n a nonsensical sentence with eight words      ";
        System.out.println(countWords(sentence));
    }

    private static int countWords(String sentence) {
        int count = 0;
        boolean isWord = true;
        for(int i = 0; i < sentence.length(); i++){
            switch(sentence.charAt(i)){
            case '\n' :
            case '\t' :
            case ' '  : 
                isWord = true; 
                break;
            default : 
                if(isWord){
                    count++;
                    isWord = false;
                }				
            }
        }
        return count;
    }
}
