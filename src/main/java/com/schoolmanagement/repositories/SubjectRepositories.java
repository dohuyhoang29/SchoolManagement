package com.schoolmanagement.repositories;

import com.schoolmanagement.model.Subjects;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubjectRepositories extends PagingAndSortingRepository<Subjects, Integer> {

}
