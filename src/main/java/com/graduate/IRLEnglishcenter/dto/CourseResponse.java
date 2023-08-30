package com.graduate.IRLEnglishcenter.dto;

import java.time.LocalDate;

import com.graduate.IRLEnglishcenter.entity.EducationProgram;

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
	private LocalDate course_completion_time;
	private EducationProgramResponse education_programs;
}
