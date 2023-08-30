package com.graduate.IRLEnglishcenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.graduate.IRLEnglishcenter.entity.StudyTime;

public interface StudyTimeRepository extends JpaRepository<StudyTime, Long> {
	 List<StudyTime> findByNameContaining(String name);
}
