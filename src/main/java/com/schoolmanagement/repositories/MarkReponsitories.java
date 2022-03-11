package com.schoolmanagement.repositories;



import org.springframework.data.repository.PagingAndSortingRepository;

import com.schoolmanagement.model.Mark;

public interface MarkReponsitories extends PagingAndSortingRepository<Mark, Integer> {

}
