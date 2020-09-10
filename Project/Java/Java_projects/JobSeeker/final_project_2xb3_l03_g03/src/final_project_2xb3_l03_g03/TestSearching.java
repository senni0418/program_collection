/**
  *  Author: Gengyun Wang
  *  Revised: Apr 6, 2020
  *  
  *  Description: A class implement binary search on Job objects.
 */

package final_project_2xb3_l03_g03;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @brief A class tests the Searching module.
 */

public class TestSearching extends Searching{
	
	static ArrayList<Job> dataset = new ArrayList<Job>();
	static int[] noc1 = {0, 1, 1, 1};
	static Job job1 = new Job(noc1, "teacher", 0, 2016, "ON", 1234, "Toronto");
	static int[] noc2 = {0, 1, 3, 1};
	static Job job2 = new Job(noc2, "Professor", 1, 2017, "ON", 1234, "Toronto");
	static int[] noc3 = {1, 2, 3, 1};
	static Job job3 = new Job(noc3, "Casher", 2, 2017, "ON", 2345, "Vancouver");
	
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
	 * @brief A private method that detects if two Job arrays are equal.
	 * @param a is one of the given Job array.
	 * @param b is another given Job array.
	 * @return true if two arrays are equal, false otherwise.
	 */
	private static boolean equalsArrayList(ArrayList<Job> a, ArrayList<Job> b) {
		if (a.size() != b.size())
			return false;
		for (int i = 0; i < a.size(); i++) {
			if (! equalsJob(a.get(i), b.get(i)))
				return false;
		}
		return true;
	}

	
	/**
	 * @brief Set up before the tests.
	 * @throws Exception raised when set up failed.
	 */

	@Before
	public void setUp() throws Exception {
		dataset.add(job1);
		dataset.add(job2);
		dataset.add(job3);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * @brief A method that tests the LocationSearch method, normal case.
	 */

	@Test
	public void testLocationSearch() {
		ArrayList<Job> expect = new ArrayList<Job>();
		expect.add(job1);
		expect.add(job2);
		ArrayList<Job> result = LocationSearch(dataset, "Toronto");
		assertTrue(equalsArrayList(expect, result));
	}
	
	/**
	 * @brief A method that tests the LocationSearch method, exception case.
	 */

	@Test (expected = IndexOutOfBoundsException.class)
	public void testLocationSearch2() {
		ArrayList<Job> expect = new ArrayList<Job>();
		ArrayList<Job> result = LocationSearch(dataset, "Hamilton");
	}

	/**
	 * @brief A method that tests the NocSearch method, normal case.
	 */

	@Test
	public void testNocSearch() {
		ArrayList<Job> expect = new ArrayList<Job>();
		expect.add(job1);
		expect.add(job2);
		ArrayList<Job> result = NocSearch(dataset, 0);
		assertTrue(equalsArrayList(expect, result));
	}

	/**
	 * @brief A method that tests the NocSearch method, exception case.
	 */

	@Test (expected = IndexOutOfBoundsException.class)
	public void testNocSearch2() {
		ArrayList<Job> expect = new ArrayList<Job>();
		ArrayList<Job> result = NocSearch(dataset, 3);
	}
}
