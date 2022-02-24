package com.schoolmanagement;

import java.nio.file.Paths;

public class Test {

  public static void main(String[] args) {
    String path = Paths.get(".").toAbsolutePath().normalize() + "\\src\\main\\resources\\static\\images\\";
    System.out.println(path);
  }
}
