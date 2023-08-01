package com.ecrm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ecrm.base.TestBase;

public class CommonUtility extends TestBase
{

	/** This method returns the time stamp in a specific date-time format i.e. dd_MM_yyyy_HH_mm_ss
	 * @return date-time as string value
	 * @author Akshay Yelsange
	 */
	public static String getDateTime()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
         Date date = new Date();
         String currentDateTime = formatter.format(date);

        return currentDateTime;
    }
}
