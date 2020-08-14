package array.largest;

/**
 * @author 47un
 *
 * Given an unsorted array. Find out the second largest and second smallest values.
 * 
 * http://www.geeksforgeeks.org/second-minimum-element-using-minimum-comparisons/
 * http://www.geeksforgeeks.org/to-find-smallest-and-second-smallest-element-in-an-array/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Initialize the smallest to +infinity and largest to -infinity
 *            Scan the array
 *              If the element is > second largest and is > largest, second largest gets the value of largest
 *              If the element is > second largest and is < largest, second largest gets the value of the element
 *              If the element is < second largest, second largest is unchanged
 *              Now if this element is > largest, update largest to this element *              
 *              Do the same with smallest and second smallest with the comparison operators flipped
 *              
 *              The above logic looks super cute when written using just the conditional operator @ ~ @
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Second_Largest_And_Smallest {
    public static void main(String[] args) {
        int[] array = {2, 500, -97, 4, 4, 8, 7, 7088, 9, 3, -99};
        findSeconds(array);
    }

    private static void findSeconds(int[] array) {
        int smallest = Integer.MAX_VALUE;
        int largest  = Integer.MIN_VALUE;
        int secondSmallest = Integer.MAX_VALUE;
        int secondLargest  = Integer.MIN_VALUE;
        for(int i = 0; i < array.length; i++) {
            int element = array[i];
            if(element < smallest) {
                secondSmallest = smallest;
                smallest = element;
            }
            else if(element > smallest && element < secondSmallest)
                secondSmallest = element;            
            if(element > largest) {
                secondLargest = largest;
                largest = element;
            }
            else if(element < largest && element > secondLargest)
                secondLargest = element;
        }
        System.out.println("Second smallest : " + secondSmallest);
        System.out.println("Second largest : "  + secondLargest);
    }
}
