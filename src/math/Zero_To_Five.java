package math;

/**
 * @author 47un
 * 
 * Change all 0s to 5s
 * 
 * 
 * METHOD 1 : Find the number to be added to make the change and add it
 *
 * TIME     : O(d)
 * SPACE    : O(1)
 *
 * 
 */

public class Zero_To_Five {

    public static void main(String[] args) {
        System.out.println(zeroToFive(500050000));
        System.out.println(zeroToFive(123456));
    }

    private static int zeroToFive(int number) {
        int place = 0;
        int backup = number;
        int adder = 0;
        while(backup > 0) {
            if(backup % 10 == 0)
                adder += 5 * Math.pow(10, place);
            place++;
            backup /= 10;
        }
        return number + adder;
    }
}
