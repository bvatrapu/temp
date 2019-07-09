package com.SAP.framework.utils.drivergeneric;

import com.SAP.framework.utils.framework.Log;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Keyboard {

    public static Robot robot;
	
	//SAP - Methods - START

    /**
     * Method Description :: Press Key
     * @param keyEvent
     */
    public void keyPress(int keyEvent){
        try {
            Robot robot = new Robot();

            Thread.sleep(1000);
            robot.keyPress(keyEvent);
            robot.keyRelease(keyEvent);
            Thread.sleep(1000);
        }catch (Exception e){
            Log.error("Exception in keyPress :"+ e.getMessage());
        }
    }
    /*  using Robot */

    /**
     * Method Description :: To Press ENTER Key
     */
    public void hitEnter(){
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }catch (Exception e){
            Log.error("Exception in hitEnter :"+ e.getMessage());
        }

    }

    /**
     * Method Description :: To Press BACKSPACE Key
     */
    public void hitBackspace() {
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        } catch (Exception e){
            Log.error("Exception in hitBackspace :"+ e.getMessage());
        }
    }

    /**
     * Method Description :: To Press DELETE Key
     */
    public void hitDelete() {
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_DELETE);
            robot.keyRelease(KeyEvent.VK_DELETE);
        }catch (Exception e){
            Log.error("Exception in hitBackspace :"+ e.getMessage());
        }
    }

    /**
     * Method Description :: To Select all the Text/Web Elements
     */
    public void selectAll() {
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_A);
        }catch (Exception e){
            Log.error("Exception in selectAll :"+ e.getMessage());
        }
    }
       /**
     * Method Description :: To Copy all the Selected Text/Web Elements
     */
    public void copyAll() {
        try{
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_C);
        }catch (Exception e){
            Log.error("Exception in selectAll :"+ e.getMessage());
        }

    }

    /**
     * Method Description :: To Paste all the Selected Text/Web Elements
     */
    public void pasteAll(){
        try{
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
        }catch (Exception e){
            Log.error("Exception in selectAll :"+ e.getMessage());
        }

    }
    /**
     * Method Description :: To ZoomOut
     */
    public void robotZoomOut(){
        try{
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }catch (Exception e){
            Log.error("Exception in selectAll :"+ e.getMessage());
        }

    }

    /**
     * Method Description :: To ZoomIn
     */
    public static void robotZoomIn(){
        try{
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ADD);
            robot.keyRelease(KeyEvent.VK_ADD);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }catch (Exception e){
            Log.error("Exception in selectAll :"+ e.getMessage());
        }

    }
    /**
     * Method Description :: To ScrollUp
     */
    public static void robotScrollUp(){
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_PAGE_UP);
            robot.keyRelease(KeyEvent.VK_PAGE_UP);
        }catch (Exception e){
            Log.error("Exception in selectAll :"+ e.getMessage());
        }
    }
    /**
     * Method Description :: To ScrollDown
     */
    public void robotScrollDown(){
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_PAGE_DOWN);
            robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
        }catch (Exception e){
            Log.error("Exception in selectAll :"+ e.getMessage());
        }
    }
	
}
//SAP - Methods - END