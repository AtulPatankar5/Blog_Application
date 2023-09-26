package com.project.blog.entities;

import java.util.Date;

import org.hibernate.resource.beans.internal.FallbackBeanInstanceProducer;
import org.springframework.stereotype.Service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post extends BaseEntity {

	@Column(name = "title", length = 50, nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "image", nullable = false)
	private String imageName;

	@Column(name = "date", nullable = false)
	private Date addedDate;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;// FK

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;// FK

}
