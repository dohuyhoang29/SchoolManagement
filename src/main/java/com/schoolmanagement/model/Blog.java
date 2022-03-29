package com.schoolmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "blog")
@Getter
@Setter
@NoArgsConstructor
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	
	@Column(name = "header")
	private String header;

	
	@Column(name = "content")
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "created_date")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private LocalDate createdDate;

	@Column(name = "updated_date")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private LocalDateTime updatedDate;

	@Column(name = "images")
	private String images;

	public LocalTime getTime() {
		LocalTime lt = updatedDate.toLocalTime();

		return lt;

	}

	public LocalDate getDate() {
		LocalDate ld = updatedDate.toLocalDate();

		return ld;

	}

	public String getBlogBanner() {
		if (images == null && id == null) {
			return null;
		}

		return "/upload/image/blog_image/cover_image/" + images;
	}
}
