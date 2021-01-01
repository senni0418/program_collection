package cas2xb3_A2_tan_ST;

import java.util.ArrayList;
import java.util.Stack;

public class DFS extends BuildObject{

	private boolean[] marked;
	private int [] edgeTo;
	private final int s;
	
	public DFS(EdgeWeightedDigraph G, int s) {
		marked = new boolean[G.V()]; // array used to mark vertices that has been visited
		edgeTo = new int[G.V()]; // the last vertenode on known path to this vertenode
		this.s = s; // source
		//initialize the marked array
		for (int i = 0; i < G.V(); i++)
			marked[i] = false;
		dfs(G, s);
	}
	
	private void dfs(EdgeWeightedDigraph G, int v) {
		marked[v] = true; // mark a vertenode as we visit it
		/* for every adjacent vertenode w from v, if w is not marked, set w a vertenode from v
		 by operation on edgeTo[], then recursively run DFS on the adjacent vertices */
		for (DirectedEdge e : G.adj(v)) { 
			int w = e.to();
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G,w);
			}
		}
	}
	
	// check if there is a path to v
	private boolean hasPathTo(int v) {
		return marked[v];
	}
	
	// return the path from source to v in a stack
	private Stack<Integer> pathTo(int v){
		if (!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		for (int node = v; node != s; node = edgeTo[node])
			path.push(node);
		path.push(s);
		return path;
	}
	
	// return the path from source to v in an array list
	public ArrayList<Integer> pathTo_arraylist(int v){
		Stack<Integer> path = this.pathTo(v);
		ArrayList<Integer> p = new ArrayList<Integer>();
		while (!path.isEmpty())
			p.add(path.pop());
		return p;
	}
	
	// convert path to a string for junit tests
	public String pathToString(int v) {
		ArrayList<Integer> path = pathTo_arraylist(v);
		if (path.size() < 1)
			return null;
		if (path.size() == 1)
			return num2city(path.get(0)).name();
		String s = num2city(path.get(0)).name();
		for (int i = 1; i < path.size(); i++) {
			String name = num2city(path.get(i)).name();
			if (i < path.size())
				s = s.concat(", ");
			s = s.concat(name);
		}
		return s;
	}
}
