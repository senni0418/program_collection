package cas2xb3_A2_tan_ST;

public class DirectedEdge {

	private final int from;
	private final int to;
	private final double weight;
	
	public DirectedEdge(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	public double weight() {
		return this.weight;
	}
	
	public int from() {
		return this.from;
	}
	
	public int to() {
		return this.to;
	}
	
}
