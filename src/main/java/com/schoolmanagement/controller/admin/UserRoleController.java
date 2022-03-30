package com.schoolmanagement.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.schoolmanagement.service.TeacherService;

@Controller
public class UserRoleController {
	
	@Autowired
	private TeacherService teacherService;
	
	@DeleteMapping("/teacherrole/delete/{teacherId}/{roleId}")
	public ResponseEntity<Void> deletehomeroomteacher(@PathVariable Integer teacherId,@PathVariable Integer roleId) {
		
		teacherService.deleterole(roleId, teacherId);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
