package com.schoolmanagement.service.implement;

import com.schoolmanagement.model.request.EditTeacherRequest;
import com.schoolmanagement.model.request.InsertTeacherRequest;
import com.schoolmanagement.model.request.TeacherDetailRequest;
import com.schoolmanagement.repositories.TeacherRepositories;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import javax.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.schoolmanagement.model.Role;
import com.schoolmanagement.model.User;
import com.schoolmanagement.repositories.UserRepositories;
import com.schoolmanagement.service.TeacherService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TeacherServiceImp implements TeacherService {

	@Autowired
	private UserRepositories repo;
	@Autowired
	private TeacherRepositories teacherRepositories;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private EntityManager entityManager;

	@Override
	public void saveUser(InsertTeacherRequest insertTeacherRequest, MultipartFile multipartFile) {
		User user = modelMapper.map(insertTeacherRequest, User.class);
		user.setCreatedDate(LocalDateTime.now());
		user.setUpdatedDate(LocalDateTime.now());

		Role role = entityManager.find(Role.class, 2);
		user.addRole(role);

		try {
			user.setImage(multipartFile, "upload/image/teacher_image/");
		} catch (IOException e) {
			e.printStackTrace();
		}

		repo.save(user);
	}

	@Override
	public Iterable<User> saveAllUser(Iterable<User> list) {
		return repo.saveAll(list);
	}

	@Override
	public void updateTeacher(EditTeacherRequest editTeacherRequest, MultipartFile multipartFile) {
		Optional<User> teacher = repo.findById(editTeacherRequest.getId());

		if (teacher.isPresent()) {
			modelMapper.map(editTeacherRequest, teacher.get());
			teacher.get().setUpdatedDate(LocalDateTime.now());
			try {
				teacher.get().setImage(multipartFile, "upload/image/teacher_image/");
			} catch (IOException e) {
				e.printStackTrace();
			}

			repo.save(teacher.get());
		} else {
			System.out.println("Can't found teacher with id : " + editTeacherRequest.getId());
		}
	}

	@Override
	public List<User> getAllUser() {
		List<User> list = new ArrayList<>();

		for (User user : repo.findAll()) {
			for (Role role : user.getRoles()) {
				if (role.getRoleID() == 2) {
					list.add(user);
				}
			}
		}

		return list;
	}

	@Override
	public Page<User> searchTeacher(int pageNumber, String fullName, String status, String sortField,
			String sortDir) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		Pageable page = PageRequest.of(pageNumber - 1, 10, sort);
		List<User> listCheck;
		List<User> list = new ArrayList<>();

		if (status.equalsIgnoreCase("all")) {
			listCheck = teacherRepositories.searchUsersByFullName(fullName);
		} else if (status.equalsIgnoreCase("true")) {
			listCheck = teacherRepositories.searchUsersByFullNameAndDeleted(fullName, true);
		} else {
			listCheck = teacherRepositories.searchUsersByFullNameAndDeleted(fullName, false);
		}

		for (User user: listCheck) {
			if (user.hasRole("TEACHER")) {
				list.add(user);
			}
		}

		int start = (int) page.getOffset();
		int end = (int) ((start + page.getPageSize()) > list.size() ? list.size() : (start + page.getPageSize()));

		return new PageImpl<>(list.subList(start, end), page, list.size());
	}

	@Override
	public User findByUserId(int id) {
		return repo.findById(id);
	}

	@Override
	public TeacherDetailRequest findTeacherDetail(Integer id) {
		Optional<User> user = repo.findById(id);
		TeacherDetailRequest teacherDetail = new TeacherDetailRequest();
		if(user.isPresent()) {
			teacherDetail = modelMapper.map(user.get(), TeacherDetailRequest.class);
		}
		return teacherDetail;
	}

	@Override
	public EditTeacherRequest getUserById(Integer id) {
		Optional<User> user = repo.findById(id);
		EditTeacherRequest editTeacherRequest = new EditTeacherRequest();
		if (user.isPresent()) {
			editTeacherRequest = modelMapper.map(user.get(), EditTeacherRequest.class);
		}
		return editTeacherRequest;
	}

	@Override
	public void makeRetired(Integer id) {
		teacherRepositories.changeDeleted(true, id);
	}

	@Override
	public void makeWorking(Integer id) {
		teacherRepositories.changeDeleted(false, id);
	}

	@Override
	public int countTeacher() {
		return teacherRepositories.countAllTeacher();
	}

	@Override
	public void deleterole(int roleid, int userid) {
		repo.deleterole(roleid, userid);

	}
}
