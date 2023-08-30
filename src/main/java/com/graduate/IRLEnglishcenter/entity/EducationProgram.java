package com.graduate.IRLEnglishcenter.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//Chuong trinh dao tao
@Entity
@Table(name = "education_programs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationProgram {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	
	 public EducationProgram(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	@OneToMany(mappedBy = "education_programs", cascade = CascadeType.ALL)
	    private List<Course> courses = new ArrayList<>();
}
