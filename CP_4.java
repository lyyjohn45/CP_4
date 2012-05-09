
public class CP_4 {

	public static void main(String[] args) {
		
		int[] pre = {1, 2, 4, 5, 3, 6};
		int[] in =  {4, 2, 5, 1, 6, 3};
		
		
		//unbalanced
		int[] pre2 = {1, 2, 3};
		int[] in2 = {3, 2, 1};
		
		System.out.println("LOL");
		
		BTree tree = new BTree(in, pre);
		BTree tree2 = new BTree(in2, pre2);
		
		tree.print(tree.root);
		System.out.println();
		System.out.println(tree.ifBalanced(tree.root));
		tree2.print(tree2.root);
		System.out.println();
		System.out.println(tree2.ifBalanced(tree2.root));
	}

}