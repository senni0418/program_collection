/**
  *  Author: Senni Tan
  *  Revised: Apr 6, 2020
  *  
  *  Description: A class used for comparator in sorting module.
 */

package final_project_2xb3_l03_g03;

/**
 * @brief A class used for generating comparators in sorting module.
 */
public class Comparable {
	
	/**
	 * @brief A method that used to generate a comparator which compares the Job objects in alphabet order.
	 * @param a is one of the Job object.
	 * @param b is another Job object.
	 * @return 1 if the Job object in the first parameter is alphabetically "larger" than the second one;
	 * 0 if equal; -1 if smaller.
	 */
	public static int CompareString(Job a, Job b) {
		String a1 = a.get_title();
		String b1 = b.get_title();
		int cmp = a1.compareTo(b1);
		return cmp;
	}
	
	/**
	 * @brief A method that generate a comparator which compares the Job objects in outlook.
	 * @param a is one of the Job object.
	 * @param b is another Job object.
	 * @return 1 if the Job object in the first parameter is better than the second one in its outlook;
	 * 0 if equal; -1 if worse.
	 * @detail Outlook ranking: Good > Fair > Limited > Undetermined
	 */
	public static int CompareOutlook(Job a, Job b) {
		int a1 = a.get_outlook();
		int b1 = b.get_outlook();
		if (a1 > b1)
			return 1;
		if (a1 < b1)
			return -1;
		return 0;
	}
	
	/**
	 * @brief A method that generate a comparator which compares the Job objects in NOC number order.
	 * @param a is one of the Job object.
	 * @param b is another Job object.
	 * @return 1 if the Job object in the first parameter is greater than the second one in its NOC 
	 * number order; 0 if equal; -1 if less.
	 * @detail The NOC number order is same as the normal number order. 
	 */
	public static int CompareNOC(Job a, Job b) {
		int NOCa = a.get_noc(0);
		int NOCb = b.get_noc(0);
		if (NOCa < NOCb)
			return -1;
		if (NOCa > NOCb)
			return 1;
		return 0;
	}
	
	/**
	 * @brief A method that generate a comparator which compares the Job objects in its region string's alphabet order.
	 * @param a is one of the Job object.
	 * @param b is another Job object.
	 * @return 1 if the Job object in the first parameter is greater than the second one in its region's 
	 * string alphabet order; 0 if equal; -1 if less.
	 */
	public static int CompareRegionS(Job a, Job b) {
		String Ra = a.get_regions();
		String Rb = b.get_regions();
		return Ra.compareTo(Rb);
	}
}
