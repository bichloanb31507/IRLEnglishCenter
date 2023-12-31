package com.graduate.IRLEnglishcenter.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Khoa hoc
@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	@Column(name = "tuition")
	private double tuition;
	@Column(name = "day_opening")
	private LocalDate day_opening;
	@Column(name = "course_completion_time")
	private LocalDate course_completion_time;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EStatusCourse status;
	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime created_at;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updated_at;

	@ManyToOne()
	@JoinColumn(name = "education_program_id")
	@JsonIgnore
	private EducationProgram education_programs;
	@OneToMany(mappedBy = "courses", cascade = CascadeType.ALL)
	private List<ClassificationClass> classification_classes = new ArrayList<>();

	@OneToMany(mappedBy = "courses")
	private Set<RegisterCourse> registerCourses = new HashSet<>();
	@OneToMany(mappedBy = "courses")
	private Set<Transcript> transcripts = new HashSet<>();
}
