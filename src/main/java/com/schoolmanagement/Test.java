package com.schoolmanagement;

import java.util.Random;

public class Test {

  public static void main(String[] args) {
    String text = "image. adf .adfasdf. .jpg";
    System.out.println(text.substring(text.lastIndexOf('.')));
    System.out.println(randomString(text));
  }

  public static String randomString(String text) {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 20;
    Random random = new Random();

    String generatedString = random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

    return generatedString + text.substring(text.lastIndexOf('.'));
  }
}
