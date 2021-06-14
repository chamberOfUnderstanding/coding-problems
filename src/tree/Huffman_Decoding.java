package tree;

/**
 * @author 47un
 * 
 * 
 * Huffman coding assigns variable length codewords to fixed length input characters based on their frequencies.
 * More frequent characters are assigned shorter codewords and less frequent characters are assigned longer codewords. A Huffman tree is made for the input string and characters are decoded based on their position in the tree. We add a '0' to the codeword when we move left in the binary tree and a '1' when we move right in the binary tree.
 * We assign codes to the leaf nodes which represent the input characters.
 * 
 * For example :
 *         {^,5}
 *      0 /     \ 1
 *     {^,2}   {A,3}
 *    0/   \1
 * {B,1}  {C,1}  
 * 
 * Input characters are only present on the leaves. Internal nodes have a character value ^.
 * Codewords:
 *  A - 1
 *  B - 00
 *  C - 01
 *  
 * No codeword appears as a prefix of any other codeword.
 * Huffman encoding is a prefix free encoding technique.
 * Encoded String "1001011" represents the string "ABACA"
 * Given to the root of the Huffman tree and a binary coded string, decode the string.
 * 
 * =========
 * METHOD 1
 * =========
 * Set the current node as the root
 * Scan the encoded string 
 *  If the character is a 1, move right
 *  Else if the character is a 0, move left
 *  If the node is a leaf node (fully decoded a character)
 *   Append the leaf node data to the output string
 *   Reset the node to the root
 *   
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Huffman_Decoding {

    public static void main(String[] args) {
        Node nodeC = new Node(3, 'c', null, null);
        Node nodeB = new Node(3, 'b', null, null);
        Node nodeA = new Node(5, 'a', null, null);
        Node nodeInternalII  = new Node(0, '^', nodeB, nodeC);
        Node nodeInternalI   = new Node(0, '^', nodeInternalII, nodeA);
        String encodedString = "1001011";
        System.out.println(decode(nodeInternalI, encodedString));
    }
    
    static String decode(Node root, String encodedString) {
        Node node = root;
        StringBuilder decodedString = new StringBuilder();
        for(int i = 0; i < encodedString.length(); i++){
        	// go right if 1 
        	// go left if 0
            node = ( encodedString.charAt(i) == '1' ) ? 
            				node.right 
            				: 
            				node.left;
            // if leaf, include the data in the output
            // reset the node to start from the root
            if(isLeaf(node)) {
                decodedString.append(node.data);
                node = root;
            }
        }
        return new String(decodedString);
    }

    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    @SuppressWarnings("unused")
    private static class Node{
        int frequency;
        char data;
        Node left;
        Node right;

        public Node(int frequency, char data, Node left, Node right) {
            this.frequency = frequency;
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
