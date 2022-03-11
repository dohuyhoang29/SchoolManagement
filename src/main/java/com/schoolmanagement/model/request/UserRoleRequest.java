package com.schoolmanagement.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRoleRequest {
	private Integer userid;
	private Integer roleid;
}
