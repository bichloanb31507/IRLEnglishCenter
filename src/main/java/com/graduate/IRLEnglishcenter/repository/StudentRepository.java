package com.graduate.IRLEnglishcenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graduate.IRLEnglishcenter.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	List<Student> findByCodeContaining(String code);
	//Student findByEmaiStudent(String email);
	Student findByCode(String code);
}
