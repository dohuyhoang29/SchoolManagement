package com.schoolmanagement.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @NotEmpty(message = "Enter full name")
  @Column(name = "full_name", nullable = false)
  private String fullName;

  @NotEmpty(message = "Enter username")
  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false, unique = true)
  @NotEmpty(message = "Enter email")
  private String email;

  @Column(name = "phone", nullable = false)
  @NotEmpty(message = "Enter phone number")
  private String phone;

  @Column(name = "dob", nullable = false)
  @NotNull(message = "Enter date of birth")
  @DateTimeFormat(pattern = "MM/dd/yyyy")
  private LocalDate dob;

  @Column(name = "address", nullable = false)
  @NotEmpty(message = "Enter address")
  private String address;

  @Column(name = "created_date", nullable = false)
  private LocalDateTime createdDate;

  @Column(name = "updated_date", nullable = false)
  private LocalDateTime updatedDate;

  @Column(name = "image")
  private String image;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_info_id", referencedColumnName = "id")
  private UserInfo userInfo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<Role> userRole;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "teacher_subjects", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private Set<Subjects> subjects = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Blog> blogs = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "class_id", referencedColumnName = "id")
  private Class aClass;

  @OneToMany(mappedBy = "users", orphanRemoval = true)
  private List<ClassTeacherSubject> users = new ArrayList<>();

  @OneToMany(mappedBy = "students")
  private Set<Mark> marks = new HashSet<>();

  @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Mark> createMark = new HashSet<>();

  @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Mark> updateMark = new HashSet<>();

  @OneToOne(mappedBy = "student")
  private StudentEvaluate studentEvaluate;

  @OneToOne(mappedBy = "createdBy")
  private StudentEvaluate createEvaluate;

  @OneToOne(mappedBy = "updatedBy")
  private StudentEvaluate updateEvaluate;

  public void addRole(Role role) {
    this.roles.add(role);
  }

  public String getTeacherImagePath() {
    if (image == null && id == null) {
      return null;
    }

    return "/upload/image/teacher_image/" + image;
  }

  public String getStudentImagePath() {
    if (image == null && id == null) {
      return null;
    }

    return "/upload/image/student_image/" + image;
  }

  public void setImage(MultipartFile multipartFile, String folderSrc) throws IOException {
    String root = "src/main/";
    String imageTeacher = multipartFile.getOriginalFilename();
    String str_filename = "";
    if (imageTeacher != null && !imageTeacher.isEmpty()) {
      str_filename = UUID.randomUUID() + imageTeacher.substring(imageTeacher.lastIndexOf('.'));

      if (!Files.exists(Paths.get(root + folderSrc))) {
        Files.createDirectories(Paths.get(root + folderSrc));
      }
      Files.copy(multipartFile.getInputStream(), Paths.get(root + folderSrc + str_filename),
          StandardCopyOption.REPLACE_EXISTING);

      this.image = str_filename;
    }
  }

  public boolean hasRole(String roleName) {
    for (Role next : roles) {
      if (next.getRoleName().equalsIgnoreCase(roleName)) {
        return true;
      }
    }
    return false;
  }

	public User(String fullName, String username, String password, String email, String phone, LocalDate dob,
			String address, LocalDateTime createdDate, LocalDateTime updatedDate, UserInfo userInfo) {
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.dob = dob;
		this.address = address;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.userInfo = userInfo;
	}

}
