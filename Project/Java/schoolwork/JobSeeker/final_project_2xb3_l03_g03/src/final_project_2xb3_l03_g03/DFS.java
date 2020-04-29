/**
  *  Author: Zihao Du
  *  Revised: March 31, 2020
  *  
  *  Description: A class for DepthFirstSearch in a Graph
 */

package final_project_2xb3_l03_g03;

public class DFS {
	private boolean[] marked;
	private int count = 0;
	/**
	 * @brief Initializes the DFS ADT, using DFS algorithm to detect the Graph from source
	 * @param G The graph we are detecting
	 * @param s The index of the source vertex
	 * @exception IllegalArgumentException When the source vertex doesn't exist in the graph
	 * @detail The constructor calls a private method dfs() recursively to do depth-first search  
	 */
	public DFS(Graph G, int s) {
		if(s < 0 || s >= G.V())	throw new IllegalArgumentException();
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	/**
	 * @brief Depth-First search, go recursively deeper until reaching a sink
	 * @param G The graph
	 * @param v The source vertex 
	 */
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				dfs(G,w);
				count++;
			}
		}
	}
	/**
	 * @brief Determine if a vertex is reachable from source vertex
	 * @param w The index of the destination vertex
	 * @exception IllegalArgumentException When the input is not a legal vertex in the graph
	 * @return True if there's a path between them, false otherwise  
	 */
	public boolean hasPathTo(int w) {
		if (w < 0 || w >= marked.length)	throw new IllegalArgumentException();
		return marked[w];
	}
	/**
	 * @brief Get the number of reachable nodes in total 
	 * @return The number of reachable nodes from the source vertex s  
	 */
	public int count() {
		return count;
	}
}
