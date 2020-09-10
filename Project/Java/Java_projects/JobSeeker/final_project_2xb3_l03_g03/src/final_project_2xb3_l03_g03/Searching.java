/**
  *  Author: Gengyun Wang
  *  Revised: Apr 6, 2020
  *  
  *  Description: A class implement binary search on Job objects.
 */

package final_project_2xb3_l03_g03;
import java.util.ArrayList;

public class Searching {
	
	/**
	 * @brief A private method used for finding the index of given location.
	 * @param jobs is an ArrayList of Job objects
	 * @param location is the location of a group of Job objects in the ArrayList that users want to search.
	 * @return the index of the given location in the given ArrayList.
	 */

    private static int Location_Search(ArrayList<Job> jobs , String location){

		int l = 0, r = jobs.size() - 1; 
        while (l <= r) { 
            int m = l + (r - l) / 2; 
  
            int res = location.compareTo(jobs.get(m).get_regions()); 
  
            // Check if x is present at mid 
            if (res == 0) 
                return m; 
  
            // If x greater, ignore left half 
            if (res > 0) 
                l = m + 1; 
  
            // If x is smaller, ignore right half 
            else
                r = m - 1; 
        } 
        return -1; 
	}
	
    /**
	 * @brief A method used for finding the jobs which from given location.
	 * @param jobs is an ArrayList of Job objects
	 * @param location is the location of a group of Job objects in the ArrayList that users want to search.
	 * @return A ArrayList that includes all jobs from the given location.
	 */
	public static ArrayList<Job> LocationSearch(ArrayList<Job> jobs , String location){
		
		// First, use Location_Search find a index value
		int mid = Location_Search(jobs, location);
		
		//Then, the ArrayList may have multiple jobs with same location, so we need to find the beginning index value and ending index value
		int low = mid; //beginning index value
		int hi = mid; //ending index value
		while(hi < jobs.size()) {
			if(0!=location.compareTo(jobs.get(hi).get_regions())) {
                break;
			}
			hi++;
		}
		hi--;
		while(low >= 0) {
			if(0!=location.compareTo(jobs.get(low).get_regions())) {
                break;
			}
			low--;
		}
		low++;
		
		//After finding the two index values we want, add the corresponding job objects into a new ArrayList and return the ArrayList.
		ArrayList<Job> res = new ArrayList<Job>();
		for(int i = low; i <= hi; i++) {
			res.add(jobs.get(i));
		}
		return res;
	}
	
	/**
	 * @brief A private method used for finding the index of given Noc-code.
	 * @param jobs is an ArrayList of Job objects
	 * @param noc is the first number of Noc-code that users want to search.
	 * @return the index of the given Noc-number in the given ArrayList.
	 */
	private static int noc_Search(ArrayList<Job> jobs , int noc){

		int l = 0, r = jobs.size() - 1; 
        while (l <= r) { 
            int m = l + (r - l) / 2; 
  
            int res;
            
            if (noc == jobs.get(m).get_noc(0)) {
            	res = 0;
            }
            else if (noc > jobs.get(m).get_noc(0)) {
            	res = 1;
            }
            else {
            	res = -1;
            }
  
            // Check if x is present at mid 
            if (res == 0) 
                return m; 
  
            // If x greater, ignore left half 
            if (res > 0) 
                l = m + 1; 
  
            // If x is smaller, ignore right half 
            else
                r = m - 1; 
        } 
  
        return -1; 
	}
	
	/**
	 * @brief A method used for finding the jobs which has given Noc-code.
	 * @param jobs is an ArrayList of Job objects
	 * @param noc is the first number of Noc-code that users want to search.
	 * @return A ArrayList that includes all jobs has the given Noc-number.
	 */
	public static ArrayList<Job> NocSearch(ArrayList<Job> jobs , int noc){
		int mid = noc_Search(jobs, noc);
		int low = mid;
		int hi = mid;
		while(hi < jobs.size()) {
			if(noc != jobs.get(hi).get_noc(0)) {
                break;
			}
			hi++;
		}
		hi--;
		while(low >= 0) {
			if(noc != jobs.get(low).get_noc(0)) {
                break;
			}
			low--;
		}
		low++;
		ArrayList<Job> res = new ArrayList<Job>();
		for(int i = low; i <= hi; i++) {
			res.add(jobs.get(i));
		}
		return res;
	}
}