package com.graduate.IRLEnglishcenter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduate.IRLEnglishcenter.dto.CourseResponse;
import com.graduate.IRLEnglishcenter.dto.EducationProgramResponse;
import com.graduate.IRLEnglishcenter.dto.StudentResponse;
import com.graduate.IRLEnglishcenter.entity.Student;
import com.graduate.IRLEnglishcenter.exception.ResourceNotFoundException;
import com.graduate.IRLEnglishcenter.repository.StudentRepository;

@RestController
@RequestMapping("/api")
public class StudentController {
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/students")
	public ResponseEntity<List<StudentResponse>> getAllStudent(@RequestParam(required = false) String code) {
		try {
			List<Student> courseData = new ArrayList<Student>();

			if (code == null)
				studentRepository.findAll().forEach(courseData::add);
			else
				studentRepository.findByCodeContaining(code).forEach(courseData::add);

			if (courseData.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<StudentResponse> response = new ArrayList<>();

			for (Student item : courseData) {
				response.add(new StudentResponse(item.getId(),item.getCode(), item.getFullname(), item.getEmail(),
						item.getDateOfBirth(), item.getGender(),item.getCitizenId(), item.getAddress()));
			}

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/students/{id}")
	public ResponseEntity<StudentResponse> getStudentsById(@PathVariable(value = "id") Long id) {
		Student item = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Student with id = " + id));
		StudentResponse response = new StudentResponse(item.getId(), item.getCode(), item.getFullname(), item.getEmail(),
				item.getDateOfBirth(), item.getGender(),item.getCitizenId(), item.getAddress());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
