package ir.expensetracker.util;

import ir.expensetracker.api.AllTransactionsResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {

    private static Logger logger = LogManager.getLogger(ExcelUtil.class);
    public static void saveInExcelFile(String path, List<AllTransactionsResult> transactions){
        try {
            XSSFWorkbook workbook=new XSSFWorkbook();
            XSSFSheet sheet=workbook.createSheet();
            XSSFRow row=sheet.createRow(0);
            Cell cell1=row.createCell(0);
            cell1.setCellValue("Category");
            Cell cell2=row.createCell(1);
            cell2.setCellValue("Description");
            Cell cell3=row.createCell(2);
            cell3.setCellValue("Date");
            Cell cell4=row.createCell(3);
            cell4.setCellValue("Amount");
            for (int i=0;i< transactions.size();i++) {
                AllTransactionsResult trx=transactions.get(i);
                XSSFRow rowValue=sheet.createRow(i+1);
                Cell cellVal1=rowValue.createCell(0);
                cellVal1.setCellValue(trx.getCategory());
                Cell cellVal2=rowValue.createCell(1);
                cellVal2.setCellValue(trx.getDescription());
                Cell cellVal3=rowValue.createCell(2);
                cellVal3.setCellValue(trx.getDate());
                Cell cellVal4=rowValue.createCell(3);
                cellVal4.setCellValue(trx.getAmount());
            }
            workbook.write(new FileOutputStream(path));
            workbook.close();
        } catch (IOException e) {
            logger.error(e,e);
        }
    }
}
