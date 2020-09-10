/**
  *  Author: Zihao Du
  *  Revised: March 31, 2020
  *  
  *  Description: A class for a undirected unweighed edge graph 
 */
package final_project_2xb3_l03_g03;
import java.util.ArrayList;
/**
 * @brief An ADT that represents a undirected graph
 */
public class Graph {
	private final int V;
	private int E;
	private ArrayList<Integer>[] adj;
	/**
	 * @brief Initializes a Graph object
	 * @param V The number of nodes in total
	 * @exception NegativeArraySizeException When the number of nodes is less than zero
	 * @detail The Graph is made up with adjacency list(ArrayList of ArrayList)  
	 */
	public Graph(int V) {
		if(V < 0)	throw new NegativeArraySizeException();
		this.V = V;
		this.E = 0;
		adj = new ArrayList[V];
		for(int i = 0; i < V; i++) {
			adj[i] = new ArrayList<Integer>();
		}
	}
	/**
	 * @brief Add edges to between two vertices
	 * @param w One of the nodes
	 * @param v The other node
	 * @throws IllegalArgumentException When w or v is not in the range of a normal vertex
	 */
	public void addedge(int w, int v) {
		if(w > V || v > V || w < 0 || v < 0)	throw new IllegalArgumentException();
		adj[w].add(v);
		adj[v].add(w);
		E++;
	}
	/**
	 * @brief Get the number of vertices
	 * @return The number of vertices of the Graph
	 */
	public int V() {
		return this.V;
	}
	/**
	 * @brief Get the number of edges
	 * @return The number of edges in the Graph
	 */
	public int E() {
		return this.E;
	}
	/**
	 * @brief Get the vertices connected to vertex v
	 * @param v The index of the vertex we are interested
	 * @return An ArrayList of integers representing the indices of the neighboring nodes 
	 * @exception IllegalArgumentException When then input is not a legal node of the graph
	 */
	public ArrayList<Integer> adj(int v) {
		if(v < 0 || v > V)	throw new IllegalArgumentException();
		return adj[v];
	}
}
