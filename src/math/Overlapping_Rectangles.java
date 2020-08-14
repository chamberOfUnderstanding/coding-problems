package math;

/**
 * @author 47un
 * 
 * Given two rectangles, find if the given two rectangles overlap or not.
 * A rectangle is denoted by providing the x and y co-ordinates of two points: the left top corner and the right bottom corner of the rectangle.
 * Note that two rectangles sharing a side are considered overlapping.
 *
 * http://www.geeksforgeeks.org/find-two-rectangles-overlap/
 *
 * METHOD 1 : If x of one lies between the y s of the other or y does so with the x s, true 
 *
 * TIME     : O(1)
 * SPACE    : O(1)
 *
 * 
 */

public class Overlapping_Rectangles {

    public static void main(String[] args) {
        Rectangle rectangle2 = new Rectangle(0, 0, 15, 19);
        Rectangle rectangle1 = new Rectangle(3, 3, 4, 4);
        System.out.println(doTheyOverlap(rectangle1, rectangle2) || doTheyOverlap(rectangle2, rectangle1));
    }

    private static boolean doTheyOverlap(Rectangle rectangle1, Rectangle rectangle2) {
        return isInBetween(rectangle1.x1, rectangle2.y1, rectangle1.x2) || 
                isInBetween(rectangle1.y1, rectangle2.x1, rectangle1.y2);
    }

    private static boolean isInBetween(int x, int y, int z) {
        return x <= y && y <= z;
    }

    private static class Rectangle{
        int x1;
        int y1;
        int x2;
        int y2;

        public Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}
