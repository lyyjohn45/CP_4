import java.util.ArrayList;


public class CP_4 {

	public static void main(String[] args) {
		
		/*int[] pre = {1, 2, 4, 5, 3, 6};
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
		System.out.println(tree2.ifBalanced(tree2.root));*/
		
		ArrayList<Edge> edges = new ArrayList<Edge>();
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		
		Vertex a = new Vertex("A"); 
		Vertex b = new Vertex("B"); 
		Vertex c = new Vertex("C"); 
		Vertex d = new Vertex("D"); 
		Vertex e = new Vertex("E"); 
		
		vertices.add(a);
		vertices.add(b);
		vertices.add(c);
		vertices.add(d);
		vertices.add(e);
		
		Edge ab = new Edge(a, b, 1);
		Edge ac = new Edge(a, c, 1);
		Edge ce = new Edge(c, e, 1);
		Edge cd = new Edge(c, d, 1);
		Edge db = new Edge(d, b, 1);
		
		edges.add(ab);
		edges.add(ac);
		edges.add(ce);
		edges.add(cd);
		edges.add(db);
		
		MyGraph graph = new MyGraph(vertices, edges);
		
		System.out.println(graph.isReachable(a, d));//true
		System.out.println(graph.isReachable(e, a));//false
	}

}