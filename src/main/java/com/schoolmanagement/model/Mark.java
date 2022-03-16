package com.schoolmanagement.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mark")
public class Mark {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private User students;
	
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	private Subjects subjects;
	
	@Column(name = "type")
	private Integer type;

	@Column(name = "semester")
	private Integer semester;

	@Column(name = "coefficient")
	private Integer coefficient;

	@Column(name = "created_date")
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private User createdBy;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	@ManyToOne
	@JoinColumn(name = "updated_by")
	private User updatedBy;

	
}
