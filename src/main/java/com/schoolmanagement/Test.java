package com.schoolmanagement;

import com.schoolmanagement.model.User;
import com.schoolmanagement.repositories.StudentRepositories;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
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
    DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().parseCaseInsensitive().append(DateTimeFormatter.ofPattern("MM-dd-yyyy")).toFormatter();
    String date = "02-12-2002";
    LocalDate localDateTime = LocalDate.parse(date, dateTimeFormatter);
    System.out.println(localDateTime);

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    System.out.println(encoder.encode("123456"));
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
    List<User> list = (List<User>) repositories.findAllByAdmissionYear(2022);

    return list.size();
  }
}
