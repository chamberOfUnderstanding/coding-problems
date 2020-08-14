package tree;

/**
 * @author 47un
 * 
 * Consider a coding system for alphabets to integers where ‘a’ is represented as 1, ‘b’ as 2, .. ‘z’ as 26.
 * Given an array of digits (1 to 9) as input, print all valid interpretations of input array.
 * 
 * http://www.geeksforgeeks.org/find-all-possible-interpretations/
 * 
 * ===============
 * METHOD 1 : Tree
 * ===============
 * For n integers, each of the integers can/cannot be included in the either calculation (left or right). 
 *  So two possibilities for each, hence 2^n
 * If the end of the array has been reached, exit
 * If the integer is 0, proceed to next integer for the same node
 * Get the current word (The children of this node are going to be determined in the coming steps)
 *  Convert the integer to alphabet and append it to the word
 *  This node is the left child of the current node
 *  Resume the operation on this new left node and for the next integer
 * If there are at least two integers remaining AND
 * if the current integer and the next integer make up a value < 26
 *   find the new word by converting the two integers to an alphabet and appending it to the current word
 * This is the right child of current node
 * Advance the integer by 2 and move right
 * 
 * TIME     : O(2^n)
 * SPACE    : O(2^n)
 * 
 * =======================
 * METHOD 2 : Without Tree
 * =======================
 * Same as above, instead the tree is created internally as part of recursion
 * 
 * TIME     : O(2^n)
 * SPACE    : O(2^n)
 *
 */

public class Alphabet_To_Integer_Encoding {

    public static void main(String...strings){
        int[] array = {1, 0, 8, 0, 25};
        System.out.println("Decoding without using a tree : ");
        decodeWithoutUsingTreeWrapper(array);
        System.out.println("\nDecoding using a tree : ");
        decodeWrapper(array);
    }

    private static void decodeWithoutUsingTreeWrapper(int[] array) {
        decodeWithoutUsingTree(0, array, "");
    }

    private static void decodeWithoutUsingTree(int i, int[] array, String word){
        if(i == array.length){
            System.out.print(word + " ");
            return;
        }
        if(array[i] == 0)
            decodeWithoutUsingTree(i + 1, array, word);
        else {
            decodeWithoutUsingTree(i + 1, array, word + getSingleDigitAlphabet(i, array));
            if(i < array.length - 1 && array[i] * 10 + array[i + 1] < 26)
                decodeWithoutUsingTree(i + 2, array, word + getDoubleDigitAlphabet(i, array));
        }
    }

    private static char getSingleDigitAlphabet(int index, int[] array) {
        return (char) (array[index] + 96);
    }

    private static char getDoubleDigitAlphabet(int index, int[] array) {
        return (char) (array[index] * 10 + array[index + 1] + 96);
    }

    private static void decodeWrapper(int[] array) {
        Node root = new Node("");
        decode(0, root, array);
        printLeaves(root);
    }

    private static void decode(int i, Node node, int[] array) {
        if(i == array.length)
            return;
        if(array[i] == 0){
            decode(i + 1, node, array);
            return;
        }
        char alphabet = getSingleDigitAlphabet(i, array);
        node.left = new Node(node.word + alphabet);
        decode(i + 1, node.left, array);
        if(i < array.length - 1 && array[i] * 10 + array[i + 1] < 26) {
            alphabet = getDoubleDigitAlphabet(i, array); 
            node.right = new Node(node.word + alphabet);
            decode(i + 2, node.right, array);	
        }
    }

    private static void printLeaves(Node node){
        if(node == null)
            return;
        if(node.left == null && node.right == null)	{
            System.out.print(node.word + " ");
            return;
        }
        printLeaves(node.left);
        printLeaves(node.right);
    }

    private static class Node{
        String word;
        Node left;
        Node right;

        Node(String word){
            this.word = word;
        }
    }
}
