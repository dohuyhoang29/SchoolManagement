package com.schoolmanagement.helper;

import com.schoolmanagement.model.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserExcelExporter {

  private XSSFWorkbook workbook;
  private XSSFSheet sheet;
  private List<User> listUsers;

  public UserExcelExporter(List<User> listUsers) {
    this.listUsers = listUsers;
    workbook = new XSSFWorkbook();
  }

  private void writeHeaderLine() {
    sheet = workbook.createSheet("Users");

    Row row = sheet.createRow(0);

    CellStyle style = workbook.createCellStyle();
    XSSFFont font = workbook.createFont();
    font.setBold(true);
    font.setFontHeight(16);
    style.setFont(font);

    createCell(row, 0, "User ID", style);
    createCell(row, 1, "Full Name", style);
    createCell(row, 2, "User Name", style);
    createCell(row, 3, "Email", style);
    createCell(row, 4, "Phone", style);
    createCell(row, 5, "Date of Birth", style);
    createCell(row, 6, "Address", style);
    createCell(row, 7, "Start Date", style);
    createCell(row, 8, "End Date", style);
    createCell(row, 9, "Deleted", style);
    createCell(row, 10, "Create Date", style);
    createCell(row, 11, "Updated Date", style);
  }

  private void createCell(Row row, int columnCount, Object value, CellStyle style) {
    sheet.autoSizeColumn(columnCount);
    Cell cell = row.createCell(columnCount);
    if (value instanceof Integer) {
      cell.setCellValue((Integer) value);
    } else if (value instanceof Boolean) {
      cell.setCellValue((Boolean) value);
    } else {
      cell.setCellValue((String) value);
    }
    cell.setCellStyle(style);
  }

  private void writeDataLines() {
    int rowCount = 1;

    CellStyle style = workbook.createCellStyle();
    XSSFFont font = workbook.createFont();
    font.setFontHeight(14);
    style.setFont(font);

    for (User user : listUsers) {
      Row row = sheet.createRow(rowCount++);
      int columnCount = 0;

      createCell(row, columnCount++, user.getId(), style);
      createCell(row, columnCount++, user.getFullName(), style);
      createCell(row, columnCount++, user.getUsername(), style);
      createCell(row, columnCount++, user.getEmail(), style);
      createCell(row, columnCount++, user.getPhone(), style);
      createCell(row, columnCount++, user.getDob().toString(), style);
      createCell(row, columnCount++, user.getAddress(), style);
      createCell(row, columnCount++, user.getStartDate().toString(), style);
      createCell(row, columnCount++, user.getEndDate().toString(), style);
      createCell(row, columnCount++, user.getDeleted(), style);
      createCell(row, columnCount++, user.getCreatedDate().toString(), style);
      createCell(row, columnCount++, user.getUpdatedDate().toString(), style);
    }
  }

  public void export(HttpServletResponse response) throws IOException {
    writeHeaderLine();
    writeDataLines();

    ServletOutputStream outputStream = response.getOutputStream();
    workbook.write(outputStream);
    workbook.close();

    outputStream.close();

  }
}
