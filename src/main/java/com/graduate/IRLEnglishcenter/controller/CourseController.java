package com.graduate.IRLEnglishcenter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduate.IRLEnglishcenter.dto.CourseRequest;
import com.graduate.IRLEnglishcenter.dto.CourseResponse;
import com.graduate.IRLEnglishcenter.dto.EducationProgramResponse;
import com.graduate.IRLEnglishcenter.repository.CourseRepository;
import com.graduate.IRLEnglishcenter.repository.EducationProgramRepository;

import com.graduate.IRLEnglishcenter.entity.Course;
import com.graduate.IRLEnglishcenter.exception.ResourceNotFoundException;
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
			    educationProgramDTO.setDescription(item.getEducation_programs().getDescription());
				response.add(new CourseResponse(item.getId(),item.getCode(), item.getName(), item.getTuition(), item.getDay_opening(),
						item.getCourse_completion_time(), educationProgramDTO));
			}

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/courses/{id}")
	public ResponseEntity<CourseResponse> getProductsById(@PathVariable(value = "id") Long id) {
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = " + id));
		EducationProgramResponse educationProgramDTO = new EducationProgramResponse();
	    educationProgramDTO.setId(course.getEducation_programs().getId());
	    educationProgramDTO.setName(course.getEducation_programs().getName());
	    educationProgramDTO.setDescription(course.getEducation_programs().getDescription());
		CourseResponse response = new CourseResponse(course.getId(), course.getCode(),course.getName(), course.getTuition(),
				course.getDay_opening(), course.getCourse_completion_time(),educationProgramDTO);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PostMapping("/courses")
	public ResponseEntity<Course> createCourse(@RequestBody CourseRequest courseRequest) {
		try {
			Course course = new Course();
			course.setCode(courseRequest.getCode());
			course.setName(courseRequest.getName());
			course.setTuition(courseRequest.getTuition());
			course.setDay_opening(courseRequest.getDay_opening());
			course.setCourse_completion_time(courseRequest.getCourse_completion_time());
			course.setEducation_programs(courseRequest.getEducation_programs());

			courseRepository.save(course);
			return new ResponseEntity<>(course, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PutMapping("/courses/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable(value = "id") Long id,
			@RequestBody CourseRequest courseRequest) {
		try {
			// Tìm kiếm khóa học cần cập nhật
			Course course = courseRepository.findById(id)

					.orElseThrow(() -> new ResourceNotFoundException("Not found Course with id = " + id));

			// Cập nhật thông tin khóa học
			course.setName(courseRequest.getName());
			course.setTuition(courseRequest.getTuition());
			course.setDay_opening(courseRequest.getDay_opening());
			course.setCourse_completion_time(courseRequest.getCourse_completion_time());
			course.setEducation_programs(courseRequest.getEducation_programs());
			

			// Lưu khóa học đã được cập nhật vào cơ sở dữ liệu
			courseRepository.save(course);
			return new ResponseEntity<>(course, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
