/**
  *  Author: Wang wenzhi
  *  Revised: April 7, 2020
  *  
  *  Description: An abstract data type that represents job objects
 */
package final_project_2xb3_l03_g03;
public class Job// DataType for a job title
{
	private int[] noc;// Four digit code by array
	private String title;
	private int outlook;// 0=Undetermined 1=Limited 2=Fair 3=Good
	private int year;// 20xx
	private String location;
	private int region;
	private String regions;
	
	public Job (int[] noc, String title, int outlook, int year, String location, int region, String regions)
	{
		this.noc = noc;
		this.title = title;
		this.outlook = outlook;
		this.year = year;
		this.location = location;
		this.region = region;
		this.regions = regions;
	}
	/**
	 * @brief Get a digit of the noc code of this Job object. Used for sorting Job into different categories
	 * @param index the index of noc digit desired
	 * @return noc[index]:e.g.  NOC=[2,3,4,2], get_noc(0) = 2, get_noc(1) = 3 
	 */
	public int get_noc(int index)
	{
		return noc[index];
	}
	/**
	 * @brief Get noc code of this Job object.
	 * @return Noc as one number e.g. NOC[2,2,4,2] as 2242. Used for display.
	 */
	public int getnoc() {
		return (noc[0]*1000+noc[1]*100+noc[2]*10+noc[3]);
	}
	/**
	 * @brief Get title of this Job object as string:e.g. Electronic service technicians (household and business equipment)
	 */
	public String get_title()
	{
		return title;
	}
	/**
	 * @brief Get location(Province) of this Job object as string :e.g. QC | NL
	 */
	public String get_location()
	{
		return location;
	}
	/**
	 * @brief Get region code of this Job object as integer:e.g. 1310 | 1320
	 */
	public int get_region()
	{
		return region;
	}
	/**
	 * @brief Get outlook number of this Job object as integer: e.g.  0 or 1 or 2 or 3
	 */
	public int get_outlook()
	{
		return outlook+10-10;
	}
	public int get_year()
	{
		return year+1-1;
	}
	/**
	 * @brief Get region name of this Job object as String:e.g. Campbellton - Miramichi | Moncton - Richibucto
	 */
	public String get_regions() {
		return regions;
	}
	public void printbriefInfo() {
		System.out.print("Job title: "+this.get_title());
		System.out.println("    Noc _ "+this.get_noc(0)+" "+ this.get_noc(1)+" "+this.get_noc(2)+" "+this.get_noc(3));

	}
	public String getbriefInfo() {
		 return "Job title: "+this.get_title()+"    Noc _ "+this.get_noc(0)+" "+ this.get_noc(1)+" "+this.get_noc(2)+" "+this.get_noc(3);

	}
	public void printInfo() 
	{
		System.out.println("Job title: "+this.get_title());
		System.out.println("Noc _ "+this.get_noc(0)+" "+ this.get_noc(1)+" "+this.get_noc(2)+" "+this.get_noc(3));
		System.out.println("Outlook: "+this.get_outlook());
		System.out.println("Province: "+this.get_location());
		System.out.println("Econ region code: "+this.get_region());
		System.out.println("Econ region name: "+this.get_regions());

		System.out.println("Year "+this.get_year()+"\n");
		
	}
	public String getInfo() 
	{
		return "Job title: "+this.get_title()+"\nNoc _ "+this.get_noc(0)+" "+ this.get_noc(1)+" "+this.get_noc(2)+" "+this.get_noc(3)+"\nOutlook: "+this.get_outlook()+"\nProvince: "+this.get_location()+"\nEcon region code: "+this.get_region()+"\nEcon region name: "+this.get_regions()+"\nYear "+this.get_year()+"\n";
	}

	
}//end