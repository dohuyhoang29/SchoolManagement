package com.schoolmanagement.repositories;

import com.schoolmanagement.model.Attachment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepositories extends PagingAndSortingRepository<Attachment , Integer> {
}
