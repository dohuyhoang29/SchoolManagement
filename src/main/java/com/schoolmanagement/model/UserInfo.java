package com.schoolmanagement.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_info")
@Getter
@Setter
@NoArgsConstructor
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "admission_year")
	private Integer admissionYear;

	@Column(name = "graduate_year")
	private Integer graduateYear;

	@Column(name = "status")
	private Integer status;

	@OneToMany(mappedBy = "students" , fetch = FetchType.LAZY)
	List<Mark> mark = new ArrayList<>();

	@Column(name = "start_date")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private LocalDate startDate;

	@Column(name = "end_date")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private LocalDate endDate;

	@Column(name = "deleted")
	private Boolean deleted;

	@OneToOne(mappedBy = "userInfo" , fetch = FetchType.LAZY)
	private User user;
}
