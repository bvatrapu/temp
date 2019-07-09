package com.SAP.framework.utils.drivergeneric;

import com.SAP.framework.base.ConfigTestData;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;

public class Mouse {
    public static Robot robot;
    private WebDriver driver=null;
    public Mouse(ConfigTestData configTestData){
        this.driver=configTestData.driver;
    }

//SAP - Methods - START

    /**
     * Method Description :: To mouse hover on element
     * @param referenceElement
     */
    public void hover(WebElement referenceElement) {
        try {
            Point point;
            Robot robot = new Robot();

            point = referenceElement.getLocation();
            Thread.sleep(1000);
            robot.mouseMove(point.getX() - 10, point.getY() - 5);
            Thread.sleep(1000);
        }catch (Exception e){

        }
    }

    /**
     * Method Description :: To mouse click on web element
     * @param referenceElement
     * @param mouseButton
     */
    public void click(WebElement referenceElement, String mouseButton){
        Actions oAction = new Actions(driver);
        switch (mouseButton.toLowerCase()) {
            case "left":
                oAction.build().perform();
                break;
            case "right":
                oAction.contextClick(referenceElement).build().perform();
                break;
        }

    }
    /* To Move cursor to the Desired Location */

    /**
     *  Method Description :: To move mouse Cursor on X and Y position
     * @param X_Position
     * @param Y_Position
     */
    public void moveCursor(int X_Position, int Y_Position)  {
        robot.mouseMove(X_Position, Y_Position);
    }

}
//SAP - Methods - END