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
 * Scan each petrol pump
 *  Get the left over petrol by subtracting distance from the petrol quantity
 *  Add this value to the petrol total
 *  If current petrol is < 0, then
 *   The start has to be changed to the current pump
 *   And current petrol is now the leftover petrol
 *  Else if the current petrol is >=0 then add the left over petrol to the current petrol
 * After scanning all the pumps, if the total petrol is >= 0 then the truck should start at 'start' else there is no solution
 * 
 * If starting from pump 0, pump 5 was reached and then the truck ran out of petrol
 * Then no need to start from pump 1 as there's no way there can be a cycle if started from 1
 * Next index to start from would be 6 
 *  
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Circular_Tour_Of_Petrol_Pumps{

    public static void main(String...x){
        PetrolPump[] petrolPumps = new PetrolPump[4];
        petrolPumps[0] = new PetrolPump(4, 6);
        petrolPumps[1] = new PetrolPump(6, 5);
        petrolPumps[2] = new PetrolPump(7, 3);
        petrolPumps[3] = new PetrolPump(4, 5);
        System.out.println(findWhereToStart(petrolPumps));
    }

    private static int findWhereToStart(PetrolPump[] petrolPumps) {
        int start         = 0;
        int totalPertrol  = 0;
        int currentPetrol = 0;
        for(int i = 0; i < petrolPumps.length; i++){
            int leftoverPetrol = petrolPumps[i].petrol - petrolPumps[i].distance;
            totalPertrol += leftoverPetrol;
            if(currentPetrol < 0) {
                start = i;
                currentPetrol = leftoverPetrol;
            }
            else
                currentPetrol += leftoverPetrol;
        }
        return totalPertrol < 0? -1 : start;
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
