/*	
*	Yiyuan Li
*
*   MyGraph class is the representation of a graph.
*   We assume there is no negative cost edges in the graph.
*/

public class Vertex {
	private final String label;   // label attached to this vertex
	private int cost;
	private boolean known;
	public Vertex pre;
	
	/**
	 * Construct a new vertex
	 * Thrown IllegalArgumentException if label is null 
	 * @param label the label attached to this vertex
	 */
	public Vertex(String label) {
		if(label == null)
			throw new IllegalArgumentException("null");
		this.label = label;
		this.cost = Integer.MAX_VALUE;
		this.known = false;
		this.pre = null;
	}

	/*
	 * Set the cost.
	 */
	public int setCost (int cost){
		return this.cost = cost;
	}
	
	/*
	 * Set the cost is know.
	 */
	public boolean setKnown (boolean know){
		return this.known = know;
	}
	
	/*
	 * Return true if the cost is known 
	 * else return false 
	 */
	public boolean isKnown (){
		return this.known;
	}
	
	/*
	 * Return the cost of the label 
	 */
	public int getCost(){
		return this.cost;
	}
	
	/*
	 * A string representation of this object
	 * Return the previous label
	 */
	public String getPre() {
		if (this.pre == null){
			return "null";
		}else{
			return pre.toString();
		}
	}
	
	/**
	 * Get a vertex label
	 * @return the label attached to this vertex
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * A string representation of this object
	 * @return the label attached to this vertex
	 */
	public String toString() {
		return label;
	}


	//auto-generated: hashes on label
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	//auto-generated: compares labels
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Vertex other = (Vertex) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}


}
