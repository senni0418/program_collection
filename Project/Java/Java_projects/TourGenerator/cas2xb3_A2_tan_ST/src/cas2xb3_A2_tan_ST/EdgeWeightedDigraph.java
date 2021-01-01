package cas2xb3_A2_tan_ST;

import java.util.ArrayList;

public class EdgeWeightedDigraph {

	private final int V; // number of vertices
	private int E; // number of edges
	
	// adj is an array storing array lists which represent adjacency lists of edges
	private ArrayList<DirectedEdge>[] adj;
	
	public EdgeWeightedDigraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new ArrayList<DirectedEdge>(); 
	}
	
	public int V() {
		return this.V;
	}
	
	public int E() {
		return this.E;
	}
	
	// add edges to the corresponding vertex
	public void addEdge(DirectedEdge e) {
		adj[e.from()].add(e);
		E++;
	}
	
	// method that returns all adjacent edge of vertex v
	public ArrayList<DirectedEdge> adj(int v){
		return this.adj[v];
	}
	
	// method that returns an array list that contains all edges of the graph
	public Iterable<DirectedEdge> edges(){
		ArrayList<DirectedEdge> edges = new ArrayList<DirectedEdge>();
		for (int v = 0; v < V; v++)
			for (DirectedEdge e : adj[v])
				edges.add(e);
		return edges;
	}
}
