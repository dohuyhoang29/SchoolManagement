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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_evaluate")
@Getter
@Setter
@NoArgsConstructor
public class StudentEvaluate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "semester")
	private Integer semester;

	@Column(name = "evaluate")
	private String evaluate;

	@Column(name = "conduct")
	private Integer conduct;

	@Column(name = "academic_ability")
	private Integer academicAbility;

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
