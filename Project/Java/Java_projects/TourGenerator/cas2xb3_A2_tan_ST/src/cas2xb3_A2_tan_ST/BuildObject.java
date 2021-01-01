package cas2xb3_A2_tan_ST;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


// Build the objects that we need in the Graph API such as cities, restaurants etc.
public class BuildObject {

	public static ArrayList<City> cities;
	public static Menu[] McDonalds;
	public static Menu[] BurgerKing;
	public static Menu[] Wendys;
	public static Menu[] all;
	public static ArrayList<Restaurant> restaurants;
	public static ArrayList<DirectedEdge> edges;
	public static EdgeWeightedDigraph G;
	
	////read menu.csv and build menu objects
	public static void BuildMenu() throws IOException {
		McDonalds = new Menu[18]; // there are 18 objects in McDonald's menu
		BurgerKing = new Menu[12]; // there are 12 objects in BurgerKing's menu
		Wendys = new Menu[10]; // there are 10 objects in Wendy's menu
		
		FileReader fread = new FileReader("menu.csv");
		BufferedReader bread = new BufferedReader(fread);
		String s;
		
		// build menus
		all = new Menu[40];
		for (int i = 0; i < 41; i++) { // there are 41 lines in menu.csv
			s = bread.readLine();
			if (i > 0) { // start building objects after reading first line
				String[] tmp;
				tmp = s.split(",");
				Menu object;
				if (i < 19)
					object = new Menu("McDonalds", tmp[1], Double.parseDouble(tmp[2].substring(1)), tmp[3], i - 1);
				else if (i < 31)
					object = new Menu("BurgerKing", tmp[1], Double.parseDouble(tmp[2].substring(1)), tmp[3], i - 1);
				else
					object = new Menu("Wendys", tmp[1], Double.parseDouble(tmp[2].substring(1)), tmp[3], i - 1);
				all[i-1] = object;
			}
		}
		// transfer menus to each array (McDonalds, BurgerKing, Wendys)
		for (int i = 0; i < 18; i++) 
			McDonalds[i] = all[i];
		for (int i = 0; i < 12; i++)
			BurgerKing[i] = all[18 + i];
		for (int i = 0; i < 10; i++)
			Wendys[i] = all[30 + i];
				
		bread.close();
	}
	
	public static Menu num2meal(int num) {
		return all[num];
	}
	
	public static void BuildRestaurant() throws IOException {
		////Build McDonald's
		// count the lines of file
		BufferedReader reader = new BufferedReader(new FileReader("mcdonalds.csv"));
		int lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
				
		// read the mcdonalds.csv
		restaurants = new ArrayList<Restaurant>(); 
		FileReader fread = new FileReader("mcdonalds.csv");
		BufferedReader bread = new BufferedReader(fread);
		String s;
		for (int i = 0; i < lines; i++) {
			s = bread.readLine();
			if (i > 0) {
				String[] tmp;
				tmp = s.split(",");
				double longitude = Double.parseDouble(tmp[0]);
				double latitude = Double.parseDouble(tmp[1]);
				String name = "McDonalds";
				String address = tmp[3];
				Restaurant object = new Restaurant(longitude, latitude, name, address);
				object.set_menu(McDonalds);
				restaurants.add(object);
			}
			
		}
		bread.close();
		
		//// Build BurgerKing
		reader = new BufferedReader(new FileReader("burgerking.csv"));
		lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
				
		// read the burgerking.csv
		fread = new FileReader("burgerking.csv");
		bread = new BufferedReader(fread);
		for (int i = 0; i < lines; i++) {
			s = bread.readLine();
			if (i > 0) {
				String[] tmp;
				tmp = s.split(",");
				double longitude = Double.parseDouble(tmp[0]);
				double latitude = Double.parseDouble(tmp[1]);
				String name = "BurgerKing";
				String address = tmp[3];
				Restaurant object = new Restaurant(longitude, latitude, name, address);
				object.set_menu(BurgerKing);
				restaurants.add(object);
			}
			
		}
		bread.close();
		
		//// Build Wendy's
		reader = new BufferedReader(new FileReader("wendys.csv"));
		lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
				
		// read the wendys.csv
		fread = new FileReader("wendys.csv");
		bread = new BufferedReader(fread);
		for (int i = 0; i < lines; i++) {
			s = bread.readLine();
			if (i > 0) {
				String[] tmp;
				tmp = s.split(",");
				double longitude = Double.parseDouble(tmp[0]);
				double latitude = Double.parseDouble(tmp[1]);
				String name = "Wendys";
				String address = tmp[3];
				Restaurant object = new Restaurant(longitude, latitude, name, address);
				object.set_menu(Wendys);
				restaurants.add(object);
			}
			
		}
		bread.close();
	}
	
