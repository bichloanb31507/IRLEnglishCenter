package com.graduate.IRLEnglishcenter.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import com.graduate.IRLEnglishcenter.entity.Account;
import com.graduate.IRLEnglishcenter.entity.AttendanceTrackingEmployee;
import com.graduate.IRLEnglishcenter.entity.EStatusCourse;
import com.graduate.IRLEnglishcenter.entity.EmployeeSalary;
import com.graduate.IRLEnglishcenter.entity.TuitionFeeReceipt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {
	private Long id;
	private String code;
	private String name;
	private double tuition;
	private LocalDate day_opening;
	private EStatusCourse status_course;
	private LocalDate course_completion_time;
	private EducationProgramResponse education_programs;
	private String created_at;
	
}
