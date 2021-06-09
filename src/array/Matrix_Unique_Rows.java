package array;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 47un
 * 
 * Given a binary matrix, print all unique rows of the given matrix.
 * 
 * http://www.geeksforgeeks.org/print-unique-rows/
 * 
 * ===============================
 * METHOD 1 : Decimal Values, Sets
 * ===============================
 * Maintain a set that records the decimal values of each row.
 * Scan each row and generate a decimal value out of its contents.
 *  The powers of 2 are stored in the array, powersOfTwo, to avoid re calculation.
 *  Only the necessary powers (Cells with 1) are calculated. Cells with 0 are completely ignored.
 * If the decimal set DOES NOT contain this decimal value
 *  Update the decimal set with the new value
 *  Print this row of the matrix 
 * 
 * TIME     : O(m * n)
 * SPACE    : O(m * n)  // If they are all unique
 *
 * ===============
 * METHOD 2 : Trie
 * ===============
 * Scan each row
 *  Insert the row into a Trie
 *   Set current node as root
 *   Maintain a flag to check insertion status
 *   Scan the items in the row
 *    If the item appears as a child of the current node, fetch the child node and update the current node
 *    Else, (This row is not present in the Trie)
 *     Prepare a node of this item, insert it as a child of current node, update the current node to new node
 *     Set insertion status as true 
 *  If insertion was successful, print the row 
 * 
 * TIME     : O(m * n)
 * SPACE    : O(m * n)
 * 
 */

public class Matrix_Unique_Rows {

    public static void main(String[] args) {
        int rows    = 11;
        int columns = 4;
        int[][] matrix = {
                {0, 1, 1, 0}, 
                {0, 1, 1, 0}, 
                {0, 1, 1, 0}, 
                {0, 1, 1, 0}, 
                {1, 1, 1, 1}, 
                {1, 1, 0, 1}, 
                {1, 1, 0, 1}, 
                {1, 1, 0, 1}, 
                {1, 1, 0, 1},
                {0, 0, 0, 0},
                {1, 1, 1, 1}};
        findUniqueRows(matrix, rows, columns);
        System.out.println();
        findUniqueRowsUsingTrie(matrix, rows, columns);
    }


    // DECIMAL APPROACH
    private static void findUniqueRows(int[][] matrix, int rows, int columns) {
        Set<Integer> decimals   = new HashSet<>();
        int[] powersOfTwo       = new int[columns];
        for(int[] row : matrix) {
            int decimal = generateDecimalValue(row, columns, powersOfTwo);
            if(!decimals.contains(decimal)) {
                decimals.add(decimal);
                printRow(row);
            }
        }
    }

    private static int generateDecimalValue(int[] row, int columns, int[] powersOfTwo) {
        int decimal = 0;
        for(int i = 0; i < columns; i++)
            if(row[i] == 1) {
                int exponent = columns - i - 1;
                if(powersOfTwo[exponent] == 0)
                    powersOfTwo[exponent] = (int) Math.pow(2, exponent);   
                decimal += powersOfTwo[exponent];
            }
        return decimal;
    }

    // TRIE APPROACH
    private static void findUniqueRowsUsingTrie(int[][] matrix, int rows, int columns) {
        Node root = new Node(-1);        
        for(int i = 0; i < rows; i++)
            if(insertRowIntoTrie(matrix[i], root))
                printRow(matrix[i]);
    }

    private static boolean insertRowIntoTrie(int[] row, Node root) {
        Node node = root;
        boolean inserted = false;
        for(int i : row)
            if(node.children[i] != null)
                node = node.children[i];
            else {
                Node newNode = new Node(i);
                node.children[i] = newNode;
                node = newNode;
                inserted = true;
            }
        return inserted;
    }

    @SuppressWarnings("unused")
    private static class Node{
        int data;
        Node[] children = new Node[2];

        Node(int data){
            this.data = data;
        }
    }

    private static void printRow(int[] row) {
        for(int i : row)
            System.out.print(i + " ");
        System.out.println();
    }
}
