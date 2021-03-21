package string;

/**
 * Given a string S and an integer K, find the lexicographically smallest and largest substrings of length K.
 *
 */
public class Lexicographically_Smallest_Substring {

	public static void main(String[] args) {
		String input1 = "welcometojaxva";
		int k1 = 3;
		String input2 = "ASDFHDSFHsdlfhsdlfLDFHSDLFHsdlfhsdlhkfsdlfLHDFLSDKFHsdfhsdlkfhsdlfhsLFDLSFHSDLFHsdkfhsdkfhsdkfhsdfhsdfjeaDFHSDLFHDFlajfsdlfhsdlfhDSLFHSDLFHdlfhs";
		int k2 = 30;
		System.out.println(getSmallestAndLargest(input1, k1));
		System.out.println(getSmallestAndLargest(input2, k2));
	}

	/**
	 * T O(S.length - K)
	 * S O(1)
	 */
	public static String getSmallestAndLargest(String input, int k) {
		// Return the input itself if K is larger than the string length
		if (k >= input.length())
			return input + "\n" +  input;
		int size = input.length();
		int largest = -1;
		int smallest = size + 1;  

		// Scan till size - k
		for(int i = 0; i < size - k + 1; i++) {
			
			// If largest has not been found, set current index as largest
			if (largest == -1) {
				largest = i;
			} 
			else {
				String currentSubstring = input.substring(i, i + k);
				
				// Compare currentSubstring with the largest substring
				if (currentSubstring.compareTo(input.substring(largest, largest + k)) > 0) {
					
					// If smallest has not been found, set current largest as smallest
					if(smallest == size + 1) {
						smallest = largest;
					}
					
					// Update the largest with current index
					largest = i;
				} 
				
				// If smallest is not set or current substring is smaller than the smallest substring,
				// update smallest
				else if (smallest == size + 1 
						|| currentSubstring.compareTo(input.substring(smallest, smallest + k)) < 0) {
					smallest = i;
				}
			} 
		}   

		return input.substring(smallest, smallest + k) + "\n" +  input.substring(largest, largest + k);
	}
}

