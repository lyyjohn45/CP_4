import java.util.ArrayList;

public class BTree {
	public Node root;
	private ArrayList<Integer> in = new ArrayList<Integer>();
	private ArrayList<Integer> pre= new ArrayList<Integer>();
	
	public BTree ()
	{
		root = null;
	}
	
	public BTree (int x)
	{
		root = new Node (x);
	}
	
	//construct a tree given inorder and preoder traversal
	public BTree (int[] i, int[] p)
	{
		if(i.length != p.length)
		{
			System.out.println("Not valid input");
		}
		else
		{		
			for(int val : i)
			{
				in.add(val);
			}
			
			for(int val : p)
			{
				pre.add(val);
			}
			
			root = buildTree(0, in.size() - 1 , 0);
		}
	}
	
	//pre keep counts of the in list
	private Node buildTree (int inStart, int inEnd, int preStart)
	{
		if(inStart > inEnd)
		{
			return null;
		}
		
		Node currentNode = new Node(pre.get(preStart));
		preStart++;
		if (inStart == inEnd)
			return currentNode;
		
		int inIndex = findIndex(inStart, inEnd, currentNode.val);
		
		currentNode.left = buildTree (inStart, inIndex - 1, preStart);
		currentNode.right = buildTree (inIndex + 1, inEnd , preStart);
		return currentNode;
	}
	
	public int findIndex (int start, int end, int val)
	{
		for(int i = start; i < end; i++)
		{
			if(in.get(i) == val)
			{
				return i;
			}
		}
		
		return -1;
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
