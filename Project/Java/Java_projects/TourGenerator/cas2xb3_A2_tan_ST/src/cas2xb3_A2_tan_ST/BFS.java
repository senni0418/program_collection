package cas2xb3_A2_tan_ST;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS extends BuildObject{

	private boolean[] marked; // array used to mark vertices that has been visited
	private int [] edgeTo; // the last vertex on known path to this vertex
	private final int s; // source
	
	public BFS(EdgeWeightedDigraph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		//initialize the marked array
		for (int i = 0; i < G.V(); i++)
			marked[i] = false;
		bfs(G, s);
	}
	
	private void bfs(EdgeWeightedDigraph G, int s) {
		Queue<Integer> queue = new LinkedList<Integer>();
		marked[s] = true; // mark a vertex as we visit it
		queue.add(s);
		/* while the queue is not empty, we dequeue the queue and store the head v;
		 * for every vertex w adjacent from v, we mark them as visited add vertex
		 * to the path and add w to the queue.
		 */
		while(!queue.isEmpty()) {
			int v = queue.poll();
			for (DirectedEdge e : G.adj(v)) {
				int w = e.to();
				if (!marked[w]) {
					edgeTo[w] = v;
					marked[w] = true;
					queue.add(w);
				}
			}
		}
		
	}
	
	// check if there is a path to v
	private boolean hasPathTo(int v) {
		return marked[v];
	}
	
	// private method that return the path from source to v in a stack
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
