import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class StoreStatistics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
File f = new File ("Project01Data.txt");
		
		// A BufferedReader is an object that lets you read a stream of Text Data

				BufferedReader in = null;


				// Try to open the file

				// Handle exceptions if they happen

				//

				try {

				in = new BufferedReader(new FileReader(f));

				}

				catch (FileNotFoundException e)	{

				System.out.println("The file cannot be opened! Error Number - " + e);

				}


				String line = null;

				// Used to read the line of text / (String) from the file

				String sStoreNum [] [] = {
						{"  ", "TOTAL"},
						{"101", "St. John's, Newfoundland"},
						{"102", "Halifax, Nova Scotia"},
						{"103", "Fredericton, New Brunswick"},
						{"201", "Quebec City, Quebec"},
						{"202", "Montreal, Quebec"},
						{"301", "Ottawa, Ontario"},
						{"302", "Toronto, Ontario"},
						{"303", "Mississauga, Ontario"},
						{"304", "Hamilton, Ontario"},
						{"305", "Kitchener, Ontario"},
						{"401", "Winnipeg, Manitoba"},
						{"402", "Regina, Saskatchewan"},
						{"403", "Calgary, Alberta"},
						{"404", "Edmonton, Alberta"},
						{"405", "Vancouver, British Columbia"},
						{"??", "Unknown"},	
						
						
				};
				
				String sProductList [] [] = {
						{"  ", "TOTAL"},
						{"PR121", "Metal Pipe"},
						{"PR122", "Smooth Wall Steel Pipe"},
						{"PR123", "Corrugated Metal Pipe"},
						{"PR124", "S.P.C Metal Pipe"},
						{"PR231", "Tunnel Liner Plate"},
						{"PR331", "Concrete Arch Systems"},
						{"PR441", "Gates"},
						{"PR511", "Polyethylene Pipe"},
						{"PR512", "Polyvinyl Chloride Pipe"},
						{"PR513", "Reinforced Concrete Pipe"},
						{"PR661", "Beaver Control"},
						{"PR701", "End Treatments"},
						{"PR811", "Culvert Liner"},
						{"??", "Unknown"},	
						
				};
						
					
				
				double dSalesAmt = 0; //Temp variable to hold the sales amount
				
				double [] [] [] dTableOfData = new double [17] [15] [14];
				//DTABLEOFDATA will be used to hold the sales totals
				//The rows represent the provinces
				//		Row 0 - Column Totals
				//		Rows 1 to 10 - Province Sales Totals in order of sProvs
				//	Row 11 - Unmatached Province code
				//
				//	Col 0 - Row Totals
				//	Cols 1 to 12 - Monthly Totals
				//	Col 13 - Unmatched monthly values in Date Information
				//
				//	Position [0][0] will hold the Grand Total
				//
				
				int iLinesRead = 0;			//  ILINESREAD is used to track the number of lines read from the file
				int iStore = 0;				//  iStore is used to position the row (store) in DATATABLE
				int iProduct = 0;			//  iProduct is used to position the column (product) in DATATABLE
				int iMonth = 0;				//	iMonth is used to position the month in DATATABLE
				
				String sValue = "";
				String [] data;
				//DATA is used to split the information on a single line
			
				boolean done = false;
				// Use to indicate when there's no more data in the file 

	do {

		try		{ // Try to Read the next line from the file
				iLinesRead++;	// Increment the counter
				line = in.readLine();

				}

				catch (IOException e) { // Handle any errors

				System.out.println("There is a problem at line "+ iLinesRead

				+ " Error number - " +e);

				}


				if (line == null){

				done = true; // No more data in the file - You're done!

				}

				else { // More data
					
					
					// Data on one line comes in as 
					//		ON,20100527,45.33
					// The comma is used as a data separator
					
					//////////////////////////////////////////////
					
					// The following option will split the data from
					// The input line (line) based on the comma.
					// The string array data will be populated with information
					// "Hello There!" will fill element zero (0) of data
					// 435 will fill element one (1) OF DATA
					
					//////////////////////////////////////////////
					
					
					//System.out.println("Line: "+line);
					data = line.split(",");
					if (data.length==4)
					{
					
					//System.out.println("Data0: "+data[0]);
					//System.out.println("Data1: "+data[1]);
					//System.out.println("Data2: "+data[2]);
					//System.out.println("Data3: "+data[3]);
					
					
					//Search through the store code-
					//array for a match. Use the match-
					//location as the array row #.
					
						for (iProduct = 1; iProduct<=13; iProduct++)
						{
							if (data[2].equals(sProductList[iProduct][0])) break;
						}
						
						
						
					for (iStore = 1; iStore<=15; iStore++)
					{
						if (data[0].equals(sStoreNum[iStore][0])) break;
					}
					
					//Extract the month number out of the date
					
					iMonth = Integer.parseInt(data[1]);
					iMonth = (iMonth / 100) % 100;
					if ((iMonth < 1) | (iMonth>12)) iMonth = 13;
					
					//Convert the Sales Amount from a-
					//string into a numeric value.
					
					dSalesAmt = Double.parseDouble(data[3]);
					
					//Populate the table with the sales amount 
					
					dTableOfData [iStore][iProduct][iMonth] += dSalesAmt;	//Prov & month sales
					dTableOfData [iStore][iProduct][     0] += dSalesAmt;	//Grand total
					dTableOfData [iStore][       0][iMonth] += dSalesAmt;	//Prov total for the year
					dTableOfData [     0][       0][     0] += dSalesAmt;	//Month total for all provs
					
					dTableOfData [     0][iProduct][iMonth] += dSalesAmt;	
					dTableOfData [     0][iProduct][     0] += dSalesAmt;	
					dTableOfData [     0][       0][iMonth] += dSalesAmt;	
					dTableOfData [     0][       0][     0] += dSalesAmt;	
					
					
					}
				}
	} while (!done);
	
	//All data has been read from the file
	
	//Now print the report!
	
	// loop through each store 

	for (iStore = 0; iStore<=16; iStore++)
	{
		sValue = padRString(sStoreNum[iStore][1],25);
		
		
		System.out.print(sValue);
	
	NumberFormat nf = new DecimalFormat("##,###,###.00");
	NumberFormat pf = new DecimalFormat("###.0");
	int iLen = 13;
	
	System.out.println ("                                                     "	//53 spaces
					  + "2016 SALES for ABC ROAD SYSTEMS LTD..");
	System.out.println ("PRODUCTS                 JANUARY      FEBRARY      MARCH        APRIL"
					  + "        MAY          JUNE         JULY         AUGUST       SEPTEMBER    OCTOBER"
					  + "      NOVEMBER     DECEMBER    UNKNOWN MONTH YEAR TOTAL    %");
	System.out.println ("------------------------ ----------   ---------   ----------   ----------"
					  + "    --------     ----------   ----------   ----------   ----------   ---------"
	                  + "    ----------   ----------  ------------- ----------   -----");
	
	for (iProduct = 1; iProduct<=14; iProduct++)
	{
		//Print full product name
		sValue = padRString(sProductList[iProduct][1],25);
		
		System.out.print(sValue);
		
		//Loop through the columns and print the months
		for (iMonth = 1; iMonth<=13; iMonth++) 
			{
				sValue = padRString(nf.format(dTableOfData [iStore][iProduct][iMonth]),iLen);
				System.out.print(sValue);
				
			}
			
			//Print total for each product (Row)
			
			sValue = padRString(nf.format(dTableOfData [iStore][iProduct][0]),iLen);//
			System.out.print(sValue);
			
			//Print percentage for the province (Row)
			
			sValue = padLString(pf.format(100.0 * dTableOfData [iStore][iProduct][0] / dTableOfData [iStore][0][0]),3);
			System.out.println(sValue+"%");
			
		}
	
		//Print column totals
		
		System.out.println ("=======================  ==========   ==========   ==========   =========="
				          + "   ==========   ==========   ==========   ==========   ==========   =========="
				          + "   ==========   ==========   ==========   ==========     =====");
		//Loop through the columns and print the monthly totals
		System.out.print("                         ");
		for (iProduct = 1; iProduct<=12; iProduct++)
		{
			sValue = padRString(nf.format(dTableOfData [iStore][iProduct][0]),iLen);//
			System.out.print(sValue);
			
		}
		
		//Print the yearly totals
		
		sValue = padRString(nf.format(dTableOfData [iStore][iProduct][0]),iLen);//
		System.out.print(sValue);
		
		
		//Print total for the province (Row)
		
		sValue = padLString(pf.format(dTableOfData [iStore][0][0]),5);//
		System.out.print(sValue);
		
		//Print percentage total for the province (Row)
		
		sValue = padLString(pf.format(100.0 * dTableOfData [iStore][iProduct][0] / dTableOfData [iStore][0][0]),5);
		System.out.println(sValue+"%");
		
			
	}	
	}

	public static String padRString (String sName, int iWidth)
	{
		
		while (sName.length()<iWidth)
		{
			sName+=" ";
		}
		
		return sName;
		
	}
	
	
	public static String padLString (String sName, int iWidth)
	{
		while (sName.length()<iWidth)
		{
			sName=" "+sName;

		}
		
		return sName;
	}
	
}
