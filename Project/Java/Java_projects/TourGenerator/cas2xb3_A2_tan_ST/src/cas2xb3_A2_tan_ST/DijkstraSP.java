package cas2xb3_A2_tan_ST;

import java.util.ArrayList;
import java.util.Stack;

// Dijkstra's shortest-paths algorithm
public class DijkstraSP {

	private DirectedEdge[] edgeTo; // the array used to keep track of the path
	private double[] distTo; // the array used to keep track of the distance
	private IndexMinPQ<Double> pq; // the Index Minimum Priority Queue(the lowest distance tree)
	
	
	public DijkstraSP(EdgeWeightedDigraph G, int s) {
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		// initialize the distance from source to other vertices to positive infinity
		for (int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		// initialize the distance from source to source to zero
		distTo[s] = 0.0;
		// relax the non-tree vertex that is lowest distance to the source
		pq.insert(s, 0.0);
		while (!pq.isEmpty())
			relax(G, pq.delMin());
	}
	
	// relax the vertex: check and update the distance(if needed) and update the priority queue
	private void relax(EdgeWeightedDigraph G, int v) {
		for (DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if (pq.contains(w))
					pq.changeKey(w, distTo[w]);
				else
					pq.insert(w, distTo[w]);
			}
		}
	}
	
	// return the shortest path distance from source to vertex v
	public double disTo(int v) {
		return distTo[v];
	}
	
	// detect if there is a path from source to vertex v
	private boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	
	// return the shortest path from source to v in a stack
	private Stack<DirectedEdge> pathTo(int v){
		if (!hasPathTo(v))
			return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
			path.push(e);
		return path;
	}
	
	// return the shortest path from the source to v in an array list
	public ArrayList<Integer> pathTo_InVertex(int v){
		ArrayList<Integer> pathInVertex = new ArrayList<Integer>();
		Stack<DirectedEdge> path = this.pathTo(v);
		DirectedEdge e = path.pop();
		pathInVertex.add(e.from());
		pathInVertex.add(e.to());
		while (!path.isEmpty()) {
			DirectedEdge e2 = path.pop();
			pathInVertex.add(e2.to());
		}
		return pathInVertex;
	}
}
