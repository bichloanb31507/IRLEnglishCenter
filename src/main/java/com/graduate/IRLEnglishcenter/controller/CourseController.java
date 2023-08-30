package com.graduate.IRLEnglishcenter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduate.IRLEnglishcenter.dto.CourseResponse;
import com.graduate.IRLEnglishcenter.dto.EducationProgramResponse;
import com.graduate.IRLEnglishcenter.repository.CourseRepository;
import com.graduate.IRLEnglishcenter.repository.EducationProgramRepository;
import com.graduate.IRLEnglishcenter.entity.Course;
@RestController
@RequestMapping("/api")
public class CourseController {
	@Autowired
	EducationProgramRepository educationProgramRepository;
	@Autowired
	CourseRepository courseRepository;
	@GetMapping("/courses")
	public ResponseEntity<List<CourseResponse>> getAllCourse(@RequestParam(required = false) String code) {
		try {
			List<Course> courseData = new ArrayList<Course>();

			if (code == null)
				courseRepository.findAll().forEach(courseData::add);
			else
				courseRepository.findByCodeContaining(code).forEach(courseData::add);

			if (courseData.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<CourseResponse> response = new ArrayList<>();

			for (Course item : courseData) {
				EducationProgramResponse educationProgramDTO = new EducationProgramResponse();
			    educationProgramDTO.setId(item.getEducation_programs().getId());
			    educationProgramDTO.setName(item.getEducation_programs().getName());
				response.add(new CourseResponse(item.getId(),item.getCode(), item.getName(), item.getTuition(), item.getDay_opening(),
						item.getCourse_completion_time(), educationProgramDTO));
			}

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
