package final_project_2xb3_l03_g03;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Demo extends Sorting{
	/**
	 * @brief Connect related jobs in the graph
	 * @param G The graph of jobs
	 * @param jobs ArrayList of jobs
	 */
	public static void connectjobs(Graph G, ArrayList<Job> jobs) {
		for(int i = 0; i < jobs.size(); i++) {
			for(int j = i + 1; j < jobs.size(); j++) {
				if(jobs.get(i).get_noc(1) == jobs.get(j).get_noc(1) &&
					jobs.get(i).get_noc(2) == jobs.get(j).get_noc(2))
					G.addedge(i, j);
			}
		}
	}
	/**
	 * @brief Client module
	 * @exception FileNotFoundException, IOException, InterruptedException When the data base misses, input errors
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException{
		ArrayList<Job> joblist = new ArrayList<Job>();
		DataProcess data = new DataProcess();
		//joblist is an arraylist of all jobs in dataset
		joblist = data.get_data();
		int SIZE = joblist.size();
       //////////////////////////////////////////////////////
        //sort the arraylist of jobs(joblist) with respect to region name string(regions) alphabetically
		Job[] temp = joblist.toArray(new Job[joblist.size()]);
    	Sorting.sortRegionS(temp);
    	ArrayList<Job> sortedjoblist = new ArrayList<Job>(Arrays.asList(temp));
    	//sorted jobs are stored in sortedjoblist
		//code2string works as a dictionary where index is the region code of the content,
		//the max region code is less than 6300 in Canada
		String[] code2string = new String[6300];
		for(Job j : sortedjoblist) {
			code2string[j.get_region()] = j.get_regions();
		}
		////////////////////////////////
		//demonstrate the regions we have and let the user pick one
		int userlocation = 0;
		int usercategory = -1;
		while(true) {
			System.out.println("Please choose from these regions and enter the four digit code of the region you are interested in:");
			for(int i = 0; i < SIZE - 1; i++) {
				if(!sortedjoblist.get(i).get_regions().equals(sortedjoblist.get(i+1).get_regions()) && sortedjoblist.get(i).get_region() != sortedjoblist.get(i+1).get_region()) {
					System.out.println(sortedjoblist.get(i).get_regions() + "    : " + sortedjoblist.get(i).get_region());					
				}
			}
			if(sortedjoblist.get(SIZE-1).get_region() != sortedjoblist.get(SIZE-2).get_region())
				System.out.println(sortedjoblist.get(SIZE-1).get_regions() + "    : " + sortedjoblist.get(SIZE-1).get_region());
			//now take user input
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String input = reader.readLine();
			try {
				int input1 = Integer.parseInt(input);
				if(input1 < 6300 && input1 >= 0 && code2string[input1] != null) {
					userlocation = input1;
					break;
				}
			}
			catch(NumberFormatException e) {}
			System.out.println("Please enter the correct code");
			TimeUnit.SECONDS.sleep(1);
		}
        //////////////////////////////////////////////////////
		//now let the user choose category
		for(int i = 0; i < NOC.Noc.length; i++)
			System.out.println(i + ": " + NOC.Noc[i]);
		while(true) {
			System.out.println("\nPlease enter the index of the categories you are interested here: ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String input = reader.readLine();
			try {
				int input1 = Integer.parseInt(input);
				if(input1 >= 0 && input1 <= 9) {
					usercategory = input1;
					break;
				}
			}
			catch(NumberFormatException e) {}
			System.out.println("Please enter the correct index");
		}
        //select the given region and category, and have a new arraylist
		ArrayList<Job> jobinthisregion = Searching.LocationSearch(sortedjoblist,code2string[userlocation]);
		temp = jobinthisregion.toArray(new Job[jobinthisregion.size()]);
		Sorting.sortNOC(temp);
		ArrayList<Job> specificjobs = new ArrayList<Job>(Arrays.asList(temp));
		specificjobs = Searching.NocSearch(specificjobs, usercategory);
		temp = specificjobs.toArray(new Job[specificjobs.size()]);
		Sorting.sortOutlook(temp);
		/////////////////////////////////////////////////////
        //create a graph according to that
		Graph G = new Graph(specificjobs.size());
		connectjobs(G,specificjobs);
        //show the list of jobs(sorted by outlook)
		System.out.println("Searching for jobs...");
		int count = 0;
		outer:
		while(count < specificjobs.size() - 8) {
			for(int i = 0; i < 8; i++) {
				specificjobs.get(count).printbriefInfo();
				count++;
			}
			System.out.println("\n\nPress Enter to see more job titles; type 'quit' to quit scaning and have a close look at one job");
			while(true) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String input = reader.readLine();
				if(input.equals(""))
					break;
				if(input.equals("quit"))
					break outer;
				System.out.println("Wrong input, try again!");
			}
		}
        //allow the user have a close look at one job(outlook, relevant jobs)
		while(true) {
			System.out.println("\n\n\nPlease type the Noc code (just the number, no \"NOC\" in front) of the job you are interested in:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String noccode = reader.readLine();
			int tem;
			try {
				int code = Integer.parseInt(noccode);
				for(tem = 0; tem < specificjobs.size(); tem++) {
					if(code == specificjobs.get(tem).getnoc()) {
						specificjobs.get(tem).printInfo();
						DFS dfs = new DFS(G,tem);
						System.out.println("Number of relatedjobs: " + dfs.count());
						for(int k = 0; k < specificjobs.size(); k++)
							if(dfs.hasPathTo(k)) {
								specificjobs.get(k).printbriefInfo();
							}
					}
				}
			break;
			}
			catch(NumberFormatException e) {}
		}
    }
}