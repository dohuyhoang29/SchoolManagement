package com.schoolmanagement.model.request;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoefficientRequest {
	private int id;
	private int type ;
	private int semester;
	private String coefficient;
	private Float coeffi;
	private List<String> marks;
}
