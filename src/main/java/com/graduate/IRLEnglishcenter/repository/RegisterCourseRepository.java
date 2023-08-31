package com.graduate.IRLEnglishcenter.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.graduate.IRLEnglishcenter.entity.RegisterCourse;


public interface RegisterCourseRepository extends JpaRepository<RegisterCourse, Long>{
//	 List<RegisterCourse> findByCourseId(Long course_id);
	@Query("SELECT rc FROM RegisterCourse rc WHERE rc.students.id = :studentId")
    List<RegisterCourse> findRegisteredCoursesByStudentId(@Param("studentId") Long studentId);
}

