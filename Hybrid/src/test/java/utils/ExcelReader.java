package utils;

import java.io.FileInputStream;



import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    public static String cellValue;
    public static String readData(int sheetNum, int rowNum, int cellNum){
        
        try{
        FileInputStream fis = new FileInputStream("./testdata/data.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(sheetNum);
        XSSFRow row = sheet.getRow(rowNum);
        XSSFCell cell = row.getCell(cellNum);
        cellValue = cell.getStringCellValue();
        wb.close();
        fis.close();
        }catch(Exception e){
            e.printStackTrace();
            LoggerHandler.error(e.getMessage());
        }
        return cellValue;
    }
}
