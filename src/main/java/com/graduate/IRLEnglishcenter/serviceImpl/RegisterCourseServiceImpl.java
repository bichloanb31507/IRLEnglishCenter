package com.graduate.IRLEnglishcenter.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.graduate.IRLEnglishcenter.dto.RegisterCourseRequest;
import com.graduate.IRLEnglishcenter.dto.StudentResponse;
import com.graduate.IRLEnglishcenter.entity.Course;
import com.graduate.IRLEnglishcenter.entity.EPaymentStatus;
import com.graduate.IRLEnglishcenter.entity.RegisterCourse;
import com.graduate.IRLEnglishcenter.entity.Student;
import com.graduate.IRLEnglishcenter.message.Message;
import com.graduate.IRLEnglishcenter.repository.CourseRepository;
import com.graduate.IRLEnglishcenter.repository.RegisterCourseRepository;
import com.graduate.IRLEnglishcenter.repository.StudentRepository;
import com.graduate.IRLEnglishcenter.service.RegisterCourseService;
@Service
public class RegisterCourseServiceImpl implements RegisterCourseService {
    private final RegisterCourseRepository registerCourseRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public RegisterCourseServiceImpl(RegisterCourseRepository registerCourseRepository, StudentRepository studentRepository,
            CourseRepository courseRepository) {
        this.registerCourseRepository = registerCourseRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<RegisterCourse> getRegisteredCoursesByStudentId(Long studentId) {
        return registerCourseRepository.findRegisteredCoursesByStudentId(studentId);
    }

//    @Override
//    public RegisterCourse createRegisterCourse(RegisterCourseRequest registerCourseRequest) {
//        RegisterCourse registerCourse = new RegisterCourse();
//        registerCourse.setCourses(registerCourseRequest.getCourses());
//        registerCourse.setStudents(mapStudentResponseToStudent(registerCourseRequest.getStudent()));
//        registerCourse.setDay_opening(registerCourseRequest.getDay_opening());
//        registerCourse.setStatus_payment_tuition(EPaymentStatus.PENDING);
//        return registerCourseRepository.save(registerCourse);
//    }
    @Override
    public ResponseEntity<String> createRegisterCourse(RegisterCourseRequest registerCourseRequest) {
        Student student = mapStudentResponseToStudent(registerCourseRequest.getStudent());
        Course course = registerCourseRequest.getCourses();

        RegisterCourse existingRegisterCourse = registerCourseRepository.findByStudentsAndCourses(student, course);
        if (existingRegisterCourse != null) {
            return Message.getResponseEntity("Student already registered this course.", HttpStatus.BAD_REQUEST);
        }

        RegisterCourse registerCourse = new RegisterCourse();
        registerCourse.setCourses(course);
        registerCourse.setStudents(student);
        registerCourse.setDay_opening(registerCourseRequest.getDay_opening());
        registerCourse.setStatus_payment_tuition(EPaymentStatus.PENDING);

        registerCourseRepository.save(registerCourse);
        String responseMessage = "Succesfully register.";
        HttpStatus httpStatus = HttpStatus.OK;
        return Message.getResponseEntity(responseMessage, httpStatus);
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
}