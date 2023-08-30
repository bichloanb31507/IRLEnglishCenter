package com.graduate.IRLEnglishcenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graduate.IRLEnglishcenter.entity.RegisterCourse;


public interface RegisterCourseRepository extends JpaRepository<RegisterCourse, Long>{
//	 List<RegisterCourse> findByCourseId(Long course_id);
}
