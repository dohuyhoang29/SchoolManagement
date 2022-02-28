package com.schoolmanagement.repositories;

import com.schoolmanagement.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends PagingAndSortingRepository<User, Integer> {
    User findById(int integer);
}
