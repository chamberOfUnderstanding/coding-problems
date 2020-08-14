package dynamic_programming;

/**
 * @author 47un
 *
 * Given two sequences, find the length of longest subsequence present in both of them.
 * A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous.
 * For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”.
 * Strings are not necessarily of the same lengths. 
 * So a string of length n has 2^n different possible subsequences.
 * 
 * LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
 * LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
 * http://www.geeksforgeeks.org/printing-longest-common-subsequence/
 *  
 * METHOD 1 : Recursion
 *            Scan from last to first of the strings
 *            Return 0 if index of either string is 0 (Termination clause)
 *            Case 1 If the last two characters of the strings match then lcs = 1 + lcs till the last character (matching characters form a part of the lcs)
 *            Case 2 If the last two dont match then lcs = max(lcs with last of string1 and second last of string2, lcs with last of string2 and second last of string1) 
 *            So for each call
 *             If there is a match, there is one more function call (Case 1) => Best case will be the strings matching fully => O(n)
 *             If there is a mismatch, there are two function calls (Case 2) => Worst case will be strings mismatching fully => O(2^n)
 *             
 * TIME    : O(2^n)  // Strings are of lengths n each
 * SPACE   : O(logn)
 * 
 * METHOD 2 : Dynamic Programming
 *            Bottom Up (0 to n-1)
 *            Similar logic as the recursive one, except the lcs for indices i, j is stored and reused
 *            
 * TIME    : O(n^2) // Strings are of lengths n each
 * SPACE   : O(n^2)
 * 
 * Printing the LCS
 * Scan from last to first
 *  If the characters at last of both strings are equal, add it to the lcs string
 *  Else find out which lcs value is greater, i.e. lcs[i][j-1] or lcs[i-1][j], and move to the greater one
 * Reverse the string and print it
 * 
 * TIME   : O(n)
 * SPACE  : O(1)
 * 
 */
public class Longest_Common_Subsequence {

    public static void main(String[] args) {
        String string1 = "AGGTAB";      
        String string2 = "GXTXAYB";
        System.out.println(lcsRecursive(string1.length(), string2.length(), string1, string2));
        System.out.println(lcsDPBottomUp(string1, string2));
    }

    private static int lcsRecursive(int index1, int index2, String string1, String string2) {
        if(index1 == 0 || index2 == 0)
            return 0;
        if(string1.charAt(index1 - 1) == string2.charAt(index2 - 1))
            return 1 + lcsRecursive(index1 - 1, index2 - 1, string1, string2);
        else
            return Math.max(lcsRecursive(index1, index2 - 1, string1, string2), lcsRecursive(index1 - 1, index2, string1, string2));
    }

    private static int lcsDPBottomUp(String string1, String string2) {
        int length1 = string1.length();
        int length2 = string2.length();             
        int[][] lcs = new int[length1 + 1][length2 + 1];
        for(int i = 0; i <= length1; i++)
            for(int j = 0; j <= length2; j++)
                if(i == 0 || j == 0)
                    lcs[i][j] = 0;
                else if(string1.charAt(i - 1) == string2.charAt(j - 1))
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                else
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
        printLCS(lcs, string1, string2);
        return lcs[length1][length2];
    }

    private static void printLCS(int[][] lcs, String string1, String string2) {
        StringBuilder common = new StringBuilder();
        int i = string1.length();
        int j = string2.length();
        while(i > 0 && j > 0) {
            if(string1.charAt(i - 1) == string2.charAt(j - 1)) {
                common.append(string1.charAt(i - 1));
                i--;
                j--;
            }
            else if(lcs[i][j-1] > lcs[i-1][j])
                j--;
            else
                i--;

        }
        System.out.println("LCS : " + common.reverse().toString());
    }
}