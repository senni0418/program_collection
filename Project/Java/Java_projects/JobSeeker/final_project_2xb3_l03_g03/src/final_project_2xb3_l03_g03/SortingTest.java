/**
  *  Author: Senni Tan
  *  Revised: Apr 6, 2020
  *  
  *  Description: Unit tests for Sorting.
 */

package final_project_2xb3_l03_g03;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @brief A class tests the Sorting module.
 */
public class SortingTest extends Sorting {

	static Job[] a1;
	static Job[] a2;
	static Job[] a3;
	static Job[] a4;
	static Job[] a5;
	
	/**
	 * @brief A private method that detects if two Job arrays are equal.
	 * @param a is one of the given Job array.
	 * @param b is another given Job array.
	 * @return true if two arrays are equal, false otherwise.
	 */
	private static boolean equalsArray(Job[] a, Job[] b) {
		if (a.length != b.length)
			return false;
		for (int i = 0; i < a.length; i++) {
			if (! equalsJob(a[i], b[i]))
				return false;
		}
		return true;
	}
	
	/**
	 * @brief A private method that detects if two Job objects are equal.
	 * @param a is one of the given Job object.
	 * @param b is another given Job object.
	 * @return true if two Job objects are equal, which means they are the 
	 * same instance variables; false otherwise.
	 */
	private static boolean equalsJob(Job a, Job b) {
		int n1 = a.getnoc();
		int n2 = b.getnoc();
		if (n1 != n2)
			return false;
		if (! a.get_title().contentEquals(b.get_title()))
			return false;
		if (a.get_outlook() != b.get_outlook())
			return false;
		if (a.get_year() != b.get_year())
			return false;
		if (! a.get_location().contentEquals(b.get_location()))
			return false;
		if (a.get_region() != b.get_region())
			return false;
		if (! a.get_regions().contentEquals(b.get_regions()))
			return false;
		return true;
	}
	
	/**
	 * @brief Set up before the tests.
	 * @throws Exception raised when set up failed.
	 */
	@Before
	public void setUp() throws Exception {
		Job teacher = new Job(new int[]{1,2,3,4,5}, "teacher", 2, 2020, "ON", 10086, "Toronto");
		Job engineer = new Job(new int[]{2,2,3,4,5}, "engineer", 3, 2020, "ON", 11086, "Markham");
		Job farmer = new Job(new int[]{3,2,3,4,5}, "farmer", 1, 2020, "ON", 13086, "Waterloo");
		Job barber = new Job(new int[]{4,2,3,4,5}, "barber", 0, 2020, "ON", 15086, "Ottawa");
		a1 = new Job[]{teacher, engineer, farmer, barber};
		a2 = new Job[] {barber,engineer, farmer, teacher};
		a3 = new Job[] {barber, farmer, teacher, engineer};
		a4 = new Job[] {teacher, engineer, farmer, barber};
		a5 = new Job[] {engineer, barber, teacher, farmer};
		
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * @brief A method that tests the sortString method.
	 */
	@Test
	public void testSortString() {
		sortString(a1);
		assertTrue(equalsArray(a1, a2));
	}

	/**
	 * @brief A method that tests the sortOutlook method.
	 */
	@Test
	public void testSortOutlook() {
		sortOutlook(a1);
		assertTrue(equalsArray(a1, a3));
	}

	/**
	 * @brief A method that tests the sortNOC method.
	 */
	@Test
	public void testSortNOC() {
		sortNOC(a1);
		assertTrue(equalsArray(a1, a4));
	}

	/**
	 * @brief A method that tests the sortRegionS method.
	 */
	@Test
	public void testSortRegionS() {
		sortRegionS(a1);
		assertTrue(equalsArray(a1, a5));
	}

}
