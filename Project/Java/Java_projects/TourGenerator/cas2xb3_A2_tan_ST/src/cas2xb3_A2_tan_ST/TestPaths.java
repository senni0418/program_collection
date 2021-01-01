package cas2xb3_A2_tan_ST;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPaths extends BuildObject {

	@Before
	public void setUp() throws Exception {
		BuildMenu();
		BuildRestaurant();
		BuildCity();
		SetCityRestaurant();
		BuildEdges();
		BuildGraph();
	}

	@After
	public void tearDown() throws Exception {
	}

	// test BFS for normal case
	@Test
	public void testBFS1() {
		String from = "Boston";
		String to = "Minneapolis";
		City c1 = searchCity(from);
		City c2 = searchCity(to);
		String expected = "BOSTON, NEW YORK CITY, PITTSBURGH, COLUMBUS, CHICAGO, MINNEAPOLIS";
		BFS bfs_object = new BFS(G, c1.num());
		String result = bfs_object.pathToString(c2.num());
		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	// test BFS for boundary case: there is no path between two city
	@Test(expected = NullPointerException.class)
	public void testBFS2() {
		String from = "Miami";
		String to = "Boston";
		City c1 = searchCity(from);
		City c2 = searchCity(to);
		String expected = null;
		BFS bfs_object = new BFS(G, c1.num());
		String result = bfs_object.pathToString(c2.num());
		assertTrue(result == null);
	}
	
	// test BFS for boundary case: the city name is not properly entered
	@Test(expected = NullPointerException.class)
	public void testBFS3() {
		String from = "";
		String to = "Minneapolis";
		City c1 = searchCity(from);
		City c2 = searchCity(to);
		String expected = null;
		BFS bfs_object = new BFS(G, c1.num());
		String result = bfs_object.pathToString(c2.num());
		assertTrue(result == null);
	}
	
	// test BFS for boundary case: from the same city to the same city
	@Test
	public void testBFS4() {
		String from = "Boston";
		String to = "Boston";
		City c1 = searchCity(from);
		City c2 = searchCity(to);
		String expected = "BOSTON";
		BFS bfs_object = new BFS(G, c1.num());
		String result = bfs_object.pathToString(c2.num());
		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	// test DFS for normal case
	@Test
	public void testDFS1() {
		String from = "Boston";
		String to = "Minneapolis";
		City c1 = searchCity(from);
		City c2 = searchCity(to);
		String expected = "BOSTON, NEW YORK CITY, PHILADELPHIA, BALTIMORE, WASHINGTON, CHARLOTTE, ATLANTA, NASHVILLE, COLUMBUS, INDIANAPOLIS, ST. LOUIS, KANSAS CITY, DENVER, SALT LAKE CITY, LAS VEGAS, LOS ANGELES, SAN FRANCISCO, PORTLAND, SEATTLE, MINNEAPOLIS";
		DFS dfs_object = new DFS(G, c1.num());
		String result = dfs_object.pathToString(c2.num());
		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	// test DFS for boundary case: there is no path between two city
	@Test(expected = NullPointerException.class)
	public void testDFS2() {
		String from = "Miami";
		String to = "Boston";
		City c1 = searchCity(from);
		City c2 = searchCity(to);
		String expected = null;
		BFS bfs_object = new BFS(G, c1.num());
		String result = bfs_object.pathToString(c2.num());
		assertTrue(result == null);
	}
	
	// test DFS for boundary case: the city name is not properly entered
	@Test(expected = NullPointerException.class)
	public void testDFS3() {
		String from = "";
		String to = "Minneapolis";
		City c1 = searchCity(from);
		City c2 = searchCity(to);
		String expected = null;
		BFS bfs_object = new BFS(G, c1.num());
		String result = bfs_object.pathToString(c2.num());
		assertTrue(result == null);
	}
	
	// test DFS for boundary case: from the same city to the same city
	@Test
	public void testDFS4() {
		String from = "Boston";
		String to = "Boston";
		City c1 = searchCity(from);
		City c2 = searchCity(to);
		String expected = "BOSTON";
		DFS dfs_object = new DFS(G, c1.num());
		String result = dfs_object.pathToString(c2.num());
		assertTrue(expected.equalsIgnoreCase(result));
	}

}
