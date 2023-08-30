package com.graduate.IRLEnglishcenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graduate.IRLEnglishcenter.entity.Shift;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
	 List<Shift> findByNameContaining(String name);
}
