/**
  *  Author: Senni Tan
  *  Revised: Apr 6, 2020
  *  
  *  Description: A class implement quick sort on Job objects.
 */

package final_project_2xb3_l03_g03;

/**
 * @brief A class generates quick sort on Job objects.
 */
public class Sorting extends Comparable {
	
	/**
	 * @brief A private method used for exchange two Job objects in an array.
	 * @param a is an array of Job objects
	 * @param i is the given index of a Job object in the array that needs to be exchanged.
	 * @param j is the given index of a Job object in the array that needs to be exchanged.
	 */
	private static void exch(Job[] a, int i, int j) {
		Job t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	/**
	 * @brief A method that used to sort a Job object based on its title.
	 * @param a is the array of the Job objects that needs to be sorted.
	 */
	public static void sortString(Job[] a) {
		sortString(a, 0, a.length - 1);
	}
	
	/**
	 * @brief A private method that implements quick sort on Job objects based on its title.
	 * @param a is the array of Job objects that needs to be sorted.
	 * @param lo is the index in the array that indicates the start of a range that needs to be sorted.
	 * @param hi is the index in the array that indicates the end of a range that needs to be sorted.
	 */
	private static void sortString(Job[] a, int lo, int hi) {
		if (hi <= lo) return;
		int j = partitionString(a, lo, hi);
		sortString(a, lo, j - 1);
		sortString(a, j + 1, hi);
	}
	
	/**
	 * @brief A private method that implements the partition based on Job objects' titles
	 * @param a is the array of Job objects that needs to be sorted.
	 * @param lo is the index in the array that indicates the start of a range that needs to be sorted.
	 * @param hi is the index in the array that indicates the end of a range that needs to be sorted.
	 * @return
	 */
	private static int partitionString(Job[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Job v = a[lo];
		while(true) {
			while (CompareString(a[++i], v) < 0) if (i == hi) break;
			while (CompareString(v, a[--j]) < 0) if (j == lo) break;
			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
	
	/**
	 * @brief A method that used to sort a Job object based on its outlook.
	 * @param a is the array of the Job objects that needs to be sorted.
	 */
	public static void sortOutlook(Job[] a) {
		sortOutlook(a, 0, a.length - 1);
	}
	
	/**
	 * @brief A private method that implements quick sort on Job objects based on its outlook.
	 * @param a is the array of Job objects that needs to be sorted.
	 * @param lo is the index in the array that indicates the start of a range that needs to be sorted.
	 * @param hi is the index in the array that indicates the end of a range that needs to be sorted.
	 */
	private static void sortOutlook(Job[] a, int lo, int hi) {
		if (hi <= lo) return;
		int j = partitionOutlook(a, lo, hi);
		sortOutlook(a, lo, j - 1);
		sortOutlook(a, j + 1, hi);
	}
	
	/**
	 * @brief A private method that implements the partition based on Job objects' outlook.
	 * @param a is the array of Job objects that needs to be sorted.
	 * @param lo is the index in the array that indicates the start of a range that needs to be sorted.
	 * @param hi is the index in the array that indicates the end of a range that needs to be sorted.
	 * @return
	 */
	private static int partitionOutlook(Job[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Job v = a[lo];
		while(true) {
			while (CompareOutlook(a[++i], v) < 0) if (i == hi) break;
			while (CompareOutlook(v, a[--j]) < 0) if (j == lo) break;
			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
	
	/**
	 * @brief A method that used to sort a Job object based on its first digit of its NOC number.
	 * @param a is the array of the Job objects that needs to be sorted.
	 */
	public static void sortNOC(Job[] a) {
		//sort by NOC[0]
		sortNOC(a, 0, a.length - 1);
	}
	
	/**
	 * @brief A private that implements quick sort on Job objects based on its first digit of its NOC number.
	 * @param a is the array of Job objects that needs to be sorted.
	 * @param lo is the index in the array that indicates the start of a range that needs to be sorted.
	 * @param hi is the index in the array that indicates the end of a range that needs to be sorted.
	 */
	private static void sortNOC(Job[] a, int lo, int hi) {
		if (hi <= lo) return;
		int j = partitionNOC(a, lo, hi);
		sortNOC(a, lo, j - 1);
		sortNOC(a, j + 1, hi);
	}
	
	/**
	 * @brief A private method that implements the partition based on the first digit of NOC number.
	 * @param a is the array of Job objects that needs to be sorted.
	 * @param lo is the index in the array that indicates the start of a range that needs to be sorted.
	 * @param hi is the index in the array that indicates the end of a range that needs to be sorted.
	 * @return
	 */
	private static int partitionNOC(Job[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Job v = a[lo];
		while(true) {
			while ((CompareNOC(a[++i], v)) < 0) if (i == hi) break;
			while (CompareNOC(v, a[--j]) < 0) if (j == lo) break;
			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}

	/**
	 * @brief A method that used to sort a Job object based on its region name.
	 * @param a is the array of the Job objects that needs to be sorted.
	 */
	public static void sortRegionS(Job[] a) {
		//sort by region String "regions"
		sortRegionS(a, 0, a.length - 1);
	}
	
	/**
	 * @brief A private that implements quick sort on Job objects based on its region name.
	 * @param a is the array of Job objects that needs to be sorted.
	 * @param lo is the index in the array that indicates the start of a range that needs to be sorted.
	 * @param hi is the index in the array that indicates the end of a range that needs to be sorted.
	 */
	private static void sortRegionS(Job[] a, int lo, int hi) {
		if (hi <= lo) return;
		int j = partitionRegionS(a, lo, hi);
		sortRegionS(a, lo, j - 1);
		sortRegionS(a, j + 1, hi);
	}
	
	/**
	 * @brief A private method that implements the partition based on the region name.
	 * @param a is the array of Job objects that needs to be sorted.
	 * @param lo is the index in the array that indicates the start of a range that needs to be sorted.
	 * @param hi is the index in the array that indicates the end of a range that needs to be sorted.
	 * @return
	 */
	private static int partitionRegionS(Job[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Job v = a[lo];
		while(true) {
			while ((CompareRegionS(a[++i], v)) < 0) if (i == hi) break;
			while (CompareRegionS(v, a[--j]) < 0) if (j == lo) break;
			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
}
