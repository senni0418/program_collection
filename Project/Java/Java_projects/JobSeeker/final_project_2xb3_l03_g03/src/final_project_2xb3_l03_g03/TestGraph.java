/**
  *  Author: Zihao Du
  *  Revised: April 05, 2020
  *  
  *  Description: Test the Graph class 
 */

package final_project_2xb3_l03_g03;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class TestGraph {
	private Graph G;
	private Graph g;
	@Before 
	/**
	 * @brief Set up two graphs for testing
	 */
	public void setUp() {
		G = new Graph(7);
		G.addedge(0,1);
		G.addedge(0,2);
		G.addedge(1,2);
		G.addedge(1,5);
		G.addedge(3,4);
		g = new Graph(0);
	}
	@After
	/**
	 * @brief Free the two graphs
	 */
	public void teardown() {
		G = null;
		g = null;
	}
	@Test
	/**
	 * @brief Test the constructor, edge case
	 */
	public void Testgraph() {
		assertTrue(g.V() == 0);
	}
	@Test
	/**
	 * @brief Test the method V(), normal case
	 */
	public void TestV1() {
		assertTrue(G.V() == 7);
	}
	/**
	 * @brief Test the constructor when there is an exception 
	 */
	@Test (expected = NegativeArraySizeException.class)
	public void Testgraphabnormal() {
		Graph g3 = new Graph(-1);
	}
	@Test
	/**
	 * @brief Test the method E(), normal case
	 */
	public void TestE1() {
		assertTrue(G.E() == 5);
	}
	@Test
	/**
	 * @brief Test the method E(), edge case
	 */
	public void TestE2() {
		assertTrue(g.E() == 0);
	}
	@Test
	/**
	 * @brief Test the method adj(i), edge case
	 */
	public void Testadj1() {
		assertTrue(G.adj(6).size() == 0);
	}
	@Test
	/**
	 * @brief Test the method adj(i), normal case
	 */
	public void Testadj2() {
		assertTrue(G.adj(0).contains(1) && G.adj(0).contains(2));
	}
	@Test (expected = IllegalArgumentException.class)
	/**
	 * @brief Test the method adj(i) when the input is illegal
	 */
	public void Testadj3() {
		ArrayList<Integer> temp = G.adj(10);
	}
	@Test
	/**
	 * @brief Test the method addedge(): increment of E
	 */
	public void Testaddegde1() {
		G.addedge(3, 6);
		assertTrue(G.E() == 6);
	}
	@Test
	/**
	 * @brief Test the method addedge(): neighboring relation
	 */
	public void Testaddedge2() {
		G.addedge(3, 6);
		assertTrue(G.adj(3).contains(6) && G.adj(6).contains(3));
	}
	@Test (expected = IllegalArgumentException.class)
	/**
	 * @brief Test the method addedge() when there is an exception
	 */
	public void Testaddedge3() {
		G.addedge(-1, 7);
	}

}
