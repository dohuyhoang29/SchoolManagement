package com.schoolmanagement.helper;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.request.CoefficientRequest;
import com.schoolmanagement.model.request.MarkRequest;
import com.schoolmanagement.service.MarkService;

public class ClassExcelExporter {
	
	
	
	private MarkService markService;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet; 
	private List<MarkRequest> lMarkRequests;
	private Iterable<Subjects> listSubject;
	
	
	
	public ClassExcelExporter(List<MarkRequest> lMarkRequests , Iterable<Subjects> listSubject , MarkService markService) {
		this.markService = markService;
		this.lMarkRequests = lMarkRequests;
		this.listSubject = listSubject;
		workbook = new XSSFWorkbook();
	}
	
	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		
		if (value instanceof Integer) {
		
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
		
			cell.setCellValue((Boolean) value);
			
		} else if (value instanceof Float) {
			
			cell.setCellValue((Float) value);	
		}else {
		
			cell.setCellValue((String) value);
		}
		
		cell.setCellStyle(style);
	}
	
	private void writeHeaderLine() {
		
		int column = 6;
		
		sheet = workbook.createSheet("Infomation Students");		
		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Name Student", style);
		createCell(row, 1, "Address", style);
		createCell(row, 2, "Date of Birth", style);
		createCell(row, 3, "Admission Year", style);
		createCell(row, 4, "Graduate Year", style);
		createCell(row, 5, "Class", style);
		
		for(Subjects sb : listSubject) {
			
			createCell(row, column, sb.getSubjectName(), style);
			column++;
			
		}
		createCell(row, column++, "Average 1", style);
		createCell(row, column++, "Average 2", style);
		createCell(row, column++, "Average Year", style);
		
	}
	
	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for(MarkRequest markRequest : lMarkRequests) {
			
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, markRequest.getStudentName(), style);
			createCell(row, columnCount++, markRequest.getAddress(), style);
			createCell(row, columnCount++, markRequest.getDateOfBirth().toString(), style);	
			createCell(row, columnCount++, markRequest.getSchoolYear(), style);
			createCell(row, columnCount++, markRequest.getGade(), style);
			createCell(row, columnCount++, markRequest.getClassName(), style);
			
			for(CoefficientRequest cr : markRequest.getCoefficients()) {
				
				createCell(row, columnCount++, cr.getCoefficient(), style);
				
			}
			
			
			
			String average1 = "";
			String average2 = "";
			String averageYear = "";
			
			if(markService.Average(markRequest.getStudentId(), 1) != null && markService.Average(markRequest.getStudentId(), 1) >= 0) {
				
				average1 = Float.valueOf(String.format(Locale.getDefault(), "%.2f" , markService.Average(markRequest.getStudentId(), 1))).toString() ;
				
				
			}else {
				
				average1 = "";
			}
			
			if(markService.Average(markRequest.getStudentId(), 2) != null && markService.Average(markRequest.getStudentId(), 2) >= 0) {
				
				average2 = Float.valueOf(String.format(Locale.getDefault(), "%.2f" , markService.Average(markRequest.getStudentId(), 2))).toString() ;
						
			}else {
				
				average2 = "";
			}
			
			if( markService.findMarkMediumByStudent(markRequest.getStudentId(), 6, 0) != null) {
			
				MarkRequest rq = markService.findMarkMediumByStudent(markRequest.getStudentId(), 6, 0);
				
				averageYear = Float.valueOf(String.format(Locale.getDefault(), "%.2f" ,rq.getCoefficient())).toString() ;
			
			}else {
				averageYear="";
			}
			
			
			createCell(row, columnCount++, average1 , style);
			createCell(row, columnCount++, average2, style);
			createCell(row, columnCount++, averageYear , style);
			
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
