package com.SAP.framework.utils.generic;

import org.joda.time.DateTime;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTime {
	
	//SAP - Methods - START

	/**
	 * Method Description :: To get the Current Time
	 * @return
	 */
	public static String getTime() {
		String TimeNow=null;
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("kk_mm");
			TimeNow = dateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TimeNow;
		//getTime() - END
	}
	/**
	 * Method Description :: To get the Current Date
	 * @return
	 */
	public static String getDate(){
		String DateNow=null;
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			DateNow = dateFormat.format(date);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return DateNow;
		//getDate() - END
	}

	/**
	 * Method Description :: To get the Current Month in Integer
	 * @return
	 */
	public static int getMonth_Integer(){
		int month=0;
		try {
			DateTime datetime = DateTime.now();
			month = datetime.getMonthOfYear();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return month;
		//getMonth_Integer() - END
	}
	/**
	 * Method Description :: To get the Current Month Text as Full in String
	 * @return
	 */
	public String getMonth_Full(){
		String month_Full=null;
		try {
			DateTime datetime = DateTime.now();
			month_Full = datetime.monthOfYear().getAsText();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return month_Full;
		//getMonth_Integer() - END
	}
	/**
	 * Method Description :: To get the Current Month Text as Short in String
	 * @return
	 */
	public String getMonth_Short(){
		String month_Short=null;
		try {
			DateTime datetime = DateTime.now();
			month_Short = datetime.monthOfYear().getAsShortText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return month_Short;
		//getMonth_Short() - END
	}
	/**
	 * Method Description :: To get the Current Day of the Month
	 * @return
	 */
	public String getDayOfTheMonth(){
		String dayOfTheMonth=null;
		try {
			DateTime datetime = DateTime.now();
			dayOfTheMonth = datetime.dayOfMonth().getAsText();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dayOfTheMonth;
		//getDayOfTheMonth() - END
	}
	/**
	 * Method Description :: To get the Current Day Count in the Year
	 * @return
	 */
	public String getDayCount(){
		String dayCountYear=null;
		try {
			DateTime datetime = DateTime.now();
			dayCountYear = datetime.dayOfYear().getAsText();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dayCountYear;
		//getDayCount() - END
	}
	/**
	 * Method Description :: To get the Current Minute of the Hour in String
	 * @return
	 */
	public String getMinuteOfTheHourAsString(){
		String minuteOfTheHour=null;
		try {
			DateTime datetime = DateTime.now();
			minuteOfTheHour = datetime.minuteOfHour().getAsText();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return minuteOfTheHour;
		//getMinuteOfTheHourAsString() - END
	}
	/**
	 * Method Description :: To get the Current Year as Integer
	 * @return
	 */
	public int getYear(){
		int year=0;
		try {
			DateTime datetime = DateTime.now();
			year = datetime.getYear();
		}catch (Exception e){
			e.printStackTrace();
		}
		return year;
		//getYear() - END
	}
	/**
	 * Method Description :: To get the Current Hour of the Day in String
	 * @return
	 */
	public String getHourOfTheDay(){
		String hour=null;
		try {
			DateTime datetime = DateTime.now();
			hour = datetime.hourOfDay().getAsShortText();
		}catch (Exception e){
			e.printStackTrace();
		}
		return hour;
		//getHourOfTheDay() - END
	}
	/**
	 * Method Description :: To get the Current Week Count
	 * @return
	 */
	public String getWeekCount(){
		String week =null;
		try {
			DateTime datetime = DateTime.now();
			week = datetime.weekOfWeekyear().getAsText();
		}catch (Exception e){
			e.printStackTrace();
		}
		return week;
		//getWeekCount() - END
	}
	
}
//SAP - Methods - END