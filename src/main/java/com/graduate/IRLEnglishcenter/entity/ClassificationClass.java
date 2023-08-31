package com.graduate.IRLEnglishcenter.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//Phan loai lop
@Entity
@Table(name = "classification_classes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassificationClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "study_time_id")
	@JsonIgnore
	private StudyTime study_times;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shift_id")
	@JsonIgnore
	private Shift shifts;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	@JsonIgnore
	private Course courses;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lecture_id")
	@JsonIgnore
	private Lecture lectures;
	
	@OneToMany(mappedBy = "classification_classes")
	private Set<ClassificationRoom> classification_rooms = new HashSet<>();
	
	@OneToMany(mappedBy = "classification_classes")
	private Set<StudentClassification> student_classifications = new HashSet<>();
}
