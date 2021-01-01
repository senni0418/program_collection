package cas2xb3_A2_tan_ST;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends SetMeals {
	
	static FileWriter fwriter;
	static BufferedWriter bwriter;
	
	public static void main(String[] args) throws IOException {
		ArrayList<City> resultBFS = new ArrayList<City>();
		ArrayList<City> resultDFS = new ArrayList<City>();
		ArrayList<City> resultDijkstra = new ArrayList<City>();
		
		
		// set up and build all objects
		BuildMenu();
		BuildRestaurant();
		BuildCity();
		SetCityRestaurant();
		BuildEdges();
		BuildGraph();
		
		final City Boston = searchCity("BOSTON");
		final City Minneapolis = searchCity("Minneapolis");
		
		// run BFS and store the result
		BFS bfs_object = new BFS(G, Boston.num());
		ArrayList<Integer> resultBFS_int = bfs_object.pathTo_arraylist(Minneapolis.num());
		// transfer the integer array list result to City array list result
		for (int i = 0; i < resultBFS_int.size(); i++)
			resultBFS.add(num2city(resultBFS_int.get(i)));
		
		// run DFS and store the result
		DFS dfs_object = new DFS(G, Boston.num());
		ArrayList<Integer> resultDFS_int = dfs_object.pathTo_arraylist(Minneapolis.num());
		// transfer the integer array list result to City array list result
		for (int i = 0; i < resultDFS_int.size(); i++)
			resultDFS.add(num2city(resultDFS_int.get(i)));
		
		// run Dijkstra and store the result
		DijkstraSP object = new DijkstraSP(G, Boston.num());
		ArrayList<Integer> path = object.pathTo_InVertex(Minneapolis.num());
		// transfer the integer array list result to City array list result
		for (int i = 0; i < path.size(); i++)
			resultDijkstra.add(num2city(path.get(i)));
		// get the lowest cost meal
		ArrayList<Menu> mealsList = setMeals(path);
		
		// write results to a2_out.txt
		fwriter = new FileWriter("a2_out.txt");
		bwriter = new BufferedWriter(fwriter);
		// write BFS results
		bwriter.write("BFS: ");
		for (int i = 0; i < resultBFS.size(); i++) {
			bwriter.write(resultBFS.get(i).name());
			if (i < resultBFS.size() - 1)
				bwriter.write(", ");
		}
		bwriter.write(";");
		bwriter.newLine();
		// write DFS results
		bwriter.write("DFS: ");
		for (int i = 0; i < resultDFS.size(); i++) {
			bwriter.write(resultDFS.get(i).name());
			if (i < resultDFS.size() - 1)
				bwriter.write(", ");
		}
		bwriter.write(";");
		bwriter.newLine();
		// write Dijkstra results
		bwriter.write("Dijkstra Algorithms: ");
		bwriter.newLine();
		bwriter.write(String.format("%-20s %-40s %-20s%n", "City", "Meal Choice", "Cost of Meal"));
		bwriter.newLine();
		for (int i = 0; i < resultDijkstra.size(); i++) {
			City city = resultDijkstra.get(i);
			String name = city.name();
			String mealChoice;
			String cost;
			// we do not have meals for the first city
			if (i > 0) {
				mealChoice = mealsList.get(i-1).meal();
				cost = Double.toString(mealsList.get(i-1).price());
			}else {
				mealChoice = null;
				cost = null;
			}
			bwriter.write(String.format("%-20s %-40s %-20s%n", name, mealChoice, cost));
			bwriter.newLine();
		}
		// close the file
		bwriter.close();

	}

}
