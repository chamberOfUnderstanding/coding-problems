package array;

/**
 * @author 47un
 *
 * Add 1 to a number represented by an array!
 * 
 * http://codereview.stackexchange.com/questions/43343/add-one-to-a-number-represented-as-an-array-of-digits
 * 
 * ==================== METHOD 1 : Non nines ==================== 
 * Scan the array from LEFT TO RIGHT and find the LAST non nine digit Add 1
 * to the value (if it exists) Set all values after this index to the
 * last index to 0 If all the digits are 9s, then copy this array onto a
 * new array and set the new array's 0 th index as 1
 * 
 * TIME : O(n) SPACE : O(1)
 * 
 * ================ METHOD 2 : Carry ================ 
 * Reverse scan the array Add 1 to the value, find carry, add carry to the rest of the
 * digits If carry is 1 after scanning the entire array Copy the array
 * onto a new array of 1 extra size and set result[0] as carry
 * 
 * TIME : O(n) SPACE : O(1)
 *
 * 
 */

public class Add_One_To_An_Array {

	public static void main(String[] args) {
		test(new int[]{1, 9, 9});
		test(new int[]{7, 9, 9, 0});
		test(new int[]{1, 0, 0, 0, 0});
		test(new int[]{1, 9, 2, 3, 4, 5, 0});
		test(new int[]{9, 9, 9, 9});
	}

	private static int[] addOne_I(int[] number) {
		int nonNineIndex = -1;
		for(int i = 0; i < number.length; i++)
			if(number[i] != 9)
				nonNineIndex = i;
		for(int i = nonNineIndex + 1; i < number.length; i++)
			number[i] = 0;
		if(nonNineIndex == -1){
			int[] result = new int[number.length + 1];
			System.arraycopy(number, 0, result, 1, number.length);
			result[0] = 1;
			return result;
		}            
		else {
			number[nonNineIndex] += 1;
			return number;
		}
	}

	private static int[] addOne_II(int[] number) {
		int carry = 1;      
		for(int i = number.length - 1; i >= 0 && carry == 1; i--) {
			number[i] += carry;
			carry = number[i] / 10;
			number[i] %= 10;
		}
		if(carry == 1) {
			int[] result = new int[number.length + 1];
			System.arraycopy(number, 0, result, 1, number.length);
			result[0] = 1;
			return result;
		}           
		return number;    
	}
	
	private static void test(int[] number1) {
		printArray(addOne_II(number1));
	}

	private static void printArray(int[] number) {
		for(int digit : number)
			System.out.print(digit);
		System.out.println();
	}
}
