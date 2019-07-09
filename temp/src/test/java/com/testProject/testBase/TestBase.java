package com.testProject.testBase;

//SAP-START   - IMPORT DECLARATION
import com.SAP.framework.base.ConfigBase;
import com.SAP.framework.utils.drivergeneric.*;
import com.SAP.framework.utils.generic.Generic;
import org.testng.annotations.BeforeMethod;
//SAP-END   - IMPORT DECLARATION

public class TestBase extends ConfigBase {

    //SAP-START   - PAGE DECLARATION

	//SAP-END   - PAGE DECLARATION

    @BeforeMethod(alwaysRun = true)
    public void initTestMethods(){
        initilizePages();
    }

    public void initilizePages(){
        //SAP-START   - PAGE INITILAZATION
        browser=new Browser(configTestData);
        element=new Element(configTestData);
        keyboard=new Keyboard();
        mouse=new Mouse(configTestData);
        wait=new Wait(configTestData);
        generic=new Generic(configTestData);
	   //SAP-END   - PAGE INITILAZATION
    }
}





