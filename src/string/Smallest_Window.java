package string;

/***
 * @author 47un
 * 
 * http://www.geeksforgeeks.org/find-the-smallest-window-in-a-string-containing-all-characters-of-another-string/
 *
 * http://articles.leetcode.com/finding-minimum-window-in-s-which/#comment-511216
 * 
 * Algorithm
 * """""""""
 * The idea is to maintain 2 arrays of size 255. 
 *	'need' tracks the count of each character that is needed to form a valid window
 * 	'have' tracks the count of each character that has been found(included) in our window
 * Now scan string1 and fill the 'need' array
 * Scan string2, maintain 'begin' and 'end'. They denote the starting index and ending index of our window.
 * Get the 'first' and 'last' characters in the window.
 * If the last character is not needed (need[character]=0) move to the next character
 * Else if you have one more of the last character, increase have[last]. If you still need more of such characters, update length
 * If length is equal to length1 (length of the string that is contained), we can start to manage our window.
 * 	If the first character in the window is not needed or if we have more than what we want of the first character, decrease the count and move to the next character
 * If the window size has dropped, update the window size
 */

public class Smallest_Window {
	public static void main(String...strings){
		String string1 = "tstri";
		String string2 = "this is a test string";
		new Smallest_Window().findSmallestWindow(string1,string2);
	}

	// O(n), O(1)
	private boolean findSmallestWindow(String string1, String string2) {

		int smallestWindowLength = Integer.MAX_VALUE;
		int smallestWindowBegin = Integer.MAX_VALUE;
		int smallestWindowEnd = Integer.MAX_VALUE;
		int length1 = string1.length();
		int length2 = string2.length();		

		int[] haveSeen = new int[255];
		int[] needToSee = new int[255];
		int seenLength = 0;

		for(int index = 0; index < length1; index++)
			needToSee[string1.charAt(index)]++;

		for(int windowStart = 0, windowEnd = 0; windowEnd < length2; windowEnd++){

			char firstCharacter = string2.charAt(windowStart);
			char lastCharacter = string2.charAt(windowEnd);			

			if(needToSee[lastCharacter] != 0){
			
				if(++haveSeen[lastCharacter] <= needToSee[lastCharacter])
					seenLength++;
					
				if(seenLength == length1){
					while(needToSee[firstCharacter] == 0 || haveSeen[firstCharacter] > needToSee[firstCharacter]){
						if(haveSeen[firstCharacter] > needToSee[firstCharacter])
							haveSeen[firstCharacter]--;
						firstCharacter = string2.charAt(++windowStart);					
					}

					int windowLength = windowEnd - windowStart + 1;
					if(windowLength < smallestWindowLength){
						smallestWindowBegin = windowStart;
						smallestWindowEnd = windowEnd;
						smallestWindowLength = windowLength;
						System.out.println(windowStart + " " + windowEnd + " " + string2.substring(windowStart, windowEnd+1));
					}
				}			
			}
		}
		return seenLength == length1;
	}
}
