package com.feeder.entity;



import java.util.Date;

import org.modelmapper.internal.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name = "post_title",length =100,nullable = false)
	private String title;
	
	@Column(length = 1000)
	private String content;
	
	private Date addedDate;
	
	private String imageName;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private  Category category;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
