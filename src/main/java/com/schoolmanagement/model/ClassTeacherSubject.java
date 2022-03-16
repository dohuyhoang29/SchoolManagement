package com.schoolmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "class_teacher_subject")
@Getter
@Setter
@NoArgsConstructor
public class ClassTeacherSubject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User users;
	
	@ManyToOne
	@JoinColumn(name="subject_id")
	private Subjects subjects;
	
	@ManyToOne
	@JoinColumn(name="class_id")
	private Class theClass; 
	

	
}
