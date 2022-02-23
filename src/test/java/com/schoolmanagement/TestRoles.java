package com.schoolmanagement;

import com.schoolmanagement.model.Role;
import com.schoolmanagement.repositories.RoleRepositories;
import com.schoolmanagement.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class TestRoles {
  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void testAddRole(){
    Role admin = new Role("Administrator");
    Role teacher = new Role("Teacher");
    Role student = new Role("Student");

    entityManager.persist(admin);
    entityManager.persist(teacher);
    entityManager.persist(student);
  }
}
