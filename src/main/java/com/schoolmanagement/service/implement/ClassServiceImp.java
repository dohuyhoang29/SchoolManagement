package com.schoolmanagement.service.implement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.CoefficientRequest;
import com.schoolmanagement.model.request.MarkRequest;
import com.schoolmanagement.model.request.SchoolYearClassRequest;
import com.schoolmanagement.repositories.ClassRepositories;
import com.schoolmanagement.repositories.StudentRepositories;
import com.schoolmanagement.repositories.SubjectRepositories;
import com.schoolmanagement.service.ClassService;

@Service
public class ClassServiceImp implements ClassService {

	@Autowired
	private StudentRepositories studentRepositories;
	
	@Autowired
	private ClassRepositories classRepositories;

	@Autowired
	private SubjectRepositories subjectRepositories;
	
	@Override
	public void saveClass(Class aClass) {
		
		classRepositories.save(aClass);
	}

	@Override
	public Class getClassById(Integer id) {
		
		return classRepositories.findById(id).get();
	}

	@Override
	public Iterable<Class> getAllClass() {
		
		return classRepositories.findAll();
	}

	@Override
	public Page<Class> getAllClassPage(String b , int page, String sortField, String sortDir){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		Pageable pages = PageRequest.of(page -1, 10, sort);
		
		if(b == null) {
			return classRepositories.findAll(pages);
		}else {
			return classRepositories.listClass(b, pages);
		}
	}

	@Override
	public Page<Class> getAllByTeacherId(Integer id, int page, String sortField, String sortDir) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		Pageable pages = PageRequest.of(page -1, 1, sort);
		
		
		return classRepositories.getAllByTeacherId(id, pages);
	}

	@Override
	public List<SchoolYearClassRequest> getSchoolYear() {
		return classRepositories.getSchoolYear();
	}

	@Override
	public Class getClassByClassName(String className) {
		Optional<Class> aClass = classRepositories.getClassByClassName(className);
		if (aClass.isPresent()) {
			return aClass.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Class> getAllClassInCurrentYear() {
		List<Class> classList = new ArrayList<>();
		for (Class c : classRepositories.findAll()) {
			if (c.getSchoolYear() == LocalDate.now().getYear()) {
				classList.add(c);
			}
		}
		return classList;
	}

	@Override
	public int countAllClass() {
		return classRepositories.countAll();
	}

	@Override
	public List<MarkRequest> findAverageOneAndTwo(int classId) {

		List<User> studentList = studentRepositories.findAllStudentByClass(classId);
		Iterable<Subjects> subjectList = subjectRepositories.findAll();

		List<MarkRequest> listMarkRq = new ArrayList<>();

		for(User u : studentList) {
			MarkRequest mr = new MarkRequest();

			mr.setClassName(u.getAClass().getClassName());
			mr.setSchoolYear(u.getAClass().getSchoolYear());
			mr.setGade(u.getAClass().getGrade());
			mr.setStudentName(u.getFullName());
			mr.setDateOfBirth(u.getDob());
			mr.setAddress(u.getAddress());
			mr.setStudentId(u.getId());

			List<CoefficientRequest> listCq = new ArrayList<>();
			for(Subjects s : subjectList) {


				MarkRequest markRequest = classRepositories.DataExport(u.getId(), classId, s.getId());
				CoefficientRequest cRequest = new CoefficientRequest();

				if( markRequest != null) {

					cRequest.setCoefficient(markRequest.getCoeff());
				}else {

					cRequest.setCoefficient("No data");
				}

				listCq.add(cRequest);
			}


			mr.setCoefficients(listCq);

			listMarkRq.add(mr);

		}

		// TODO Auto-generated method stub
		return listMarkRq;
	}

}
