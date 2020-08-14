package tree.special;

/**
 * @author 47un
 * 
 * Evaluate an expression tree
 * 
 * http://www.geeksforgeeks.org/evaluation-of-expression-tree/
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * If the node is a leaf (operand), return it (Termination clause)
 * If the node has a left and no right (Happens for signed numbers), get the left data and multiply it with the proper sign and return
 * Else if the node is an operator, recurse and get the results for LST and RST and operate on the results
 * 
 * TIME     : O(n)
 * SPACE    : O(h)
 *
 * 
 */

public class Expression_Tree {

    public static void main(String[] args) {
        Node node5 = new Node(null, '5', null);
        Node node7 = new Node(null, '7', null);
        Node node8 = new Node(null, '8', null);
        Node node4 = new Node(null, '4', null);
        Node node2 = new Node(null, '2', null);
        Node nodeExponent = new Node(node4, '^', node2);
        Node nodeSubtract = new Node(node8, '-', null);
        Node nodeAdd      = new Node(node5, '+', node7);
        Node nodeDivide   = new Node(nodeAdd, '/', nodeSubtract);
        Node nodeMultiply = new Node(nodeDivide, '*', nodeExponent);
        Node root = nodeMultiply;
        
        System.out.println(evaluateExpression(root));       
    }

    private static int evaluateExpression(Node node) {
        int result = 0;
        if(node != null) {
            if(isLeaf(node))
                result = Character.getNumericValue(node.data);
            else if(node.left != null && node.right == null)
                result = (node.data == '-'? -1 : 1) * Character.getNumericValue(node.left.data);
            else {
                switch(node.data) {
                    case '+' : result = evaluateExpression(node.left) + evaluateExpression(node.right); break;
                    case '-' : result = evaluateExpression(node.left) - evaluateExpression(node.right); break;
                    case '*' : result = evaluateExpression(node.left) * evaluateExpression(node.right); break;
                    case '/' : result = evaluateExpression(node.left) / evaluateExpression(node.right); break;
                    case '^' : result = (int) Math.pow(evaluateExpression(node.left), evaluateExpression(node.right)); break;
                }
            }
        }
        return result;
    }

    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    private static class Node{
        char data;
        Node left;
        Node right;

        public Node(Node left, char data, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "" + this.data;
        }
    }
}
