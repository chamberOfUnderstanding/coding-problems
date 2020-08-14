package array;

/**
 * @author 47un
 * 
 * Given a sorted array which is rotated 'N' times. Find the value of 'N'.
 * 
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=145
 * 
 * ===========
 * METHOD 1 
 * ===========
 * If the current value is less than the previous value,
 *   then number of left rotations = current index
 *   and number of right rotations = array length - current index
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
	}

	private static void test( int[] input) {
		findRotations(input);
	}    

	public static void findRotations(int[] input){
		int i = 1;
		for( ; i < input.length && input[i] > input[i - 1]; i++);
		System.out.println("Left Rotations  : " + i);
		System.out.println("Right Rotations : " + (input.length - i));
	}
}
