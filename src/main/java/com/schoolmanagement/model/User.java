package com.schoolmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

//  @NotEmpty(message = "Enter username")
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

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "teacher_subjects", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private Set<Subjects> subjects = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Blog> blogs = new HashSet<>();

	@OneToOne(mappedBy = "user")
	private Class aClass;

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
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

	public String getUserImagePath() {
		if (image == null && id == null) {
			return null;
		}

		return "/upload/image/user_image/" + image;
	}

	public String getStudentImagePath() {
		if (image == null && id == null) {
			return null;
		}

		return "/upload/image/student_image/" + image;
	}

	public boolean hasRole(String roleName) {
		for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext();) {
			Role next = iterator.next();
			if (next.getRoleName().equalsIgnoreCase(roleName)) {
				return true;
			}
		}
		return false;
	}

	public float getAverageMarks() {
		for (Iterator<Mark> iterator = marks.iterator(); iterator.hasNext();) {
			Mark next = iterator.next();
			if (next.getType() == 4) {
				return next.getCoefficient();
			}
		}

		return 0.0f;
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
