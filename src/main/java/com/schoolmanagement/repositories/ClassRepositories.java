package com.schoolmanagement.repositories;

import com.schoolmanagement.model.Class;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ClassRepositories extends PagingAndSortingRepository<Class, Integer> {

	
	@Query(value="SELECT c FROM Class c WHERE c.className LIKE (:dataFind)")
	Page<Class> listClass(@Param("dataFind") String data , Pageable pageable);
	
}
