package tree;

public class TODO_Postorder_From_Inorder_And_Preorder {

	static int[] inorder =  {4,2,5,1,3,6};
	static int[] preorder = {1,2,4,5,3,6};	

	public static void main(String...strings){
		printPostorder(0, 0, inorder.length-1);
	}

	private static void printPostorder(int preorderIndex, int low, int high) {
		if(low > high)
			return;
		int index = search(low, high, preorder[preorderIndex]);
		int currentIndex = preorderIndex++;		
		printPostorder(preorderIndex, low, index-1);
		printPostorder(preorderIndex, index+1, high);
		System.out.print(preorder[currentIndex] + " ");
	}

	private static int search(int start, int end, int data) {
		for(int i = start; i <= end; i++)
			if(inorder[i] == data)
				return i;
		return -1;
	}
}
