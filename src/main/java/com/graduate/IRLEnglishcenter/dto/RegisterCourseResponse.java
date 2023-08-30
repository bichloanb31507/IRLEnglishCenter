package com.graduate.IRLEnglishcenter.dto;

import java.time.LocalDate;

import com.graduate.IRLEnglishcenter.entity.Course;
import com.graduate.IRLEnglishcenter.entity.EPaymentStatus;
import com.graduate.IRLEnglishcenter.entity.EStatusCourse;
import com.graduate.IRLEnglishcenter.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCourseResponse {
	private Course course;
	private Student student;
	private LocalDate day_opening;
	private EStatusCourse status_course;
	private Double entry_score;
	private EPaymentStatus status_payment_tuition;
}
