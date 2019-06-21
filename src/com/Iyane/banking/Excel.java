package com.Iyane.banking;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excel {
    MgmtAccount mgmt = new MgmtAccount();

    public void readExecl() throws IOException {
        FileInputStream excel = new FileInputStream("Banking.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(excel);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getPhysicalNumberOfRows();

        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            XSSFRow row = sheet.getRow(rowIndex);
            if (row != null) {
                XSSFCell value1 = row.getCell(0);
                XSSFCell value2 = row.getCell(1);
                XSSFCell value3 = row.getCell(2);
                XSSFCell value4 = row.getCell(3);
                XSSFCell value5 = row.getCell(4);

                String accountNumber = value1.getStringCellValue().substring(0, value1.getStringCellValue().length() + 0);
                String password = value2.getStringCellValue().substring(0, value2.getStringCellValue().length() + 0);
                String name = value3.getStringCellValue();
                String birth = value4.getStringCellValue().substring(0, value4.getStringCellValue().length() + 0);
                double balance = value5.getNumericCellValue();

                mgmt.accounts.add(new Account(accountNumber, password, name, birth, Math.round(balance)));
            }
        }
    }

    public void writeExcel() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Banking");

        for (int rowIndex = 0; rowIndex < mgmt.accounts.size(); rowIndex++) {
            XSSFRow row = sheet.createRow(rowIndex);
            for (int columnIndex = 0; columnIndex < 5; columnIndex++) {
                XSSFCell cell = row.createCell(columnIndex);
                switch (columnIndex) {
                    case 0:
                        cell.setCellValue(mgmt.accounts.get(rowIndex).getAccountNumber());
                        break;
                    case 1:
                        cell.setCellValue(mgmt.accounts.get(rowIndex).getPassword());
                        break;
                    case 2:
                        cell.setCellValue(mgmt.accounts.get(rowIndex).getName());
                        break;
                    case 3:
                        cell.setCellValue(mgmt.accounts.get(rowIndex).getBirth());
                        break;
                    case 4:
                        cell.setCellValue(mgmt.accounts.get(rowIndex).getBalance());
                        break;
                    default:
                        cell.setCellValue("ERROR");
                }
                FileOutputStream excel = new FileOutputStream("Banking.xlsx");
                workbook.write(excel);
                excel.close();
            }
        }

    }
}
