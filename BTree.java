public class BTree {
	public Node root;
	private int[] in ;//in-order list 
	private int[] pre;//pre-order list
	private int preIndex;// short-cut bad implementation for my convenience 
	
	
	public BTree ()
	{
		root = null;
	}
	
	public BTree (int x)
	{
		root = new Node (x);
	}
	
	public BTree (int[] in, int[] pre)
	{
		this.in = in;
		this.pre = pre;
		preIndex = 0;
		
		root = buildTree(0, this.in.length - 1);
	}
	
	//leftIn is the left bound of current in-order list
	//rightIn is the right bound of the current in-order list
	private Node buildTree (int leftIn, int rightIn)
	{
		if(preIndex > pre.length -1)
		{
			return null;
		}
		//grape the val from pre-order list, and increment the preIndex;
		int val = pre[preIndex++];
		
		Node currentNode = new Node(val);
		
		if(leftIn == rightIn)
		{
			return currentNode;
		}
		
		//find the first occurrence of the value in the in the given range of order list.
		int inorderIndex = findIndex(leftIn, rightIn, val);
		currentNode.left = buildTree(leftIn, inorderIndex - 1);
		currentNode.right = buildTree(inorderIndex + 1, rightIn);
		
		return currentNode;
	}
	
	private int findIndex (int leftIn, int rightIn, int target)
	{
		for(int i = leftIn; i < rightIn; i++ )
		{
			if (in[i] == target)
			{
				return i;
			}
		}
		
		return -1;
	}
	
	
	public boolean ifBalanced(Node root)
	{
		if(root == null)
		{
			return true;
		}
		int maxHeight = Math.max(ifBalancedHelper(root.left), ifBalancedHelper(root.right));
		int minHeight = Math.min(ifBalancedHelper(root.left), ifBalancedHelper(root.right));
		
		return (maxHeight - minHeight <= 1);
	}
	
	private int ifBalancedHelper(Node root)
	{
		if (root == null)
		{
			return 0;
		}
		
		return 1 + ifBalancedHelper(root.left) + ifBalancedHelper(root.right);
	}

	
	public void print(Node root) 
	{
		if(root == null)
		{
			return;
		}
		
		print(root.left);
		System.out.print(root.val + "->");
		print(root.right);
	}
	
}
