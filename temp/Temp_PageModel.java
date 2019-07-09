package com.testProject.testPages;

import com.SAP.framework.base.ConfigTestData;
import com.SAP.framework.utils.framework.Log;
import com.SAP.framework.utils.drivergeneric.Browser;
import com.SAP.framework.utils.drivergeneric.Element;
import com.SAP.framework.utils.drivergeneric.Keyboard;
import com.SAP.framework.utils.drivergeneric.Mouse;
import com.SAP.framework.utils.drivergeneric.Wait;
import com.SAP.framework.utils.generic.DateAndTime;
import com.SAP.framework.utils.generic.Excel;
import com.SAP.framework.utils.generic.Generic;
import com.SAP.framework.utils.generic.RandomGenerator;
import com.testProject.testBase.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class Temp_PageModel extends TestBase {
private ConfigTestData configTestData;
//SAP-START - CONSTRUCTOR DECLARAITON
    public Temp_PageModel(ConfigTestData configTestData) {
		browser=new Browser(configTestData);
		element=new Element(configTestData);
		keyboard=new Keyboard();
		mouse=new Mouse(configTestData);
		wait=new Wait(configTestData);
		generic=new Generic(configTestData);
		this.configTestData=configTestData;
        PageFactory.initElements(configTestData.driver,this);
    }
//SAP-END - CONSTRUCTOR DECLARAITON

//SAP-START - VARIABLE DECLARAITON
    List<WebElement> pageObjElements = new ArrayList<WebElement>();
//SAP-END - VARIABLE DECLARAITON
			
//SAP-START - OBJECT DECLARAITON
//SAP-END - OBJECT DECLARAITON
		
//SAP-START - OPERATION DECLARAITON
//SAP-END - OPERATION DECLARAITON
		
//SAP-START - METHOD DECLARAITON
//SAP-END - METHOD DECLARAITON
}
