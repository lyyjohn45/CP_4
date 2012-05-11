/*	
*	Yiyuan Li
*
*   MyGraph class is the representation of a graph.
*   We assume there is no negative cost edges in the graph.
*/

import java.util.*;

public class MyGraph implements Graph {
    
    private ArrayList<Vertex> myVertices;	// the vertices in this graph
    private ArrayList<Edge> myEdges;	// the edges in this graph
    
    /**
    * Creates a MyGraph object with the given collection of vertices
    * and the given collection of edges.
    * @param v a collection of the vertices in this graph
    * @param e a collection of the edges in this graph
    */
    public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
        myVertices = new ArrayList<Vertex>();
        myEdges = new ArrayList<Edge>();
        
        myVertices.addAll(v);
        myEdges.addAll(e);
    }
    
    /**
    * Return the collection of vertices of this graph
    * @return the vertices as a collection (which is anything iterable)
    */
    
    public Collection<Vertex> vertices() {
        return myVertices;
    }
    
    /**
    * Return the collection of edges of this graph
    * @return the edges as a collection (which is anything iterable)
    */
    public Collection<Edge> edges() {
        return myEdges;
    }
    
    public boolean isReachable(Vertex s, Vertex t)
    {
    	Stack<Vertex> pending = new Stack<Vertex>();
    	int indexOfStr = myVertices.indexOf(s);
    	myVertices.get(indexOfStr).setKnown(true); //set the beginning vertex as know
    	
    	pending.add(myVertices.get(indexOfStr));//add this to the pending list
    	
    	while(!pending.isEmpty())
    	{
    		Vertex current = pending.pop();
    		ArrayList<Vertex> adjacent = this.adjacentVertices(current);
    		
    		for(Vertex child : adjacent)
    		{
    			// if the child in adjacent vetexes equals t by label, then we find it
    			if(child.getLabel() == t.getLabel())
    			{
    				return true;
    			}
    			
    			if(!child.isKnown())
    			{
    				child.setKnown(true);
    				pending.add(child);
    			}
    		}
    	}
    	
    	return false;
    }
    
    /**
    * Return a collection of vertices adjacent to a given vertex v.
    *   i.e., the set of all vertices w where edges v -> w exist in the graph.
    * Return an empty collection if there are no adjacent vertices.
    * @param v one of the vertices in the graph
    * @return an iterable collection of vertices adjacent to v in the graph
    * @throws IllegalArgumentException if v does not exist.
    */
    public ArrayList<Vertex> adjacentVertices(Vertex v) {
        if (!myVertices.contains(v)){
            throw new IllegalArgumentException();
        }
        
        ArrayList<Vertex> adVertices = new ArrayList <Vertex>();
        
        for(int i = 0; i < myEdges.size(); i++){
            Edge current = myEdges.get(i);
            if(v.equals(current.getFrom())){
                adVertices.add(current.getTo());
            }
        }
        return adVertices;
    }
    
    /**
    * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph.
    * Assumes that we do not have negative cost edges in the graph.
    * @param a one vertex
    * @param b another vertex
    * @return cost of edge if there is a directed edge from a to b in the graph,
    * return -1 otherwise.
    * @throws IllegalArgumentException if a or b do not exist.
    */
    public int isAdjacent(Vertex a, Vertex b) {
        if (!(myVertices.contains(a)&&(myVertices.contains(b)))){
            throw new IllegalArgumentException();
        }
        Edge targetEdge = new Edge (a , b, 0); //set a dummy weight
        int exist = myEdges.indexOf(targetEdge);
        if(exist == -1){
            return exist;
        }else{
            return myEdges.get(exist).w;
        }
    }
    
    /**
    * Returns the shortest path from a to b in the graph.  Assumes positive
    * edge weights.  Uses Dijkstra's algorithm.
    * @param a the starting vertex
    * @param b the destination vertex
    * @param path a list in which the path will be stored, in order, the first
    * being the start vertex and the last being the destination vertex.  The
    * list will be empty if no such path exists.  NOTE: the list will be
    * cleared of any previous data.  path is not expected to contain any data
    * needed by the method when the method is called.  It is used to allow
    * us to return multiple values from the function.
    //* @return the length of the shortest path from a to b, -1 if no such path
    * exists.
    * @throws IllegalArgumentException if a or b does not exist.
    */
    public int shortestPath(Vertex start, Vertex end, List<Vertex> path) {
        if (!(myVertices.contains(end)&&(myVertices.contains(end)))){
            throw new IllegalArgumentException();
        }
        
        //remove everything in the path!
        path.removeAll(path);
        //Initially set all the vertices with cost of infinity
        for(int i = 0; i < myVertices.size() ; i++){
            myVertices.get(i).setCost(Integer.MAX_VALUE);
            myVertices.get(i).setKnown(false);
        }
        
        //set the start element's cost to zero
        int startIndex = myVertices.indexOf(start);
        int endIndex = myVertices.indexOf(end);
        
        if(startIndex == endIndex){
            path.add(myVertices.get(startIndex));
            return 1;
        }
        
        //set the start vertex's cost to zero
        myVertices.get(startIndex).setCost(0);    
        while(!allKnown()){         
            Vertex min = minUnknown(start); //find unknown node with smallest cost
            min.setKnown(true);
            
            //found the shortest path already, return the path
            if(myVertices.get(endIndex).isKnown())//(min.equals(end))
            {
                if(myVertices.get(endIndex).getCost() < (-2140000000) || myVertices.get(endIndex).getCost() > 2140000000){
                    return -1;
                }

                path.add(min);
                while(!min.pre.equals(start)){
                    path.add(min.pre);
                    min = min.pre;
                }
                path.add(start);
                
                Collections.reverse(path);
                return myVertices.get(endIndex).getCost();
            }
            
            ArrayList<Vertex> adjacent = adjacentVertices(min);
            //for each edge ad that connected with min, as (min,ad)
            for(int i = 0; i < adjacent.size();i++){
                Vertex ad = adjacent.get(i);
                if(!ad.isKnown()){
                	//found the the ad Vertex in myVertex
                	int adIndex = myVertices.indexOf(ad);       
                    int cost1 = min.getCost() + isAdjacent(min,myVertices.get(adIndex));
                    int cost2 = myVertices.get(adIndex).getCost();   
                    if(cost1 < cost2){
                        myVertices.get(adIndex).setCost(cost1);
                        myVertices.get(adIndex).pre = min;                     
                    }
                }
            }
            
            
        }
        
        return -1;
    }
    
    /*
    * allKnown method return true, if all the vertices are known;
    * else return false.
    */
    private boolean allKnown(){
        for(int i = 0; i < myVertices.size() ; i++){
            if(!myVertices.get(i).isKnown()){
                return false;
            }
        }
        return true;
    }
    
    
    /*
    * minUnknow method is a help method that find the minimum path.
    * This method return the vertex with the smallest cost among unknown vertices.
    */
    private Vertex minUnknown(Vertex start){
        Vertex smallest = null;
        for(int i = 0; i < myVertices.size(); i++){
            Vertex current = myVertices.get(i);
            if(!current.isKnown()){
                if (smallest == null){
                    smallest = current;
                }
                if (current.getCost()<smallest.getCost()){
                    smallest = current;
                }
            }
        }
        return smallest;
    }
    
}
