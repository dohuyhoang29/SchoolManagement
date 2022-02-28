package com.schoolmanagement;

import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.User;
import com.schoolmanagement.repositories.SubjectRepositories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class TestSubject {

  @Autowired
  private SubjectRepositories repo;

  @Test
  public void getSubject() {
    Subjects subjects = repo.findById(1);

    for (User user : subjects.getUsers()) {
      System.out.println(user.getFullName());
    }
    System.out.println(subjects.getUsers().size());
  }
}
