package com.schoolmanagement.helper;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.Role;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.UserInfo;
import com.schoolmanagement.repositories.StudentRepositories;
import com.schoolmanagement.service.implement.ClassServiceImp;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

public class StudentExcelImporter {
  @Autowired
  private StudentRepositories studentRepositories;

  @Autowired
  private ClassServiceImp classService;

  public List<User> excelImport(MultipartFile multipartFile, StudentRepositories studentRepositories, ClassServiceImp classService, Role role) throws IOException {
    List<User> studentList = new ArrayList<>();

    String fullName = "";
    String email = "";
    String phone = "";
    String address = "";
    LocalDate dob = null;
    Integer status = null;
    double admissionYear = 0;
    double graduateYear = 0;
    String className = "";

    Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
    Sheet firstSheet = workbook.getSheetAt(0);
    Iterator<Row> rowIterator = firstSheet.iterator();
    rowIterator.next();

    while (rowIterator.hasNext()) {
      Row nextRow = rowIterator.next();
      Iterator<Cell> cellIterator = nextRow.cellIterator();
      while (cellIterator.hasNext()) {
        DataFormatter dataFormatter = new DataFormatter();
        Cell nextCell = cellIterator.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        int columnIndex = nextCell.getColumnIndex();
        switch (columnIndex) {
          case 0:
            fullName = nextCell.getStringCellValue();
            break;
          case 1:
            email = nextCell.getStringCellValue();
            break;
          case 2:
            phone = nextCell.getStringCellValue();
            break;
          case 3:
            address = nextCell.getStringCellValue();
            break;
          case 4:
            String dateDob = dataFormatter.formatCellValue(nextCell);
            dob = LocalDate.parse(dateDob, formatter);
            break;
          case 5:
            if (nextCell.getStringCellValue().equalsIgnoreCase("Studying")) {
              status = 1;
            } else if (nextCell.getStringCellValue().equalsIgnoreCase("Absent")) {
              status = 2;
            } else {
              status = 3;
            }
            break;
          case 6:
            admissionYear = nextCell.getNumericCellValue();
            break;
          case 7:
            graduateYear = nextCell.getNumericCellValue();
            break;
          case 8:
            className = nextCell.getStringCellValue();
            break;
        }
      }

      int size = studentRepositories.findAllByAdmissionYear((int) admissionYear).size();
      String username = "std_" + (int) admissionYear + "_" + (size+ 1);
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      String password = encoder.encode("123456");
      Class aClass = classService.getClassByClassName(className);

      UserInfo userInfo = new UserInfo();
      userInfo.setAdmissionYear((int) admissionYear);
      userInfo.setGraduateYear((int) graduateYear);
      userInfo.setStatus(status);
      User user = new User(fullName, username, password, email, phone, dob, address,
          LocalDateTime.now(), LocalDateTime.now(), userInfo);
      user.addRole(role);
      studentList.add(user);
    }
    workbook.close();
    return studentList;
  }
}
