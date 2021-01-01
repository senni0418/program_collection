package cas2xb3_A2_tan_ST;

import java.util.ArrayList;

// set the lowest cost meal plan in the travel
public class SetMeals extends BuildObject {
	
	private static Menu meal; // the meal that we eat from last visited city
	public static ArrayList<Menu> meals; // the meals that we eat along the way (in lowest cost)
	
	/* The basic idea is that I will use a variable meal to keep a record on 
	 * what we eat in from the last city we visit and an array list of what
	 * we eat along the way to the destination; when we visit a new 
	 * city I add all meals information of the restaurants in this city into
	 * the IndexMinPQ; then I extract the minimum cost meal from the PQ
	 * and compare this meal with the meal we ate in last city,
	 * if this meal != last meal, then we take this meal, add it in
	 * the array list; if this meal = last meal, then we extract the next minimum
	 * cost meal from the PQ and repeat the previous step 
	 */
	public static ArrayList<Menu> setMeals(ArrayList<Integer> path) {
		meals = new ArrayList<Menu>();
		// we don't need to eat at the started city so we start at index 1
		for (int i = 1; i < path.size(); i++) {
			/*the priority queue that contains all meals with the key(cost)
			  there are 40 meals in total including all restaurants */
			IndexMinPQ<Double> pq = new IndexMinPQ<Double>(40); 
			// convert the number in the path to actual city object
			int num = path.get(i);
			City city = num2city(num);
			// get the restaurants in this city
			Restaurant[] restaurants = city.restaurantArray();
			// transfer the meals in restaurants to the PQ
			for (Restaurant r : restaurants) {
				Menu[] m = r.menu();
				for (Menu meal : m) 
					pq.insert(meal.num(), meal.price());
			}
			/* at the beginning, take the cheapest meal; 
			 * keep an record and add it to the array list
			 */
			if (i == 1)	{
				int n = pq.delMin();
				Menu cheapest = num2meal(n);
				meal = cheapest;
				meals.add(meal);
			}else {
				/* in the next following cities, check if the cheapest meal in this city
				 * is eaten in last city; if yes, take the next cheapest; if no, take this one
				 */
				int n = pq.delMin();
				Menu cheapest = num2meal(n);
				if (cheapest.equals(meal)) {
					n = pq.delMin();
					cheapest = num2meal(n);
					meal = cheapest;
					meals.add(meal);
				}else {
					meal = cheapest;
					meals.add(meal);
				}
			}
			
		}
		return meals;
	}

}
