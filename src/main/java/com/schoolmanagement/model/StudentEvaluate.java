package com.schoolmanagement.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@NotNull(message = "Choose a semester")
	private Integer semester;

	@Column(name = "evaluate")
	@NotEmpty(message = "Enter evaluate")
	private String evaluate;

	@Column(name = "conduct")
	@NotNull(message = "Choose a conduct")
	private Integer conduct;

	@Column(name = "academic_ability")
	private Integer academicAbility;

	@Column(name = "created_date")
	private LocalDate createdDate;

	@OneToOne
	@JoinColumn(name = "student_id", referencedColumnName = "id")
	private User student;

	@Column(name = "updated_date")
	private LocalDate updatedDate;

	@OneToOne
	@JoinColumn(name = "created_by", referencedColumnName = "id")
	private User createdBy;

	@OneToOne
	@JoinColumn(name = "updated_by", referencedColumnName = "id")
	private User updatedBy;
	
	public Integer getAcademicAbility() {
		if (student.getAverageMarks() > 0) {
			if (student.getAverageMarks() < 3) {
				this.academicAbility = 1;
			} else if (3 <= student.getAverageMarks() && student.getAverageMarks() < 5) {
				this.academicAbility = 2;
			} else if (5 <= student.getAverageMarks() && student.getAverageMarks() < 8) {
				this.academicAbility = 3;
			} else {
				this.academicAbility = 4;
			}
		}
		return this.academicAbility;
	}
}
