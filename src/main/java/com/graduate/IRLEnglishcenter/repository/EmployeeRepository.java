package com.graduate.IRLEnglishcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graduate.IRLEnglishcenter.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
