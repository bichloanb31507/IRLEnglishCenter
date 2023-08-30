package com.graduate.IRLEnglishcenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graduate.IRLEnglishcenter.entity.EducationProgram;

public interface EducationProgramRepository extends JpaRepository<EducationProgram, Long>{
	 List<EducationProgram> findByNameContaining(String name);
}
