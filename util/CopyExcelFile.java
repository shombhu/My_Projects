package com.ecrm.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;


public class CopyExcelFile {

    public static XSSFWorkbook inputWorkbook = null;


    public CopyExcelFile(String filePathSource, String filePathDest, String dataFileName) throws IOException {
        // Step Locate path and file name of target and output excel.
        String SourceSheetPathAndName = filePathSource+dataFileName;
        String DestPathAndName = filePathDest+dataFileName;
        if (SourceSheetPathAndName != null && !"".equals(SourceSheetPathAndName.trim())) {
            try {
                File sourceFile = new File(SourceSheetPathAndName.trim());
                FileInputStream inputStream = new FileInputStream(sourceFile);
                inputWorkbook = new XSSFWorkbook(inputStream);
                int sourceSheetCount = inputWorkbook.getNumberOfSheets();
                System.out.println("Total no. of sheet(s) in the Source Workbook: " + sourceSheetCount);
                File outputFile = new File(DestPathAndName.trim());
                FileOutputStream outputStream = new FileOutputStream(outputFile);
                XSSFWorkbook outputWorkbook = new XSSFWorkbook();
// Creating sheets with the same name as appearing in target workbook.
                for (int i = 0; i < sourceSheetCount; i++) {
                    XSSFSheet targetSheet = inputWorkbook.getSheetAt(i);
                    String inputSheetName = inputWorkbook.getSheetName(i);
                    XSSFSheet outputSheet = outputWorkbook.createSheet(inputSheetName);
                    copyExcelWB(targetSheet, outputSheet);
                }
// Write all the sheets in the new Workbook using FileOutStream Object (Step 3 is mentioned below)
                outputWorkbook.write(outputStream);
                outputStream.close();
            }
            catch (Exception ex) {
                System.out.println("Please check the Source sheet given path and name: " + SourceSheetPathAndName);
                System.out.println();
                ex.printStackTrace();
            }
        }
    }
    public static void copyExcelWB(XSSFSheet targetSheet, XSSFSheet outputSheet) {
        int rowCount = targetSheet.getLastRowNum();
        System.out.println("There are " + rowCount + " rows in the Target workbook with sheet name -" + "'"
        + targetSheet.getSheetName() + "'");
        int currentRowIndex = 0;
        if (rowCount > 0) {
            Iterator<Row> rowIterator = targetSheet.iterator();
            while (rowIterator.hasNext()) {
                int currentCellIndex = 0;
                Iterator<Cell> cellIterator = ((Row) rowIterator.next()).cellIterator();
                while (cellIterator.hasNext()) {

//  Creating new Row, Cell and Input value in the newly created sheet.
                    String cellData = cellIterator.next().toString().replaceAll(".0","");
                    if (currentCellIndex == 0)
                        outputSheet.createRow(currentRowIndex).createCell(currentCellIndex).setCellValue(cellData);
                    else
                        outputSheet.getRow(currentRowIndex).createCell(currentCellIndex).setCellValue(cellData);
                    currentCellIndex++;
                }
                currentRowIndex++;
            }
            System.out.println("Total " + (currentRowIndex - 1) + " rows are Copied in the new Workbook with sheet name- "
            + "'" + outputSheet.getSheetName() + "'");
        }
    }

    public int getTotalSheetCount() {
        return inputWorkbook.getNumberOfSheets();
    }
    public String getCurrentSheet(int i) {
        System.out.println("Current Sheet Name is: "+inputWorkbook.getSheetName(i));
        return inputWorkbook.getSheetName(i);
    }
}




