package com.graduate.IRLEnglishcenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.graduate.IRLEnglishcenter.dto.RegisterCourseRequest;
import com.graduate.IRLEnglishcenter.entity.RegisterCourse;
import com.graduate.IRLEnglishcenter.message.Message;
import com.graduate.IRLEnglishcenter.service.RegisterCourseService;


@RestController
@RequestMapping("/api")
public class RegisterCourseController {
    private final RegisterCourseService registerCourseService;

    @Autowired
    public RegisterCourseController(RegisterCourseService registerCourseService) {
        this.registerCourseService = registerCourseService;
    }

    @PostMapping("/registercourse")
    public ResponseEntity<String> createRegisterCourse(@RequestBody RegisterCourseRequest registerCourseRequest) {
        try {
            ResponseEntity<String> responseEntity = registerCourseService.createRegisterCourse(registerCourseRequest);
            return responseEntity;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
