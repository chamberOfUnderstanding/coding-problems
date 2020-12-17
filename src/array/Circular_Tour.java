package array;

/**
 * @author 47un
 * 
 * Suppose there is a circle. There are n petrol pumps on that circle. You are given two sets of data.
 * 1. The amount of petrol that every petrol pump has.
 * 2. Distance from that petrol pump to the next petrol pump.
 * 
 * Calculate the first point from where a truck will be able to complete the circle (The truck will stop at each petrol pump and it has infinite capacity).
 * Expected time complexity is O(n).
 * Assume for 1 litre petrol, the truck can go 1 unit of distance.
 * 
 * For example, let there be 4 petrol pumps with amount of petrol and distance to next petrol pump value pairs as {4, 6}, {6, 5}, {7, 3} and {4, 5}. The first point from where truck can make a circular tour is 2nd petrol pump. Output should be “start = 1” (index of 2nd petrol pump).
 * 
 * http://www.geeksforgeeks.org/find-a-tour-that-visits-all-stations/
 * 
 * ====================
 * METHOD 1 : Iteration
 * ====================
 * Use a queue or maintain start and end indices for the given array to use it as a queue.
 * 
 * Scan each petrol pump
 *  leftover = petrol qty - dist (whatever is left when reaching pump i)
 *  if leftover < 0 then we cant start there, reset it to 0 and mark start as the next index
 *  this has to be added to the total too (since there is no possible circular tour if the total leftOver petrol is < 0)
 *  
 *    
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Circular_Tour{

	public static void main(String...x){
		PetrolPump[] petrolPumps = new PetrolPump[4];
		petrolPumps[0] = new PetrolPump(4, 6);
		petrolPumps[1] = new PetrolPump(6, 5);
		petrolPumps[2] = new PetrolPump(7, 3);
		petrolPumps[3] = new PetrolPump(4, 5);
		System.out.println(findWhereToStart(petrolPumps));
	}

	private static int findWhereToStart(PetrolPump[] petrolPumps) {
		int start   = 0;
		int total   = 0;
		int current = 0;
		for(int i = 0; i < petrolPumps.length; i++){
			current += petrolPumps[i].petrol - petrolPumps[i].distance;
			if(current < 0) {
				current = 0;
				start = i + 1;
			}
			total += petrolPumps[i].petrol - petrolPumps[i].distance;
		}
		return total < 0? -1 : start;
	}

	public static class PetrolPump{
		int petrol;
		int distance;

		PetrolPump(int petrol, int distance){
			this.petrol   = petrol;
			this.distance = distance;
		}

		@Override
		public String toString() {
			return new String(petrol + " " + distance);
		}
	}
}
