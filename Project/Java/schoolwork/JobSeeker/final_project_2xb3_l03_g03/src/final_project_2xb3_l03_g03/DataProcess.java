/**
  *  Author: Wang wenzhi
  *  Revised: April 7, 2020
  *  
  *  Description: A module that reads in and processes the dataset
 */
package final_project_2xb3_l03_g03;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
* @brief Dataprocess class that  processes the datasets and return a list of jobs.
*/
public class DataProcess {
//	public Job[] dataset;
    public ArrayList<Job> dataset = new ArrayList<Job>();

	public DataProcess()throws IOException{
	
	//when initialized, read csv and store
		//loop years
		for (int i = 2016; i < 2020 ;i++) {
		File input = new File("2016b.csv");
		switch(i) {
		case(2016):
			input = new File("2016b.csv");
			break;
		
		case(2017):
			input = new File("2017.csv");
			break;
			
		case(2018):
			input = new File("2018.csv");
			break;
			
		case(2019):
			input = new File("2019.csv");
			break;
		}
		
		
		
		 
		BufferedReader br = null;
        br = new BufferedReader(new FileReader(input));
        String line = br.readLine();
		while((line = br.readLine()) != null) {
		//To loop,readline - split - create Jobobject - append to dataset[]
		String[] linecontent = line.split(",");
		int[] noc= {(int)linecontent[0].charAt(4)-48,(int)linecontent[0].charAt(5)-48,(int)linecontent[0].charAt(6)-48,(int)linecontent[0].charAt(7)-48};
		
		 int outlook = 10;

			//Set outlook Undetermined = 0/ Limited = 1 / Fair = 2 / Good = 3
			switch(linecontent[2]) {
			
			case "Undetermined":
			outlook = 0;
			break;
	
			case "Limited":
				outlook = 1;
				break;
			
			case "Fair":
				outlook = 2;
				break;
				
			case "Good":
				outlook = 3;
				break;
			}// outlook set
			
			//Set year

			String yearstring = linecontent[4].substring(6);
			int year = Integer.parseInt(yearstring);
		Job j = new Job(noc, linecontent[1].replaceAll("\"", ""), outlook, year, linecontent[5],Integer.parseInt(linecontent[6]),linecontent[7]);
		dataset.add(j);
		/**
		 * linecontent[0] = NOC_0123
		 * linecontent[1] = someJobtitle
		 * linecontent[2] = Undetermined / Limited / Fair / Good   (outlook) 
		 * linecontent[4] = 21/12/2016 
		 * linecontent[5] = NL  
		 * **/



		}//end while loop
		
		
		
br.close();
		} //end for loop (years)
	}
	
	/**
	* @brief: The only method that is supposed to be used by other modules. 
	* @return : the dataset as a list of jobs.  
	*/
	public ArrayList<Job> get_data() 
	{
		return dataset;
	}
	
	//The following methods belongs to DataProcess Class
	// and is only used for testing if this module is working correctly
	// the actual method for searching jobs are implemented as the Searching module
	public Job get_job(int[] noc, int region, int year) 
	{
		
		int i = 0;
		while(i<dataset.size())//go thru list of Job
		{
			Job currentjob = dataset.get(i);
			if (currentjob.get_noc(0) == noc[0] & currentjob.get_noc(1) == noc[1]& currentjob.get_noc(2) == noc[2]& currentjob.get_noc(3) == noc[3])
			{
				if (currentjob.get_region() == region & currentjob.get_year() == year) {
						return currentjob;
				}
			}
				
			i++;
		}
		
		System.out.println("not found");
		return null;
	}

	/*public static void main(String[] args) throws IOException {
		DataProcess test = new DataProcess();
		
		int[] noc = {0,1,1,2};
		int region = 4850;
		int year = 2018;
		
		test.get_job(noc, region, year).printInfo();
		 *
		 * 
		 * Electronic service technicians (household and business equipment)
		 * 2-2-4-2
		 * 2
		 * SK
		 * 4760
		 * 2019
		 * 
		
		
		
		
		
	}*/




}//end