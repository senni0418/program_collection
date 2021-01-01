package cas2xb3_A2_tan_ST;

import java.util.ArrayList;

// An ADT that represents restaurant
public class Restaurant {

	private final double longitude;
	private final double latitude;
	private final String name;
	private final String address;
	private String city;
	private Menu[] menu;
	
	// Constructor of the class Restaurant
	public Restaurant(double longitude, double latitude, String name, String address) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.name = name;
		this.address = address;
		this.menu = null;
		this.city = null;
	}
	
	// Method that sets the city name 
	public void set_city(City city) {
		double lon = city.longitude();
		double lat = city.latitude();
		double delta_long = Math.abs(lon - this.longitude);
		double delta_lat = Math.abs(lat - this.latitude);
		if ((delta_long < 0.5) && (delta_lat < 0.5))
			this.city = city.name();
	}
	
	// Method that sets the menu
	public void set_menu(Menu[] menu) {
		this.menu = menu;
	}
	
	// Getter method that returns the longitude
	public double longitude() {
		return this.longitude;
	}
	
	// Getter method that returns the latitude
	public double latitude() {
		return this.latitude;
	}
	
	// Getter method that returns the restaurant name
	public String name() {
		return this.name;
	}
	
	// Getter method that returns the address
	public String address() {
		return this.address;
	}
	
	// Getter method that returns the menu
	public Menu[] menu() {
		return this.menu;
	}
	
	// Getter method that returns the array list of menu[]
	public ArrayList<Menu> menuList() {
		ArrayList<Menu> m = new ArrayList<Menu>();
		for (Menu dish : this.menu)
			m.add(dish);
		return m;
	}
	
	// Getter method that returns the city
	public String city() {
		return this.city;
	}
	
	// print information for testing
	public void printInfo() {
		System.out.print(this.name + " " + this.longitude + " " + this.latitude + " ");
		System.out.print("in " + this.city + " with menu ");
		/*for (int i = 0; i < this.menu.length; i++)
			this.menu[i].printInfo();
			*/
		System.out.println();
	}
}
