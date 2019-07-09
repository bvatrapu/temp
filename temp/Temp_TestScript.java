package com.testProject.testSuites;

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
import com.testProject.testConstants.GlobalConstants;
import org.testng.annotations.Test;

import java.io.File;

public class Temp_TestScript extends TestBase {

    @Test(groups=)
    public void Temp_TestScript() throws Exception {
        //SAP-START - VARIABLE DECLARAITON
            configTestData.jsonObject = generic.parseStringToJsonObject(GlobalConstants.TEST_DATA_FILE_PATH+ configTestData.testModule+File.separator+configTestData.testMethod_Name.toUpperCase()+".json");
        //SAP-END - VARIABLE DECLARAITON

        //SAP-START - TEST SCRIPT
        //SAP-END - TEST SCRIPT
    }

}
