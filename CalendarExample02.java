// The "CalendarExample02" class.
import java.awt.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarExample02
{
    
    public static void main (String[] args)
    {
       
        Calendar cDate1 = Calendar.getInstance();
        cDate1 = Calendar.getInstance ();
        
        DateDisplay("Current Time",cDate1);
        
        cDate1.add(Calendar.DAY_OF_MONTH, 0-cDate1.get(Calendar.DAY_OF_MONTH)+1);
        DateDisplay("Beginning of Month",cDate1);
        
        cDate1.add(Calendar.MONTH, -9);
        DateDisplay("9 months ago",cDate1);

        cDate1.set(Calendar.DAY_OF_WEEK,2);
        DateDisplay("Day of Week set to Monday", cDate1);

        cDate1.add(Calendar.MONTH, 1);
        DateDisplay("Add 1 month",cDate1);
        
        cDate1.add(Calendar.MONTH, 1);
        DateDisplay("Add 1 month",cDate1);
        
        cDate1.add(Calendar.MONTH, 1);
        DateDisplay("Add 1 month",cDate1);
        
        cDate1.set(Calendar.DAY_OF_WEEK,2);
        DateDisplay("Day of Week set to Monday", cDate1);
        
        cDate1.add(Calendar.DAY_OF_MONTH, 7);
        DateDisplay("Add 7 days",cDate1);
        
        cDate1.add(Calendar.DAY_OF_MONTH, 7);
        DateDisplay("Add 7 days",cDate1);
        
        cDate1.add(Calendar.DAY_OF_MONTH, 7);
        DateDisplay("Add 7 days",cDate1);
        
        cDate1.add(Calendar.DAY_OF_MONTH, 7);
        DateDisplay("Add 7 days",cDate1);
        
        cDate1.add(Calendar.DAY_OF_MONTH, 7);
        DateDisplay("Add 7 days",cDate1);
        
        cDate1.add(Calendar.DAY_OF_MONTH, 7);
        DateDisplay("Add 7 days",cDate1);

    } // main method
    
    private static void DateDisplay(String sPass, Calendar cDate)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MMM.dd 'at' hh:mm:ss a zzz");
        int iMonth = cDate.get(Calendar.MONTH);
        int iDay   = cDate.get(Calendar.DAY_OF_MONTH);
        int iYear  = cDate.get(Calendar.YEAR);
        System.out.println (sPass + " " + formatter.format(cDate.getTime()));
        int iDOW   = cDate.get(Calendar.DAY_OF_WEEK);
        System.out.println("" + iMonth + "," + iDay + "," + iYear + "," + iDOW);
    } 
} // CalendarExample class
