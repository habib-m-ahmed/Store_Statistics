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
		
		File f = new File ("WorkingExampleDataFile.txt");
		
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

				String sProvs [] [] = {
						{"  ", "TOTAL"},
						{"BC", "British Columbia"},
						{"AB", "Alberta"},
						{"SK", "Saskatchewan"},
						{"MB", "Manitoba"},
						{"ON", "Ontario"},
						{"QC", "Quebec"},
						{"NB", "New Bruinswick"},
						{"NS", "Nova Scotia"},
						{"PE", "Prince Edward Island"},
						{"NL", "Newfoundland & Labrador"},
						{"??", "Unknown"},		
				};
				
				double dSalesAmt = 0; //Temp variable to hold the sales amount
				double [] [] dTableOfData = new double [12] [14];
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
				int iRow = 0;				//  IROW is used to position the row in DATATABLE
				int iCol = 0;				//  ICOl is used to position the column in DATATABLE
				
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
					//System.out.println("Data0: "+data[0]);
					//System.out.println("Data1: "+data[1]);
					//System.out.println("Data2: "+data[2]);
					
					//Search through the province code-
					//array for a match. Use the match-
					//location as the array row #.
					
					for (iRow = 1; iRow<=10; iRow++)
					{
						if (data[0].equals(sProvs[iRow][0])) break;
					}
					
					//Extract the month number out of the date
					
					iCol = Integer.parseInt(data[1]);
					iCol = (iCol / 100) % 100;
					if ((iCol < 1) | (iCol>12)) iCol = 13;
					
					//Convert the Sales Amount from a-
					//string into a numeric value.
					
					dSalesAmt = Double.parseDouble(data[2]);
					
					//Populate the table with the sales amount 
					
					dTableOfData [iRow][iCol] += dSalesAmt;		//Prov & month sales
					dTableOfData [   0][   0] += dSalesAmt;		//Grand total
					dTableOfData [iRow][   0] += dSalesAmt;		//Prov total for the year
					dTableOfData [   0][iCol] += dSalesAmt;		//Month total for all provs
					
				}
	} while (!done);
	
	//All data has been read from the file
	
	//Now print the report!
	
	NumberFormat nf = new DecimalFormat("##,###,###.00");
	NumberFormat pf = new DecimalFormat("###.0");
	int iLen = 13;
	
	System.out.println ("                                                     "	//53 spaces
					  + "2016    SALES    for    ROAD    SYSTEMS    LTD.");
	System.out.println ("PROVINCE                      JANUARY      FEBRARY      MARCH      APRIL"
					  + "         MAY          JUNE         JULY       AUGUST     SEPTEMBER   OCTOBER"
					  + "     NOVEMBER     DECEMBER    YEAR TOTAL    %");
	System.out.println ("------------------------     ----------   ---------   ----------   --------"
					  + "   ----------   ----------   ----------   ----------  ----------  ----------"
	                  + "   ----------   ----------   ----------  -----");
	
	for (iRow = 1; iRow<=11; iRow++)
	{
		//Print full province name
		sValue = sProvs[iRow][1];
		while (sValue.length() < 25) {sValue += " ";}
		System.out.print(sValue);
		
		//Loop through the columns and print the monthly totals
		for (iCol = 1; iCol<=12; iCol++) 
			{
				sValue = nf.format(dTableOfData [iRow][iCol]);
				while (sValue.length() < iLen) {sValue = " "+sValue;}
				System.out.print(sValue);
				
			}
			
			//Print total for the province (Row)
			
			sValue = pf.format(dTableOfData [iRow][0]);
			while (sValue.length() < iLen) {sValue = " "+sValue;}
			System.out.print(sValue);
			
			//Print percentage for the province (Row)
			
			sValue = pf.format(100.0 * dTableOfData [iRow][0] / dTableOfData [0][0]);
			while (sValue.length() < 5) {sValue = " "+sValue;}
			System.out.println(sValue+"%");
			
		}
		
		//Print column totals
		
		System.out.println ("=======================      ==========    ==========     ==========     =========="
				          + "   ==========   ==========    ==========    ==========     ==========     =========="
				          + "   ==========   ==========    ==========  =====");
		//Loop through the columns and print the monthly totals
		System.out.print("                         ");
		iRow=0;
		for (iCol = 1; iCol<=12; iCol++)
		{
			sValue = nf.format(dTableOfData [iRow][iCol]);
			while (sValue.length() < iLen) {sValue = " "+sValue;}
			System.out.print(sValue);
			
		}
		
		//Print total for the province (Row)
		
		sValue = pf.format(dTableOfData [iRow][0]);
		while (sValue.length() <iLen) {sValue = " "+sValue;}
		System.out.print(sValue);
		
		//Print percentage for the province (Row)
		
		sValue = pf.format(100.0 * dTableOfData [iRow][0] / dTableOfData [0][0]);
		while (sValue.length() < 5) {sValue = " "+sValue;}
		System.out.println(sValue+"%");
		
			
		}
	}
