
public class CP_4 {

	public static void main(String[] args) {
		
		int[] pre = {1, 2, 3};
		int[] in =  {2, 1, 3};
		
		BTree tree = new BTree(in, pre);
		
		tree.print(tree.root);
	}

}