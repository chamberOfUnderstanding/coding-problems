package string;

/**
 * @author 47un
 *
 * http://www.geeksforgeeks.org/reverse-a-string-using-recursion/
 */
public class Recursive_Reverse {
	public static void main(String...x){
		String input = "abcdefghijklmnopqrstuvwxyz";
		new Recursive_Reverse().reverse(input.toCharArray(), 0);
	}

	// O(n), O(n)
	private void reverse(char[] input, int index) {
		if(index < input.length){
			
			// Scan the next character
			reverse(input,index+1);
			
			// Prints the last character first
			System.out.print(input[index]);
		}
	}
}
