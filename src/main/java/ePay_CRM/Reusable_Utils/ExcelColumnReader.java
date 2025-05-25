package ePay_CRM.Reusable_Utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ExcelColumnReader {

    // Method to read column-wise data from Excel
    public Map<String, List<String>> readColumnWiseData(String filePath, String sheetName) {
        Map<String, List<String>> columnData = new HashMap<>();
        filePath=System.getProperty("user.dir")+"//src//main//resources//FetchData//"+filePath+".xlsx";
     
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("❌ Sheet not found: " + sheetName);
            }

            Row headerRow = sheet.getRow(0); // Assume first row contains column headers
            int columnCount = headerRow.getLastCellNum();

            // Initialize lists for each column
            for (int i = 0; i < columnCount; i++) {
                columnData.put(headerRow.getCell(i).getStringCellValue(), new ArrayList<>());
            }

            // Extract data for each column from subsequent rows
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    for (int colIndex = 0; colIndex < columnCount; colIndex++) {
                        Cell cell = row.getCell(colIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                        String cellValue = (cell == null) ? "" : cell.toString().trim();
                        columnData.get(headerRow.getCell(colIndex).getStringCellValue()).add(cellValue);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error reading Excel file: " + e.getMessage());
        }

        return columnData;
    }
}