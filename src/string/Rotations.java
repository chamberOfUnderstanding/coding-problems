package string;

/**
 * @author 47un
 * 
 * http://www.geeksforgeeks.org/a-program-to-check-if-strings-are-rotations-of-each-other-or-not/
 */
public class Rotations {
	public static void main(String...x){
		String string1 = "QWERTYUIOP";
		String string2 = "IOPQWERTYU";
		System.out.println(new Rotations().check(string1, string2) ? "Yes" : "No");
	}

	// Depends on how long String.contains() takes
	// O(1), O(2n) 
	private boolean check(String string1, String string2) {
		
		// Make a string that's like 2x first string
		String string3 = string1 + string1;		
		
		// See if the 2x first string has string2
		return string3.contains(string2);
	}
}
