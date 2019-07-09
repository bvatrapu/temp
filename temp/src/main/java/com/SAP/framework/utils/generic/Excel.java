package com.SAP.framework.utils.generic;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Excel {
    //Main Directory of the project
    public static final String currentDir = System.getProperty("user.dir");
    public static String testDataExcelFileName;
    //Location of Test data excel file
    public static String testDataExcelPath = null;

    //Excel WorkBook
    private static XSSFWorkbook excelWBook;

    //Excel Sheet
    private static XSSFSheet excelWSheet;

    //Excel cell
    private static XSSFCell cell;

    //Excel row
    private static XSSFRow row;

    //Row Number
    public static int rowNumber;

    //Column Number
    public static int columnNumber;

	//SAP - Methods - START

    /**
     * Method Description :: To set Row Number in Excel Sheet
     * @param pRowNumber
     */
    public static void setRowNumber(int pRowNumber) {
        rowNumber = pRowNumber;
    }
    /**
     * Method Description :: To get Row Number as Integer  from Excel sheet
     */
    public static int getRowNumber() {
        return rowNumber;
    }

    /**
     * Method Description :: To Set Column Number
     * @param pColumnNumber
     */
    public static void setColumnNumber(int pColumnNumber) {
        columnNumber = pColumnNumber;
    }

    /**
     *  Method Description :: To get Column Number
     * @return
     */
    public static int getColumnNumber() {
        return columnNumber;
    }

    /**
     * Method Description :: It creates FileInputStream and set excel file and excel sheet to excelWBook and excelWSheet variables.
     * @param sheetName
     */
    public static void setExcelFileSheet(String sheetName) {
        //MAC or Windows Selection for excel path
        if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
            testDataExcelPath = currentDir + "//src//test//java//resources//";
        } else if (Platform.getCurrent().toString().contains("WIN")) {
            testDataExcelPath = currentDir + "\\src\\test\\java\\resources\\";
        }
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + testDataExcelFileName);
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        } catch (Exception e) {
            try {
                throw (e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Method Description :: This method reads the test data from the Excel cell. passing row number and column number as parameters.
     * @param RowNum
     * @param ColNum
     * @return
     */
    public static String getCellData(int RowNum, int ColNum) {
        try {
            cell = excelWSheet.getRow(RowNum).getCell(ColNum);
            DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
        } catch (Exception e) {
            throw (e);
        }
    }

    /**
     * Method Description :: This method takes row number as a parameter and returns the data of given row number.
     * @param RowNum
     * @return
     */
    public static XSSFRow getRowData(int RowNum) {
        try {
            row = excelWSheet.getRow(RowNum);
            return row;
        } catch (Exception e) {
            throw (e);
        }
    }

    /**
     * Method Description :: This method gets excel file, row and column number and set a value to the that cell.
     * @param value
     * @param RowNum
     * @param ColNum
     */

    public static void setCellData(String value, int RowNum, int ColNum) {
        try {
            row = excelWSheet.getRow(RowNum);
            cell = row.getCell(ColNum);
            if (cell == null) {
                cell = row.createCell(ColNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + testDataExcelFileName);
            excelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            try {
                throw (e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
	
}
//SAP - Methods - END