package string;

/**
 * @author 47un
 *
 * http://www.geeksforgeeks.org/run-length-encoding/
 */

public class Run_Length_Encoding {
	public static void main(String...strings){
		String input = "wwwwaaadexxxxxxz";
		System.out.println(new Run_Length_Encoding().encode(input));
	}

	// O(n) , O(n)
	private String encode(String input){
		StringBuilder encodedString = new StringBuilder("");
		int index = 0, run;
		while(index < input.length()){
			
			// Append a character
			encodedString.append(input.charAt(index));
			
			// Get the run length
			run = 1;
			while(index != input.length()-1 && input.charAt(index+1) == input.charAt(index)){
				index++;
				run++;
			}
			
			// Append the run and move to the next character
			encodedString.append(run);
			index++;
		}	
		return encodedString.toString();
	}
}
