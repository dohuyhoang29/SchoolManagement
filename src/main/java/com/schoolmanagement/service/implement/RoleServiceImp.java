package com.schoolmanagement.service.implement;

import com.schoolmanagement.model.Role;
import com.schoolmanagement.repositories.RoleRepositories;
import com.schoolmanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {
	@Autowired
	private RoleRepositories repo;

	@Override
	public void saveRole(Role role) {
		
		repo.save(role);
	}
}
