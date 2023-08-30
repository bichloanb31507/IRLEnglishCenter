package com.graduate.IRLEnglishcenter.dto;

import java.time.LocalDate;

import com.graduate.IRLEnglishcenter.entity.Course;
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
public class RegisterCourseRequest {
	private Course courses;
	private StudentResponse student;
	private LocalDate day_opening;
//	private EStatusCourse status_course;
	
}
