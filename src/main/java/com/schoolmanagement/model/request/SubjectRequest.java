package com.schoolmanagement.model.request;

public class SubjectRequest {
	private String subjectName;
	private int subjectId;
	private int studentId;
	private int type;
	private int semester;
	private String coefficient;

	
	public SubjectRequest(String subjectName, int subjectId, int studentId, int type, int semester,String coefficient) {
		this.subjectName = subjectName;
		this.subjectId = subjectId;
		this.setStudentId(studentId);
		this.type = type;
		this.semester = semester;
		this.coefficient = coefficient;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getSubjectid() {
		return subjectId;
	}

	public void setSubjectid(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(String coefficient) {
		this.coefficient = coefficient;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

}
