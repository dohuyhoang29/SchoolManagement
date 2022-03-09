package com.schoolmanagement.service.implement;

import com.schoolmanagement.model.Attachment;
import com.schoolmanagement.repositories.AttachmentRepositories;
import com.schoolmanagement.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImp implements AttachmentService {
	@Autowired
	private AttachmentRepositories attachmentRepositories;

	@Override
	public void SaveAttachment(Attachment attachment) {
		attachmentRepositories.save(attachment);
	}
}
