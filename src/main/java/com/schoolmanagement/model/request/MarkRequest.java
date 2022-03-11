package com.schoolmanagement.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarkRequest {
	
	private Integer id;

	private Integer students;
	
	private Integer subjects;
	
	private Integer type;

	private Integer semester;

	private Integer coefficient;

	private Integer createdBy;
	
	private Integer updatedBy;
}
