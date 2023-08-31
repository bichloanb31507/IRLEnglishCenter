package com.graduate.IRLEnglishcenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduate.IRLEnglishcenter.entity.RegisterCourse;
import com.graduate.IRLEnglishcenter.repository.RegisterCourseRepository;

@Service
public class RegisterCourseService {
	@Autowired
    private RegisterCourseRepository registerCourseRepository;

    public List<RegisterCourse> getRegisteredCoursesByStudentId(Long studentId) {
        return registerCourseRepository.findRegisteredCoursesByStudentId(studentId);
    }
}
