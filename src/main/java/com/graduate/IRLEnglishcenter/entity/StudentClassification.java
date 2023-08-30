package com.graduate.IRLEnglishcenter.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_classifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentClassification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student students;
	
	@ManyToOne
	@JoinColumn(name = "classification_class_id")
	@JsonIgnore
	private ClassificationClass classification_classes;
	
	@Column(name = "opening_day")
	private LocalDate opening_day;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ClassStatus status;
}