	// Build cities
	public static void BuildCity() throws IOException {
		// count the lines of file
		BufferedReader reader = new BufferedReader(new FileReader("USCities.csv"));
		int lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
			
		// read the USCities.csv
		cities = new ArrayList<City>(); 
		FileReader fread = new FileReader("USCities.csv");
		BufferedReader bread = new BufferedReader(fread);
		String s;
		for (int i = 0; i < lines; i++) {
			s = bread.readLine();
			if (i > 0) {
				String[] tmp;
				tmp = s.split(",");
				int state_code = Integer.parseInt(tmp[0]);
				int zip = Integer.parseInt(tmp[1]);
				String state = tmp[2];
				String name = tmp[3];
				double lat = Double.parseDouble(tmp[4]);
				double lon = Double.parseDouble(tmp[5]);
				City city = new City(state_code, zip, state, name, lat, lon, i-1);
				cities.add(city);
			}
			
		}
		bread.close();
	}
	
	//Method that returns a City object according to the num
	public static City num2city(int num) {
		if (cities.size() == 0)
			return null;
		if (num < 0 || num >= cities.size())
			return null;
		return cities.get(num);
	}
	
	// Add restaurant to corresponding cities and add city name to corresponding restaurant
	public static void SetCityRestaurant() {
		for (int i = 0; i < cities.size(); i++) {
			for (int j = 0; j < restaurants.size(); j++) {
				City city = cities.get(i);
				Restaurant res = restaurants.get(j);
				city.add_restaurant(res);
				res.set_city(city);
				cities.set(i, city);
				restaurants.set(j, res);
			}
		}
	}
	
	// Method that search the city in array list cities with an input city name string
	public static City searchCity(String s) {
		if (s == null)
			return null;
		for (int i = 0; i < cities.size(); i++) {
			City city = cities.get(i);
			String name = city.name();
			name = name.toUpperCase();
			s = s.toUpperCase();
			if (name.equalsIgnoreCase(s))
				return city;
		}
		return null;
	}
	
	// Build edges
	public static void BuildEdges() throws IOException {
		// count the lines of file
		BufferedReader reader = new BufferedReader(new FileReader("connectedCities.csv"));
		int lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
		
		// read the "connectedCities.csv" and build edges
		FileReader fread = new FileReader("connectedCities.csv");
		BufferedReader bread = new BufferedReader(fread);
		edges = new ArrayList<DirectedEdge>();
		String s;
		for (int i = 0; i < lines; i++) {
			s = bread.readLine();
			String[] tmp;
			tmp = s.split(", ");
			City city1 = searchCity(tmp[0]);
			City city2 = searchCity(tmp[1]);
			double weight = 1;
			int num1 = city1.num();
			int num2 = city2.num();
			DirectedEdge e1 = new DirectedEdge(num1, num2, weight);
			edges.add(e1);
		}
		bread.close();
	}
	
	// Build graph
	public static void BuildGraph() {
		int N = 32; // there are 32 cities in the USCities.csv so there are 32 vertices
		G = new EdgeWeightedDigraph(N);
		for (int i = 0; i < edges.size(); i++) 
			G.addEdge(edges.get(i));
	}
	
	/* tests
	public static void main(String[] args) throws IOException {
		BuildMenu();
		BuildRestaurant();
		BuildCity();
		SetCityRestaurant();
		BuildEdges();
		BuildGraph();
		System.out.println(G.V());
		System.out.println(G.E());
		for (DirectedEdge e : G.edges())
			System.out.println(e.toString());
	}*/
}
