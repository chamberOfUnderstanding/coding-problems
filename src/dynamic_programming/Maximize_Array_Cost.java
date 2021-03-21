package dynamic_programming;

public class Maximize_Array_Cost {
	
	public static void main(String[] args) {
		int[] data = new int[] {3, 15, 4, 12, 10};
		System.out.println(data);
	}

	public static int cost(int[] B) {

		int high_p1 = 0, high_c1 = 0;
		int backup = 0;

		for (int i = 1; i < B.length; i++) {
			
			// Cost if previous element is 1
			int prev_as_1_cost = Math.abs(1 - B[i]);
			// Cost if current element is 1
			int curr_as_1_cost = Math.abs(B[i - 1] - 1);                     

			backup = high_c1;

			// Highest cost with current element as 1
			high_c1  = Math.max(
					high_c1, 
					high_p1 + curr_as_1_cost
					);

			// Highest cost possible with previous as 1                            
			high_p1 = Math.max(
					high_p1, 
					backup + prev_as_1_cost
					);
		}
		return Math.max(high_c1, high_p1);
	}
}