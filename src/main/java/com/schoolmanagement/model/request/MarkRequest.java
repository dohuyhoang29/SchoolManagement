package com.schoolmanagement.model.request;

import java.time.LocalDate;
import java.util.List;


public class MarkRequest {
	
	private Integer markId;

	private Integer students;
	
	private Integer subjects;
	
	private Integer type;

	private Integer semester;

	private List<Float> coefficiented;

	private Float coefficient;
	
	private Integer createdBy;
	
	private Integer updatedBy;
	
	private String subjectName;
	
	private int subjectId;
	
	private int studentId;
	
	private String coeff;
	
	private String studentName;
	
	private LocalDate dateOfBirth;
	
	private String address;
	
	private List<CoefficientRequest> coefficients;
	
	private CoefficientRequest coeffs;
	
	
	private int currentPage;
	
	private int classId;
	
	private String fullNameTeacher;
	
	public MarkRequest(String subjectName, int subjectId, int studentId, int type, int semester,String coefficient) {
		this.subjectName = subjectName;
		this.subjectId = subjectId;
		this.studentId =studentId ;
		this.type = type;
		this.semester = semester;
		this.coeff = coefficient;
		
	}
	//m.id, m.coefficient, m.semester,s.subject_name , u.full_name 
	public MarkRequest(int markId) {
		this.markId = markId;
	}
	
	
	public MarkRequest(int subjectId , float coefficient , int semester , String subjectName , String fullname) {
		this.subjectId = subjectId;
		this.coefficient = coefficient;
		this.semester = semester;
		this.subjectName = subjectName;
		this.fullNameTeacher = fullname;
		
	}
	
	public MarkRequest(int studentId ,String fullName, LocalDate dob , String address , float coeffs , int id) {
		this.studentId = studentId;
		this.studentName = fullName;
		this.dateOfBirth = dob;
		this.address = address;
		this.coefficient = coeffs;
		this.markId = id;
		
	}
	
	public MarkRequest() {
		
	}
	
	
	
	public CoefficientRequest getCoeffs() {
		return coeffs;
	}

	public void setCoeffs(CoefficientRequest coeffs) {
		this.coeffs = coeffs;
	}

	public String getFullNameTeacher() {
		return fullNameTeacher;
	}

	public void setFullNameTeacher(String fullNameTeacher) {
		this.fullNameTeacher = fullNameTeacher;
	}

	public Integer getMarkId() {
		return markId;
	}

	public void setMarkId(Integer markId) {
		this.markId = markId;
	}

	public Integer getStudents() {
		return students;
	}

	public void setStudents(Integer students) {
		this.students = students;
	}

	public Integer getSubjects() {
		return subjects;
	}

	public void setSubjects(Integer subjects) {
		this.subjects = subjects;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public List<Float> getCoefficiented() {
		return coefficiented;
	}

	public void setCoefficiented(List<Float> coefficiented) {
		this.coefficiented = coefficiented;
	}

	public Float getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Float coefficient) {
		this.coefficient = coefficient;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getCoeff() {
		return coeff;
	}

	public void setCoeff(String coeff) {
		this.coeff = coeff;
	}

	public List<CoefficientRequest> getCoefficients() {
		return coefficients;
	}

	public void setCoefficients(List<CoefficientRequest> coefficients) {
		this.coefficients = coefficients;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}
	
	
	
}
