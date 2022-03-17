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

public class StudentExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<User> studentList;

	public StudentExcelExporter(List<User> studentList) {
		this.studentList = studentList;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Students");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Username", style);
		createCell(row, 1, "Full Name", style);
		createCell(row, 2, "Address", style);
		createCell(row, 3, "Date of Birth", style);
		createCell(row, 4, "Admission Year", style);
		createCell(row, 5, "Graduate Year", style);
		createCell(row, 6, "Status", style);
		createCell(row, 7, "Class", style);
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

		for (User student : studentList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, student.getUsername(), style);
			createCell(row, columnCount++, student.getFullName(), style);
			createCell(row, columnCount++, student.getAddress(), style);
			createCell(row, columnCount++, student.getDob().toString(), style);
			createCell(row, columnCount++, student.getUserInfo().getAdmissionYear(), style);
			createCell(row, columnCount++, student.getUserInfo().getGraduateYear(), style);
			if (student.getUserInfo().getStatus() == 1) {
				createCell(row, columnCount++, "Studying", style);
			} else if (student.getUserInfo().getStatus() == 2) {
				createCell(row, columnCount++, "Absent", style);
			} else {
				createCell(row, columnCount++, "Graduate", style);
			}
			createCell(row, columnCount++, student.getUserInfo().getAClass().getClassName(), style);
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
