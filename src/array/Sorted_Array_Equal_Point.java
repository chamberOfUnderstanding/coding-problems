package array;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 47un
 *
 * Given a sorted array of n size, find whether an element exists in the array from where # smaller elements = # greater elements.
 * If Equal Point appears multiple times in input array, return index of its first occurrence. If doesn�t exist, return -1.
 * 
 * http://www.geeksforgeeks.org/find-equal-or-middle-point-in-a-sorted-array-with-duplicates/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Store indices of unique items on a different array
 *            For each unique item seen
 *             Increase the count and store the index of the item in the unique array
 *             Advance till a new item is seen
 *            Do the same for the last item by comparing it with the second last item
 *            If the count is odd, the equal point is the item in the middle of the unique array
 *            If the count is even, there is no equal point, so -1
 *            
 * TIME     : O(n)
 * SPACE    : O(n)  // If all n items are unique
 *
 * =========
 * METHOD 1
 * ===========
 * Scan the array and count the number of unique items. Use a hashset
 * If the number of unique items is even, then there is no equal point
 * Else scan the array again until unique item count / 2 number of unique items have been seen. Return this index
 * 
 * TIME     : O(n)
 * SPACE    : O(n)  // If all n items are unique
 * 
 */

public class Sorted_Array_Equal_Point {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 4, 5, 6, 6, 6, 7};
        System.out.println(equalPoint(array));
        System.out.println(equalPointII(array));
    }

    private static int equalPoint(int[] array) {
        int[] unique = new int[array.length];
        int count = 0;
        int i = 0;
        while(i != array.length - 1) {
            unique[count++] = i;
            while(i != array.length - 1 && array[i++] == array[i]);         
        }
        if(array[array.length - 1] != array[array.length - 2])
            unique[count++] = array.length - 1;
        return count % 2 == 0? -1 : unique[count / 2];
    }

    private static int equalPointII(int[] array) {
        Set<Integer> hashSet = new HashSet<>();
        for(int i = 0; i < array.length; i++)
            if(!hashSet.contains(array[i]))
                hashSet.add(array[i]);
        int uniqueItems = hashSet.size();
        if(uniqueItems % 2 == 0)
            return -1;
        int count = 0;
        hashSet.clear();
        for(int i = 0; i < array.length; i++)
            if(!hashSet.contains(array[i])) {
                hashSet.add(array[i]);
                if(count == uniqueItems/2)
                    return i;
                count++;
            }
        return -1;
    }
}
