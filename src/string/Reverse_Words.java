package string;

/**
 * @author 47un
 *
 * Given a sentence. Reverse the sequence of words in the sentence.
 * e.g. "I can't find them" becomes "them can't find I"  
 * 
 * http://www.geeksforgeeks.org/reverse-words-in-a-given-string/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Scan the string in reverse
 *             If the current character is not a whitespace, append it to the word string
 *             If the current character is a whitespace, reverse the word and append it to the result
 *            Append the reverse of last word to the result
 *            
 *            If the sentence has additional whitespace between words and you want to avoid them
 *             Exclude the case where the word is of length 1 in the else part // if(word.length() != 1)
 *              
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Reverse_Words {

    public static void main(String...strings){
        String sentence = "We strongly recommend that you click here and practice it, before moving on to the solution";
        System.out.println(new Reverse_Words().reverseWords(sentence));
    }

    private String reverseWords(String sentence){
        StringBuilder word = new StringBuilder(" ");
        StringBuilder reversedSentence = new StringBuilder();
        for(int index = sentence.length() - 1 ; index >= 0; index--){
            char current = sentence.charAt(index);			
            if(current != ' ')
                word.append(current);
            else{
                reversedSentence.append(word.reverse());
                word = new StringBuilder(" ");
            }            
        }
        reversedSentence.append(word.reverse());
        return reversedSentence.toString();
    }
}
