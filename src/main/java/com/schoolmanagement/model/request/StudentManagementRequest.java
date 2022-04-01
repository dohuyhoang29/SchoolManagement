package com.schoolmanagement.model.request;

import java.time.LocalDate;

import com.schoolmanagement.model.Class;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentManagementRequest {
	private Integer id;
	private String fullName;
	private String address;
	private LocalDate dob;
	private Integer grade;
	private String className;
	private Integer schoolYear;
	private String image;
	private Class aClass;
	private StudentInfoRequest userInfo;

	public String getStudentImagePath() {
		if (image == null && id == null) {
			return null;
		}

		return "/upload/image/student_image/" + image;
	}
}
