package dynamic_programming;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 47un
 * 
 * Given different dimensions and unlimited supply of boxes for each dimension, stack boxes
 * on top of each other such that it has maximum height but with caveat that length and width
 * of box on top should be strictly less than length and width of box under it. You can
 * rotate boxes as you like.
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-21-box-stacking-problem/
 * http://people.cs.clemson.edu/~bcdean/dp_practice/
 * 
 * 
 * METHOD 1 :
 * 
 * Create all rotations of the boxes (l,b,h) (b,h,l) (h,l,b)
 * Sort boxes by base area in decreasing order (length * breadth). This is because box with more area will never ever go on top of box with less area.
 * Take lis[] array for the same size and apply longest increasing subsequence type of algorithm to get max height.
 *  Before lis, initialize the lis array with the height values of all boxes. i.e. lis[i] = height of box i
 *  During lis, update the height if the length and breadth of the box on top is <= length and breadth of the box in the bottom 
 *
 * TIME     : O(n^2) // O(nlogn) to sort and O(n^2) for dp
 * SPACE    : O(n)   // 3n as each box gets 3 rotations
 *
 * 
 */

public class Box_Stacking {
    
    public static void main(String[] args) {
        int boxes   = 4;
        int[] input = {4, 6, 7, 1, 2, 3, 4, 5, 6, 10, 12, 32};
        List<Box> dimensions = getDimensions(input, boxes);
        System.out.println(getLIS(dimensions));
    }
    
    private static int getLIS(List<Box> boxes) {
        int[] lis = new int[boxes.size()];
        for(int i = 0; i < boxes.size(); i++)
            lis[i] = boxes.get(i).height;
        for(int bottom = 0; bottom < boxes.size(); bottom++)
            for(int top = bottom+1; top < boxes.size(); top++) {
                Box bottomBox = boxes.get(bottom);                
                Box topBox    = boxes.get(top);                
                if(bottomBox.length > topBox.length && bottomBox.breadth > topBox.breadth)
                    lis[top] = Math.max(lis[top], lis[bottom] + topBox.height);
            }
        int maximumHeight = lis[0];
        for(int height : lis)
            maximumHeight = Math.max(maximumHeight, height);
        return maximumHeight;
    }

    private static List<Box> getDimensions(int[] input, int boxes) {
        List<Box> dimensions = new ArrayList<>();
        for(int i = 0; i < boxes * 3; i += 3) {
            dimensions.add(new Box(input[i], input[i+1], input[i+2]));
            dimensions.add(new Box(input[i+2], input[i], input[i+1]));
            dimensions.add(new Box(input[i+1], input[i+2], input[i]));
        }
        dimensions.sort((d1, d2) -> d1.length * d1.breadth > d2.length * d2.breadth? -1 : 1);
        return dimensions;
    }

    private static class Box{
        int length;
        int breadth;
        int height;

        public Box(int length, int breadth, int height) {
            this.length = length;
            this.breadth = breadth;
            this.height = height;
        }
        
        public String toString() {
            return new String(this.length + " x " + this.breadth + " x " + this.height);
        }
    }
}
