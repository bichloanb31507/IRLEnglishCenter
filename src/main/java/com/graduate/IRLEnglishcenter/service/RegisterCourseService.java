package com.graduate.IRLEnglishcenter.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.graduate.IRLEnglishcenter.dto.RegisterCourseRequest;
import com.graduate.IRLEnglishcenter.entity.RegisterCourse;


public interface RegisterCourseService {
    List<RegisterCourse> getRegisteredCoursesByStudentId(Long studentId);
    ResponseEntity<String> createRegisterCourse(RegisterCourseRequest registerCourseRequest);
}
