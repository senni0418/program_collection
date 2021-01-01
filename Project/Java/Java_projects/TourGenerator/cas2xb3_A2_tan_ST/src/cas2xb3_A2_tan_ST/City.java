package cas2xb3_A2_tan_ST;

import java.util.ArrayList;

// An ADT that represents a city
public class City {

	private final int state_code;
	private final int zip;
	private final String state;
	private final String name;
	private final double latitude;
	private final double longitude;
	private final int num; // num is a special number to identify each city, started from 0;
	private ArrayList<Restaurant> restaurant;
	
	// Constructor of the class City
	public City(int state_code, int zip, String state, String name, double latitude, double longitude, int num) {
		this.state_code = state_code;
		this.zip = zip;
		this.state = state;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.restaurant = new ArrayList<Restaurant>();
		this.num = num;
	}
	
	// Method that adds the restaurant
	public void add_restaurant(Restaurant restaurant) {
		double res_long = restaurant.longitude();
		double res_lat = restaurant.latitude();
		double delta_long = Math.abs(res_long - this.longitude);
		double delta_lat = Math.abs(res_lat - this.latitude);
		/* for coding convenience, we only add one of the McDonald's restaurants in that city,
		 * same for Burger King and Wendy's
		 */
		if ((delta_long < 0.5) && (delta_lat < 0.5))
			if (!detect(restaurant))
				this.restaurant.add(restaurant);
	}
	
	/* Private method that detect if there is already one McDonald's/Burger King/ Wendy's
	 * restaurant in the city restaurant list
	 */
	private boolean detect(Restaurant r) {
		String name = r.name();
		for (int i = 0; i < this.restaurant.size(); i++) {
			if (this.restaurant.get(i).name().contentEquals(name))
				return true;
		}
		return false;
	}
	
	// Getter method that returns the state_code
	public int state_code() {
		return this.state_code;
	}
	
	// Getter method that returns the zip code
	public int zip() {
		return this.zip;
	}
	
	// Getter method that returns the state
	public String state() {
		return this.state;
	}
	
	// Getter method that returns the city name
	public String name() {
		return this.name;
	}
	
	// Getter method that returns the latitude
	public double latitude() {
		return this.latitude;
	}
	
	// Getter method that returns the longitude
	public double longitude() {
		return this.longitude;
	}
	
	// Getter method that returns the array list of restaurant
	public ArrayList<Restaurant> restaurantList() {
		return this.restaurant;
	}
	
	// Getter method that returns the num
	public int num() {
		return this.num;
	}
	
	// Getter method that returns the array of restaurant
	public Restaurant[] restaurantArray() {
		Restaurant[] res = new Restaurant[this.restaurant.size()];
		for (int i = 0; i < this.restaurant.size(); i++)
			res[i] = this.restaurant.get(i);
		return res;
	}
	
	// print information for testing
	public void printInfo(){
		System.out.println(this.num + " " + this.name + " " + this.longitude + " " + this.latitude + " with restaurants:");
		for (int i = 0; i < this.restaurant.size(); i++)
			this.restaurant.get(i).printInfo();
		System.out.println();
	}
}
