package com.graduate.IRLEnglishcenter.controller;

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
import com.graduate.IRLEnglishcenter.service.CourseService;
import com.graduate.IRLEnglishcenter.entity.Course;
import com.graduate.IRLEnglishcenter.entity.EStatusCourse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class CourseController {
	@Autowired
	private CourseService courseService;
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@GetMapping("/courses")
	public ResponseEntity<List<CourseResponse>> getAllCourse(@RequestParam(required = false) String code) {
		List<CourseResponse> response = courseService.getAllCourse(code);

		if (response.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/courses/{id}")
	public ResponseEntity<CourseResponse> getCourseById(@PathVariable(value = "id") Long id) {
		CourseResponse response = courseService.getCourseById(id);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/courses")
	public ResponseEntity<String> createCourse(@RequestBody CourseRequest courseRequest) {
		try {
			ResponseEntity<String> response = courseService.createCourse(courseRequest);
			return new ResponseEntity<>(response.getBody(), response.getStatusCode());
		} catch (Exception e) {
			logger.error("An error occurred while creating the course.", e);
			return new ResponseEntity<>("An error occurred while creating the course.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/courses/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable(value = "id") Long id,
			@RequestBody CourseRequest courseRequest) {
		Course course = courseService.updateCourse(id, courseRequest);

		return new ResponseEntity<>(course, HttpStatus.OK);
	}
	 @GetMapping("/courses/status/{status}")
	    public List<CourseResponse> getCoursesByStatus(@PathVariable("status") EStatusCourse status) {
	        return courseService.getCoursesByStatus(status);
	    }
}