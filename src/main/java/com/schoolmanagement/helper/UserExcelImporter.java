package com.schoolmanagement.helper;

import com.schoolmanagement.model.Role;
import com.schoolmanagement.model.User;
import com.schoolmanagement.repositories.RoleRepositories;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

public class UserExcelImporter {
  @Autowired
  private RoleRepositories roleRepositories;

  public List<User> excelImport(MultipartFile multipartFile, Role role) throws IOException {

    List<User> listUser = new ArrayList<>();

    String fullName = "";
    String username = "";
    String password = "";
    String email = "";
    String phone = "";
    LocalDate dob = null;
    String address = "";
    LocalDate startDate = null;
    LocalDate endDate = null;

    Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
    Sheet firstSheet = workbook.getSheetAt(0);
    Iterator<Row> rowIterator = firstSheet.iterator();
    rowIterator.next();

    while (rowIterator.hasNext()) {
      Row nextRow = rowIterator.next();
      Iterator<Cell> cellIterator = nextRow.cellIterator();
      while (cellIterator.hasNext()) {
        Cell nextCell = cellIterator.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//        DataFormatter formatter = new DataFormatter();
//        formatter.formatCellValue(nextCell);
        int columnIndex = nextCell.getColumnIndex();
        switch (columnIndex) {
          case 0:
            fullName = nextCell.getStringCellValue();
            break;
          case 1:
            username = nextCell.getStringCellValue();
            break;
          case 2:
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            password = encoder.encode((String)nextCell.getStringCellValue());
            break;
          case 3:
            email = nextCell.getStringCellValue();
            break;
          case 4:
            phone = nextCell.getStringCellValue();
            break;
          case 5:
            String dateDob = nextCell.getStringCellValue();
            dob = LocalDate.parse(dateDob, formatter);
            break;
          case 6:
            address = nextCell.getStringCellValue();
            break;
          case 7:
            String dateStart = nextCell.getStringCellValue();
            startDate = LocalDate.parse(dateStart, formatter);
            break;
          case 8:
            String dateEnd = nextCell.getStringCellValue();
            endDate = LocalDate.parse(dateEnd, formatter);
            break;
        }
      }
      User user = new User(fullName, username, password, email, phone, dob, address, startDate,
          endDate, false, LocalDateTime.now(), LocalDateTime.now());
      user.addRole(role);
      listUser.add(user);
    }
    workbook.close();
    return listUser;
  }
}
