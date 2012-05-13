import java.util.ArrayList;

public class BTree {
	public Node root;
	private int[] in ;//in-order list 
	private int[] pre;//pre-order list
	private int preIndex;// short-cut bad implementation for my convenience 
	
	private int[] sortedList;
	
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
	
	//4.3 - build the min-height tree
	public BTree (int[] sorted)
	{
		sortedList = sorted;
		root = buildMinHeightTree(0);
	}
	
	private Node buildMinHeightTree(int index)
	{
		if (index > sortedList.length -1  )
		{
		 return null;	
		}
		
		Node parent = new Node (sortedList[index]);
		parent.left = buildMinHeightTree(index*2 + 1);
		parent.left = buildMinHeightTree(index*2 + 2);
	
		
		return parent;
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
	
	
	//4.1
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
	
	//4.3 
	public ArrayList<ArrayList<Node>> findNodePerLever()
	{
		ArrayList<ArrayList<Node>> result = new ArrayList<ArrayList<Node>>();
		
		if(root == null)
		{
			return result;
		}
		
		int level = 0;
		ArrayList<Node> list = new ArrayList<Node>();//level 0
		list.add(root);
		
		while(true)
		{
			ArrayList<Node> nextLevel = new ArrayList<Node>();
			
			for(int i = 0; i < result.get(level).size() - 1; i++)
			{	
				//grape element from the current level list
				Node left = result.get(level).get(i).left;
				Node right = result.get(level).get(i).right;
				if(left != null)
				{
					nextLevel.add(left);
				}
				
				if(right != null)
				{
					nextLevel.add(right);
				}
			}
			
			//check if next level has no element meaning the current levels are all leave
			if(nextLevel.size() == 0)
			{
				break;
			}
			else{
				result.add(level+1, nextLevel);
			}
			
		}
		
		return result;
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
