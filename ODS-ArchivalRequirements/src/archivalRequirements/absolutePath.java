package archivalRequirements;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import java.io.*;

public class absolutePath {

    // Check for absolute path to local directory using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath) throws Exception {
        boolean absPath = false;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        String absolutePath = spreadsheet.getAbsoluteFilePath(filepath);
        if (absolutePath != null) {
            absPath = true;
        }
        spreadsheet.close();

        if (absPath) {
            System.out.println("Absolute path was detected");
        }
        return  absPath;
    }

    // Remove absolute path to local directory using ODF Toolkit
    public boolean Change_ODFToolkit(String filepath) throws Exception {
        boolean absPath = false;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        String absolutePath = spreadsheet.getAbsoluteFilePath(filepath);
        if (absolutePath != null) {
            absPath = true;
            // DO SOMETHING HERE
        }
        spreadsheet.close();

        if (absPath) {
            System.out.println("Absolute path was removed");
        }
        return  absPath;
    }

    // Check for absolute path to local directory using Apache POI
    public boolean Check_ApachePOI(String filepath) throws IOException {
        boolean absPath = false;

        // Find spreadsheet and create workbook instance
        FileInputStream fileInput = new FileInputStream(filepath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInput);



        workbook.close();
        fileInput.close();

        // Inform user and return result
        if (absPath) {
            System.out.println("Absolute path to local directory was detected");
        }
        return  absPath;
    }

    // Remove absolute path to local directory using Apache POI
    public boolean Change_ApachePOI(String filepath) throws IOException {
        boolean absPath = false;

        FileInputStream fileInput = new FileInputStream(filepath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInput);



        // Save and close file
        FileOutputStream fileOutput = new FileOutputStream(filepath);
        workbook.write(fileOutput);
        workbook.close();
        fileOutput.close();
        fileInput.close();

        // Inform user and return result
        if (absPath) {
            System.out.println("Absolute path to local directory was removed");
        }
        return  absPath;
    }
}
