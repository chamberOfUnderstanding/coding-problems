package string;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 47un
 * 
 * I Check if a given string is palindrome
 *  
 * Algorithm
 * =========
 * Attack from both ends, quit if they are unequal
 * 
 * II Check if an anagram of a given string is palindrome
 * 
 * Algorithm
 * =========
 * Count the number of times each character appears. If there is more than one character that occurs odd number of times, quit
 * 
 * III Find the longest palindromic substring
 * http://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
 * http://articles.leetcode.com/longest-palindromic-substring-part-i
 * 
 * Algorithm
 * =========
 * Use dynamic programming. Set memoizer[i][j] to true if the substring from i to j is palindrome
 * memoizer[i][i] is always true (Same character! duh!)
 * Scan for longer palindromes using two loops, if found, set the memoizer for those indices
 * Also keep track of the starting index of the palindrome and the length of it
 * 
 */

public class Palindrome_Problems {

	public static void main(String[] args) {
		String input = "abcdeefggfeedabc";
		Palindrome_Problems palindrome = new Palindrome_Problems();
		System.out.println(palindrome.isPalindrome(input)? "Yes" : "No");
		System.out.println(palindrome.isAnagramPalindrome(input)? "Yes" : "No");
		System.out.println(palindrome.longestPalindromicSequence(input));
	}

	// O(n/2) , O(1)
	private boolean isPalindrome(String input){
		for(int start = 0, end = input.length()-1 ; start <= end ; start++, end--)
			if(input.charAt(start) != input.charAt(end))
				return false;
		return true;
	}

	// O(n) , O(n)
	private boolean isAnagramPalindrome(String input){		
		Map<Character,Integer> characterMap = new HashMap<Character,Integer>();
		for(int i = 0; i < input.length(); i++){
			char current = input.charAt(i);
			characterMap.put(current,characterMap.get(current) == null? 1 : characterMap.get(current) + 1);			
		}
		Set<Character> letters = characterMap.keySet();
		int oddCountCharacters = 0;
		for(char letter : letters){
			if(characterMap.get(letter)%2 != 0)
				oddCountCharacters++;
			if(oddCountCharacters > 1)
				return false;
		}	
		return true;
	}
	
	// O(n^2) , O(n^2)
	private String longestPalindromicSequence(String input){
		int length = input.length();
		int palindromeStart = 0;
		int palindromeLength = 0;
		boolean[][] lookup = new boolean[length][length];
		for(int i = 0; i < length-1; i++){
			lookup[i][i] = true;
			if(input.charAt(i) == input.charAt(i+1)){
				lookup[i][i+1] = true;
				palindromeStart = i;
				palindromeLength = 2;
			}
		}
		lookup[length-1][length-1] = true;
		for(int i = 3; i <= length; i++){
			for(int j = 0; j <= length-i; j++){
				int k = i+j-1;
				if(input.charAt(j) == input.charAt(k) && lookup[j+1][k-1]){
					lookup[j][k] = true;
					palindromeStart = j;
					palindromeLength = i;
				}
			}
		}		
		return input.substring(palindromeStart, palindromeStart+palindromeLength);
	}
}
