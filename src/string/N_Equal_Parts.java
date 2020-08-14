package string;

/**
 * @author 47un
 * 
 * http://www.geeksforgeeks.org/divide-a-string-in-n-equal-parts/
 */
public class N_Equal_Parts {

	public static void main(String...strings){
		String input = "123456789abcdefgh";
		int n = 10;
		new N_Equal_Parts().divide(input, n);
	}

	// O(n) , O(1)
	private void divide(String input, int n){
		for(int index = 0 ; index < input.length() ; index++){
			System.out.print(input.charAt(index));
			
			// Print a separator if the next index is a multiple of n
			if((index+1)%n == 0)
				System.out.print(" ");
		}
	}
}
