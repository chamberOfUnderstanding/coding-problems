package tree.special;

/**
 * @author 47un
 * 
 * Construct a segment tree for a given array 
 * 
 * https://visualgo.net/segmenttree
 * 
 * Leaves contain the actual array elements.
 * Parent node contains the sum of values of the children.
 * Each node has interval information through 'start' and 'end' fields
 * Data held by each node is the sum of the array elements that fall within that interval
 * Typical left and right children are there too
 * 
 * Construction
 * ============
 * The logic is to break the array into segments, get the sum, make a node out of it and pass the information to the parents
 * So the construction starts off with the entire array as one segment (0 to n-1)
 * If low > high, segments cross, so exit!
 * Make a node with the values of start and end as low and high respectively (Interval information)
 * If low == high, then this is a leaf node i.e. value same as the value at array[low]
 *  So set the node's sum as array[low]
 * Else recurse and set values for the kids and the node value.
 *  Segment the tree at the middle
 *  This node's left child is the segment tree formed by the interval from low to middle
 *  And this node's right child is the segment tree formed by the interval from middle + 1 to high
 *  Now that the children are ready, this node's value = sum of values of the kids
 * Return the node
 * 
 * Range Sum
 * =========
 * Given a range, low to high, looking up the segment tree should give the sum of the elements from low to high in the original array
 * If the node is null, return 0
 * If this node's start and end are the same as low and high (Interval exactly matches), return node.sum
 * No? Quickly! We must contact our children and get their data
 *  Find the middle of the interval represented by this node
 *  If the high is <= middle, the interval is before this one, so go left
 *   Since low to middle is on the left and middle + 1 to high is on the right, if the interval before or at middle, obv gotta move left  
 *  If the low is > middle + 1, the interval is after this one, so go right
 *   Kinda like the above reason
 *  Else sum low to mid and mid + 1 to high
 *   This means that values of both lst and rst are needed
 *   e.g. Root is 0,5
 *        Interval to be searched 2,4
 *        which is 2,2 (Left of 0,5) + (3,4) (Right of 0,5)
 * 
 * TIME     : O(n), O(logn)
 * SPACE    : O(logn)
 *
 * 
 */

public class Segment_Tree {

    private static class Node{
        int start;
        int end;
        int sum;
        Node left;
        Node right;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
	public static void main(String[] args) {
		int[] array = {1, 3, 5, 7, 9, 11};
		
		Node root = constructSegmentTree(array);
		traverse(root);
		System.out.println();
		System.out.println(rangeSum(2, 4, root));
	}

	private static Node constructSegmentTree(int[] array) {
		return constructSegmentTree(0, array.length - 1, array);
	}

	private static Node constructSegmentTree(int low, int high, int[] array) {
		if(low > high)
			return null;
		Node node = new Node(low, high);
		if(low == high)
			node.sum = array[low];
		else {
			int mid = (low + high) >> 1;
			node.left = constructSegmentTree(low, mid, array);
			node.right = constructSegmentTree(mid + 1, high, array);
			node.sum = node.left.sum + node.right.sum;					
		}
		return node;
	}

	private static int rangeSum(int low, int high, Node node) {
		if(node == null)
			return 0;
		if(node.start == low && node.end == high)
			return node.sum;
		int mid = (node.start + node.end) >> 1;
		if(high <= mid)
			return rangeSum(low, high, node.left);
		else if(low >= mid + 1)
			return rangeSum(low, high, node.right);
		else
			return rangeSum(low, mid, node.left) + rangeSum(mid + 1, high, node.right);
	}

    private static void traverse(Node node) {
        if(node != null) {
            traverse(node.left);
            System.out.print(node.sum + " ");
            traverse(node.right);
        }
    }
}