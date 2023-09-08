package com.graduate.IRLEnglishcenter.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.graduate.IRLEnglishcenter.dto.CourseRequest;
import com.graduate.IRLEnglishcenter.dto.CourseResponse;
import com.graduate.IRLEnglishcenter.entity.Course;
import com.graduate.IRLEnglishcenter.entity.EStatusCourse;

public interface CourseService {
    List<CourseResponse> getAllCourse(String code);
    CourseResponse getCourseById(Long id);
    ResponseEntity<String> createCourse(CourseRequest courseRequest);
    Course updateCourse(Long id, CourseRequest courseRequest);
    List<CourseResponse> getCoursesByStatus(EStatusCourse status);
}