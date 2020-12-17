package array;

/**
 * @author 47un
 * 
 * Given a sorted array which is rotated 'N' times. Find the value of 'N'.
 * 
 * https://practice.geeksforgeeks.org/problems/rotation4723/1
 * 
 * ===========
 * METHOD 1 
 * ===========
 * Loop till current value is greater than the previous value,
 * left rotations = current index
 * right rotations = array length - current index
 * If the array seems sorted, then rotations = 0
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Find_Rotations {

	public static void main(String[] args) {
		test(new int[]{5, 1, 2, 3, 4});
		test(new int[]{1, 2, 3, 4, 5});
	}

	private static void test( int[] input) {
		findRotations(input);
	}    

	public static void findRotations(int[] input){
		int i = 1;
		for( ; i < input.length && input[i] > input[i - 1]; i++);
		System.out.println("Left  : " + i % input.length);
		System.out.println("Right : " + (input.length - i) % input.length);
	}
}
