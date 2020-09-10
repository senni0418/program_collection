/**
  *  Author: Senni Tan
  *  Revised: Apr 6, 2020
  *  
  *  Description: Unit tests for Comparable.
 */

package final_project_2xb3_l03_g03;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @brief A class tests the Comparable module.
 */
public class ComparableTest extends Comparable{

	static Job j1;
	static Job j2;
	static Job j3;
	
	/**
	 * @brief Set up before the tests.
	 * @throws Exception raised when set up failed.
	 */
	@Before
	public void setUp() throws Exception {
		int[] N1 = {1, 0, 0, 2};
		int[] N2 = {2, 0, 0, 1};
		int[] N3 = {1, 0, 0, 2};
		j1 = new Job (N1, "engineer", 3, 2020, "ON", 1000, "Toronto");
		j2 = new Job (N2, "farmer", 2, 2020, "ON", 1888, "Waterloo");
		j3 = new Job (N1, "engineer", 3, 2020, "ON", 1000, "Toronto");
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * @brief A method that tests the CompareString method.
	 */
	@Test
	public void testCompareString() {
		int cmp1 = CompareString(j1, j2);
		int cmp2 = CompareString(j2, j1);
		int cmp3 = CompareString(j1, j3);
		assertTrue(cmp1 < 0);
		assertTrue(cmp2 > 0);
		assertTrue(cmp3 == 0);
	}

	/**
	 * @brief A method that tests the CompareOutlook method.
	 */
	@Test
	public void testCompareOutlook() {
		int cmp1 = CompareOutlook(j1, j2);
		int cmp2 = CompareOutlook(j2, j1);
		int cmp3 = CompareOutlook(j1, j3);
		assertTrue(cmp1 > 0);
		assertTrue(cmp2 < 0);
		assertTrue(cmp3 == 0);
	}

	/**
	 * @brief A method that tests the CompareNOC method.
	 */
	@Test
	public void testCompareNOC() {
		int cmp1 = CompareNOC(j1, j2);
		int cmp2 = CompareNOC(j2, j1);
		int cmp3 = CompareNOC(j1, j3);
		assertTrue(cmp1 < 0);
		assertTrue(cmp2 > 0);
		assertTrue(cmp3 == 0);
	}

	/**
	 * @brief A method that tests the CompareRegionS method.
	 */
	@Test
	public void testCompareRegionS() {
		int cmp1 = CompareRegionS(j1, j2);
		int cmp2 = CompareRegionS(j2, j1);
		int cmp3 = CompareRegionS(j1, j3);
		assertTrue(cmp1 < 0);
		assertTrue(cmp2 > 0);
		assertTrue(cmp3 == 0);
	}

}
