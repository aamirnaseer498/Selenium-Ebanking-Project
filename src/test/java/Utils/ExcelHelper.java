package Utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelHelper {

    public static FileInputStream fileInputStream;
    public static FileOutputStream fileOutputStream;
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public static XSSFRow row;
    public static XSSFCell cell;

    public static int getRowCount(String excelFile, String excelSheet){
        try {
            fileInputStream= new FileInputStream(excelFile);
            workbook= new XSSFWorkbook(fileInputStream);
            sheet= workbook.getSheet(excelSheet);
            int rowCount= sheet.getLastRowNum();
            workbook.close();
            fileInputStream.close();
            return rowCount;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getCellCount(String excelFile, String excelSheet, int rowNum){
        try {
            fileInputStream= new FileInputStream(excelFile);
            workbook= new XSSFWorkbook(fileInputStream);
            sheet= workbook.getSheet(excelSheet);
            row= sheet.getRow(rowNum);
            int cellCount= row.getLastCellNum();
            workbook.close();
            fileInputStream.close();
            return cellCount;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCellData(String excelFile, String excelSheet, int rowNum, int colNum){
        try {
            fileInputStream= new FileInputStream(excelFile);
            workbook= new XSSFWorkbook(fileInputStream);
            sheet= workbook.getSheet(excelSheet);
            row= sheet.getRow(rowNum);
            cell= row.getCell(colNum);
            String data;
            try {
                DataFormatter formatter= new DataFormatter();
                String cellData= formatter.formatCellValue(cell);
                return cellData;
            }catch (Exception e){
                data= "";
            }
            workbook.close();
            fileInputStream.close();
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setCellData(String excelFile, String excelSheet, int rowNum, int colNum, String data){
        try {
            fileInputStream= new FileInputStream(excelFile);
            workbook= new XSSFWorkbook(fileInputStream);
            sheet= workbook.getSheet(excelSheet);
            row= sheet.getRow(rowNum);
            cell= row.createCell(colNum);
            cell.setCellValue(data);
            fileOutputStream= new FileOutputStream(excelFile);
            workbook.close();
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
