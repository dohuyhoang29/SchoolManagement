package com.schoolmanagement.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClassRequest {
	private int classid;
	private String className;
	private int grade ;
	private int schoolYear;
	private int userId;
}
