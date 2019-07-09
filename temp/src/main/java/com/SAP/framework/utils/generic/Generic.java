package com.SAP.framework.utils.generic;

import com.SAP.framework.base.ConfigTestData;
import com.SAP.framework.utils.framework.Log;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Generic {

    static ResourceBundle rbConfig =null;
    ConfigTestData configTestData=null;

    public Generic(ConfigTestData configTestData){
        this.configTestData=configTestData;
    }
    //SAP - Methods - START
    /**
     * Method Description :: To get the host OS name
     * @return
     */
    public String getCurretnPlatform(){
        String platform = null;
        if(platform == null){
            String osname = System.getProperty("os.name").toLowerCase();
            if(osname.contains("win")){
                platform = Platform.WINDOWS.toString();
            } else if(osname.contains("nix") || osname.contains("nux") || osname.contains("aix")){
                platform=Platform.LINUX.toString();
            } else if(osname.contains("mac")){
                platform=Platform.MAC.toString();
            }
        }
        return platform;
    }

    /**
     * Method Description :: To get the ComputerName
     * @return
     */
    public String getComputerName(){
        String hostname = "Unknown";
        try
        {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        }
        catch (UnknownHostException ex) {
            ex.fillInStackTrace();
        }
        return (hostname);
    }

    /**
     * Method Description :: Load the Config properties file from test source
     */
    // Load the Config properties file from test source
    public void loadConfigProp(){
        try {
            File file = new File(ConfigTestData.PROJECT_CONFIG_PROP_FILE_PATH);
            URL[] urls = {file.toURI().toURL()};
            ClassLoader loader = new URLClassLoader(urls);
            rbConfig = ResourceBundle.getBundle("config", Locale.getDefault(), loader);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method Description :: Read the Config properties file from test source
     * @param key
     * @return
     */
    public String readConfigProp(String key){
        try {
            loadConfigProp();
        } catch (Exception e){
            e.printStackTrace();
        }
        return rbConfig.getString(key);
    }

    /**
     * Method Description :: Read any text file and return as String format
     * @param filepath
     * @return
     * @throws IOException
     */
    public String readFile(String filepath) throws IOException {
        File file = new File(filepath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        try {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while(line!=null){
                stringBuilder.append(line);
                stringBuilder.append("\n");
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();

        } finally {
            bufferedReader.close();
        }
    }

    /**
     * Method Description :: Trim the String leading zeros
     * @param stext
     * @return
     */
    public String trimLeadingZeros(String stext) {
        String sreturn_text = null;
        try {
            Double value = Double.parseDouble(stext);
            sreturn_text = value.toString();

        } catch (Exception e) {
            Log.error("Exception in generic_TrimLeadingZeros :"+ e.getMessage());
        }
        return sreturn_text;
    }

    /**
     * Method Description :: Verify Text actual and expected
     * @param sActualText
     * @param sExpectedText
     * @param verifyTextOptions
     * @return
     */
    public String verifyText(String sActualText, String sExpectedText, String verifyTextOptions){
        boolean result=true;
        try{
            switch (verifyTextOptions) {
                case "EXACTMATCH":
                    result = sActualText.equals(sExpectedText);
                    break;
                case "EXACTMATCHIGNORECASE":
                    sActualText=sActualText.trim();
                    sExpectedText=sExpectedText.trim();
                    result=sActualText.equalsIgnoreCase(sExpectedText);
                    break;
                    case "PARTIAL":
                    sActualText=sActualText.trim().toLowerCase();
                    sExpectedText=sExpectedText.trim().toLowerCase();
                    result = sActualText.contains(sExpectedText);
                    break;
            }

        }catch (Exception e) {
            result=false;
            Log.error("Exception in verifyText :"+ e.getMessage());
        }
        return String.valueOf(result);
    }
	/**
     * Method Description :: Get String value from json file
	 */
	public JSONObject parseStringToJsonObject(String filePath){
        JSONObject jsonObject = null;
        try {
            // read the json file
            System.out.println("jsonFile:"+filePath);
            FileReader reader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
//SAP - Methods - END
