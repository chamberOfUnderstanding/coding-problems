package string;

/**
 * @author 47un
 * 
 * Check if a string is a sub string of another
 * 
 * http://qa.geeksforgeeks.org/3247/write-strstr-function-with-single-without-strcmp-function
 * 
 * =========
 * METHOD 1
 * =========
 * Scan the first string
 * Scan the second string
 * Compare the characters of both string until they dont match and count the matches
 * If matches = length of second string, then string2 is a sub string of string1 
 *  i - length of second string gives the starting index of the match
 * Else repeat for the next character in the first string after resetting j to 0
 * 
 * TIME     : O(n ^ 2)
 * SPACE    : O(1)
 *
 * 
 */

public class Is_Substring {

    public static void main(String[] args) {
        String string1 = "GeeksForGeeks";
        String string2 = "Fr";
        System.out.println(isSubstring(string1, string2));
    }

    private static int isSubstring(String string1, String string2) {
        for(int i = 0; i < string1.length(); ){
            int j = 0;
            int matches = 0;
            while(i != string1.length() && 
                  j != string2.length() &&
                  string1.charAt(i++) == string2.charAt(j++))
                matches++;
            if(matches == string2.length())
              return i - string2.length();
        }
        return -1;
    }
}
