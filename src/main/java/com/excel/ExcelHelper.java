package com.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "Students";
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Student> getStudentsDataFromExcel(InputStream inputStream){
        try{

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<Student> students = new ArrayList<>();

            int rowIndex = 0;
            while(rows.hasNext()) {
                Row currentRow = rows.next();

                if(rowIndex == 0){
                    rowIndex++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Student student = new Student();

                int cellIndex = 0;
                while(cellsInRow.hasNext()) {
                    Cell currCell = cellsInRow.next();
                    switch (cellIndex) {

                        case 0:
                            student.setId((long) currCell.getNumericCellValue());
                            break;

                        case 1:
                            student.setName(currCell.getStringCellValue());
                            break;

                        case 2:
                            student.setNumber((long) currCell.getNumericCellValue());
                            break;

                        default:
                            break;

                    }
                    cellIndex++;
                }
                students.add(student);
            }
            workbook.close();
            return students;

        }catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
