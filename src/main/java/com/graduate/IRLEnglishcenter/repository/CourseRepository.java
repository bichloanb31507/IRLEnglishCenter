package com.graduate.IRLEnglishcenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graduate.IRLEnglishcenter.entity.Course;


public interface CourseRepository extends JpaRepository<Course, Long>{
	 List<Course> findByCodeContaining(String code);
}
