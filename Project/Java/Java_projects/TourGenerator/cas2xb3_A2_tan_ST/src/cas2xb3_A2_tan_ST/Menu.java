package cas2xb3_A2_tan_ST;

// An ADT that represents a menu
public class Menu {

	private final String restaurant;
	private final String meal;
	private final double price;
	private final String protein;
	private final int num;
	
	// Constructor of the class Menu
	public Menu(String restaurant, String meal, double price, String protein, int num) {
		this.restaurant = restaurant;
		this.meal = meal;
		this.price = price;
		this.protein = protein;
		this.num = num;
	}
	
	// Getter method that returns the restaurant name
	public String restaurant() {
		return this.restaurant;
	}
	
	// Getter method that returns the meal name
	public String meal() {
		return this.meal;
	}
	
	// Getter method that returns the price
	public double price() {
		return this.price;
	}
	
	// Getter method that returns the protein name
	public String protein() {
		return this.protein;
	}
	
	// Getter method that returns the num
	public int num() {
		return this.num;
	}
	
	// print information for testing
	public void printInfo() {
		System.out.println("Menu from " + this.restaurant + ", meal: " + this.meal + ", price: " + this.price + " with " + this.protein);
	}
}
