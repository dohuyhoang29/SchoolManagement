package com.schoolmanagement.model.request;

public class ClassTeacherSubjectRequest {
	
	private int userid;
	private int subjectId;
	private int classid;
	private ClassRequest classReq;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getClassid() {
		return classid;
	}
	public void setClassid(int classid) {
		this.classid = classid;
	}
	public ClassRequest getClassReq() {
		return classReq;
	}
	public void setClassReq(ClassRequest classReq) {
		this.classReq = classReq;
	}
	
}
