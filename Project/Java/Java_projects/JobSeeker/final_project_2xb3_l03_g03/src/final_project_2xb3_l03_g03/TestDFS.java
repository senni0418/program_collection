/**
  *  Author: Zihao Du
  *  Revised: April 05, 2020
  *  
  *  Description: Test the DFS class 
 */

package final_project_2xb3_l03_g03;

import org.junit.*;
import static org.junit.Assert.*;

public class TestDFS {
	private DFS dfs;
	private Graph G;
	@Before
	/**
	 * @brief Set up a graph and a DFS ADT for testing
	 */
	public void setUp() {
		G = new Graph(7);
		G.addedge(0,1);
		G.addedge(0,2);
		G.addedge(1,2);
		G.addedge(1,5);
		G.addedge(3,4);
		dfs = new DFS(G,0);
	}
	@After
	/**
	 * @brief Free the graph and DFS after a test
	 */
	public void teardown() {
		G = null;
		dfs = null;
	}
	@Test (expected = IllegalArgumentException.class)
	/**
	 * @brief Test the exception of the constructor
	 */
	public void Testconstructorexc() {
		DFS fail = new DFS(G,10);
	}
	@Test
	/**
	 * @brief Test the method hasPathTo() for all reachable nodes
	 */
	public void TesthasPathTotrue() {
		assertTrue(dfs.hasPathTo(1) && dfs.hasPathTo(2) && dfs.hasPathTo(5));
	}
	@Test
	/**
	 * @brief Test the method hasPathTo() for all unreachable nodes
	 */
	public void TesthasPathTofalse() {
		assertFalse(dfs.hasPathTo(3) && dfs.hasPathTo(4) && dfs.hasPathTo(6));
	}
	@Test (expected = IllegalArgumentException.class)
	/**
	 * @brief Test the method hasPathTo() when the input is not legal
	 */
	public void TesthasPathToexc() {
		Boolean fail = dfs.hasPathTo(50);
	}
	@Test
	/**
	 * @brief Test the method count(), normal case
	 */
	public void Testcount1() {
		assertTrue(dfs.count() == 3);
	}
	/**
	 * @brief Test the method count(), edge case
	 */
	@Test 
	public void Testcount2() {
		DFS dfs2 = new DFS(G,6);
		assertTrue(dfs2.count() == 0);
	}

}
