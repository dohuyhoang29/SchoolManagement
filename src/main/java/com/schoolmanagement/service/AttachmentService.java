package com.schoolmanagement.service;

import com.schoolmanagement.model.Attachment;
import com.schoolmanagement.repositories.AttachmentRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentService {
	@Autowired
	private AttachmentRepositories attachmentRepositories;

	public void SaveAttachment(Attachment attachment) {
		attachmentRepositories.save(attachment);
	}
}
