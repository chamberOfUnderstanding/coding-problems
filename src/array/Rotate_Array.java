package array;

/**
 * @author 47un
 * 
 * Given an array of size n, rotate it by d elements. 
 * 
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=360
 * 
 * METHOD 1 : Copy elements!  
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Rotate_Array {

    public static void main(String[] args) {
        int rotations = 3;
        int[] array   = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        printArray(rotate(array, rotations));
    }

    public static int[] rotate(int[] array, int rotations){
        rotations %= array.length;
        int[] rotatedArray = new int[array.length];
        System.arraycopy(array, rotations, rotatedArray, 0, array.length - rotations);
        System.arraycopy(array, 0, rotatedArray, array.length - rotations, rotations);
        return rotatedArray;
    }

    private static void printArray(int[] array) {
        for(int element : array)
            System.out.print(element + " ");
    }
}
