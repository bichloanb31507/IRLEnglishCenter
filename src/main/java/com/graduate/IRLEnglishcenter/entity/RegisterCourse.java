package com.graduate.IRLEnglishcenter.entity;

import java.time.LocalDate;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Dang ki khoa hoc
@Entity
@Table(name = "register_courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCourse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student students;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course courses;
	
	@Column(name="entry_score")
	private Double entry_score;
	
	@Column(name = "day_opening")
	private LocalDate day_opening;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EStatusCourse status_course;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EPaymentStatus status_payment_tuition;
}
