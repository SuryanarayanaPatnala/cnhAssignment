package dataProvider;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcel {
    FileInputStream fis;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    public ReadExcel() throws IOException {
        fis=new FileInputStream(new File(ConfigFileReader.getExcelPath()));
        workbook=new XSSFWorkbook(fis);

    }
    public XSSFSheet getSheet(){
        XSSFSheet sheet=workbook.getSheet("Sheet1");
        return sheet;
    }
}
