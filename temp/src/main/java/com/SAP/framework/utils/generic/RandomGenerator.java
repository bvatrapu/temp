package com.SAP.framework.utils.generic;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author Bharath
 * @Date 19-March-2018
 */

public class RandomGenerator {

	public static Random rand = new Random();
	public static DateAndTime dateAndTime = new DateAndTime();
	
	//SAP - Methods - START

	/**
	 * Method Description :: To Generate Random Numbers of the Entered Length.
	 * @param length
	 * @return
	 */
	public static String randomNumber(int length){

		if (length > 18)
			throw new IllegalStateException("To many digits");
		long tLen = (long) Math.pow(10, length - 1) * 9;

		long number = (long) (Math.random() * tLen) + (long) Math.pow(10, length - 1) * 1;

		String randomValue = number + "";
		if (randomValue.length() != length) {
			throw new IllegalStateException("The random number '" + randomValue + "' is not '" + length + "' digits");
		}
		return randomValue;
	}
	/**
	 * Method Description :: To Generate Random Capitalized Letters of the Entered Length.
	 * @param length
	 * @return
	 */
	public static String randomCapitalizedString(int length){
		StringBuffer output = new StringBuffer(1000);
		output.append((char)(rand.nextInt(26) + 'A'));

		for(int i = 1; i < 10; i++)		{
			char c = (char)(rand.nextInt(26) + 'a');
			output.append(c);
		}
		String RandCapitalizedString = output.toString();
		return RandCapitalizedString;
	}
	/**
	 * Method Description :: To Generate Random Small Letters of the Entered Length.
	 * @param length
	 * @return
	 */
	public static String randomSmallLetters(int length)	{
		StringBuffer output = new StringBuffer(1000);
		output.append((char)(rand.nextInt(26) + 'a'));

		for(int i = 1; i < 10; i++)		{
			char c = (char)(rand.nextInt(26) + 'a');
			output.append(c);
		}
		String RandSmallLetters = output.toString();
		return RandSmallLetters;
	}
	/**
	 * Method Description :: To Generate Random Capital Letters of the Entered Length.
	 * @param length
	 * @return
	 */
	public static String randomCapitalLetters(int length){
		StringBuffer output = new StringBuffer(1000);
		output.append((char)(rand.nextInt(26) + 'A'));

		for(int i = 1; i < 10; i++)		{
			char c = (char)(rand.nextInt(26) + 'A');
			output.append(c);
		}
		String RandCapitalLetters = output.toString();

		return RandCapitalLetters;
	}
	/**
	 * Method Description :: To Generate Random Alpha-Numeric Characters of the Entered Length.
	 * @param length
	 * @return
	 */
	public static String randomAlphaNumericCharacters(int length)	{
		return RandomStringUtils.randomAlphanumeric(length).toString();
	}
	/**
	 * Method Description :: To Generate Random ASCII Characters of the Entered Length.
	 * @param length
	 * @return
	 */
	public static String GenerateRandomASCIICharacters(int length)	{
		return RandomStringUtils.randomAscii(length).toString();
	}
	/**
	 * Method Description :: To Generate Random E-Mail IDs(Auto Generate Domain Names.
	 *
	 * @return
	 */
	public static String randomEMAILIDs() {
		String EmailID = "autotester_"+randomNumber(5) +new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		//String EmailID = "random_" + dateAndTime.getDate() + dateAndTime.getTime();
		//String Domain = RandomStringUtils.randomAlphabetic(7).toLowerCase().toString();
		String Domain = "example";

		return EmailID + "@" + Domain + ".com";
	}
	/**
	 * Method Description :: To Generate Random E-Mail IDs
	 *
	 * @return
	 */
	public static String randomEMAILIDs(String DomainName)	{

		String EmailID = RandomStringUtils.randomAlphabetic(15).toString();
		return EmailID + "@" + DomainName ;
	}
}
//SAP - Methods - END