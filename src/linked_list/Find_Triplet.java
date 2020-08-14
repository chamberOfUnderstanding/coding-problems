package linked_list;

/**
 * @author 47un
 *
 * Given three linked lists find a node from each list such that the the triplet gives a sum equal to a given number.
 *  
 * Input : -99 289 4 0 277 , 98 77 6 14 -1 , 33 644 -2 84 5
 *          277 + 98 + 33
 * Output : 277, 98, 33
 * 
 * ==========
 * REFERENCE
 * ============ 
 * http://www.geeksforgeeks.org/find-a-triplet-from-three-linked-lists-with-sum-equal-to-a-given-number/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Sort second list in ascending order and third list in descending order
 *            For each element in first list, scan the second and third lists for a pair
 *             Calculate the sum of three nodes from the lists
 *              If the sum < target sum, advance list2
 *              If the sum > target sum, advance list3
 *              If the sum = target sum, triplet found!
 *             
 * TIME     : O(blogb) + O(clogc) + O(a*max(b,c))  // a, b, c are size of the lists
 *            O(n^2)                               // if a = b = c = n
 * SPACE    : O(max(b,c))
 *
 * 
 */

public class Find_Triplet extends LinkedList_Utils{

    public static void main(String...strings){
        Node first1 = readListFromString("-99 289 4 0 277 -888");
        Node first2 = readListFromString("98 77 6 14 -1");
        Node first3 = readListFromString("33 644 -2 84 5 3 5 4");			
        int targetSum = 643;
        findTripletWrapper(first1, first2, first3, targetSum);
    }

    private static void findTripletWrapper(Node first1, Node first2, Node first3, int targetSum) {
        first2 = mergeSort(first2, true);
        first3 = mergeSort(first3, false);
        findTriplet(first1, first2, first3, targetSum);
    }

    private static void findTriplet(Node first1, Node first2, Node first3, int targetSum) {
        Node unsortedList = first1;
        int detectedSum = 0;
        while(unsortedList != null){
            Node ascendingList = first2;
            Node descendingList = first3;
            while(ascendingList != null && descendingList != null){
                detectedSum = unsortedList.data + ascendingList.data + descendingList.data;
                if(detectedSum == targetSum){
                    System.out.print("\nTriplet is : " + unsortedList.data + ", " + ascendingList.data + ", " + descendingList.data);
                    return;
                }
                else if(detectedSum < targetSum)
                    ascendingList = ascendingList.next;
                else
                    descendingList = descendingList.next;				
            }
            unsortedList = unsortedList.next;
        }
        System.out.print("\nNo triplets were found!");
    }

    private static Node mergeSort(Node first, boolean ascendingOrder) {
        if(first == null || first.next == null)
            return first;
        Node middle = getMiddle(first);
        Node nextOfMiddle = middle.next;
        middle.next = null;
        return merge(mergeSort(first, ascendingOrder), mergeSort(nextOfMiddle, ascendingOrder), ascendingOrder);
    }

    private static Node merge(Node left, Node right, boolean ascendingOrder) {
        Node dummyHead = new Node(-1);
        Node current   = dummyHead;
        while(left != null && right != null) {
            if(left.data <= right.data && ascendingOrder || left.data > right.data && !ascendingOrder) {
                current.next = left;
                left = left.next;
            }
            else{
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }
        current.next = left == null? right : left;
        return dummyHead.next;
    }

    private static Node getMiddle(Node first) {
        if(first == null)
            return null;
        Node middle = first;
        Node fast = first;
        while(fast.next != null && fast.next.next != null){
            middle = middle.next;
            fast = fast.next.next;
        }
        return middle;
    }
}
