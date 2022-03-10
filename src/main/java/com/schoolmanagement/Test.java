package com.schoolmanagement;

import com.schoolmanagement.model.Student;
import com.schoolmanagement.repositories.StudentRepositories;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class Test {

  @Autowired
  private static StudentRepositories repositories;

  public static void main(String[] args) {
    int year = LocalDateTime.now().getYear();
    System.out.println(year);
  }

  public static int randomString(String text) {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 20;
    Random random = new Random();

    String generatedString = random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
    List<Student> list = (List<Student>) repositories.findAllByAdmissionYear(2022);

    return list.size();
  }
}
