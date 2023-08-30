package com.graduate.IRLEnglishcenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.graduate.IRLEnglishcenter.dto.RegisterCourseRequest;
import com.graduate.IRLEnglishcenter.dto.StudentResponse;
import com.graduate.IRLEnglishcenter.entity.EPaymentStatus;
import com.graduate.IRLEnglishcenter.entity.RegisterCourse;
import com.graduate.IRLEnglishcenter.entity.Student;
import com.graduate.IRLEnglishcenter.repository.CourseRepository;
import com.graduate.IRLEnglishcenter.repository.RegisterCourseRepository;
import com.graduate.IRLEnglishcenter.repository.StudentRepository;


@RestController
@RequestMapping("/api")
public class RegisterCourseController {
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	RegisterCourseRepository registerCourseRepository;
//dang ki khoa hoc
	@PostMapping("/registercourse")
	public ResponseEntity<RegisterCourse> createRegisterCourse(
			@RequestBody RegisterCourseRequest registerCourseRequest) {
		try {
			RegisterCourse registerCourse = new RegisterCourse();
			registerCourse.setCourses(registerCourseRequest.getCourses());
			registerCourse.setStudents(mapStudentResponseToStudent(registerCourseRequest.getStudent()));
			registerCourse.setDay_opening(registerCourseRequest.getDay_opening());
			registerCourse.setStatus_payment_tuition(EPaymentStatus.PENDING);
			registerCourseRepository.save(registerCourse);
			return new ResponseEntity<>(registerCourse, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	private Student mapStudentResponseToStudent(StudentResponse studentResponse) {
		Student student = new Student();
		student.setId(studentResponse.getId());
		student.setCode(studentResponse.getCode());
		student.setFullname(studentResponse.getFullname());
		student.setEmail(studentResponse.getEmail());
		student.setDateOfBirth(studentResponse.getDateOfBirth());
		student.setGender(studentResponse.getGender());
		student.setCitizenId(studentResponse.getCitizenId());
		student.setAddress(studentResponse.getAddress());
		return student;
	}
//nv dao tao xem khoa hoc dang ki
//	@GetMapping("/courses/{course_id}/registercourses")
//	public ResponseEntity<List<RegisterCourse>> getAllRegisterCoursesByCourseId(@PathVariable(value="course_id") Long course_id){
//		try {
//			if(!courseRepository.existsById(course_id)) {
//				throw new ResourceNotFoundException("Not found Course with id = " + course_id);
//			}
//			List<RegisterCourse> registerCourse= registerCourseRepository.findByCourseId(course_id);
//			return new ResponseEntity<>(registerCourse,HttpStatus.OK);
//			
//		}catch(Exception e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		
//	}
}
