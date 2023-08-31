package com.graduate.IRLEnglishcenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//luong nhan vien
@Entity
@Table(name = "employee_salary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSalary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "salary_id")
	@JsonIgnore
	private Salary salaries;
	@ManyToOne
	@JoinColumn(name = "employee_id")
	@JsonIgnore
	private Employee employees;
	@Column(name = "allowances")
    private double allowances;
	@Column(name = "bonus")
    private double bonus;
	@Column(name = "commission")
    private double commission;
	@Column(name = "salary")
    private double salary;
	@Column(name = "total")
    private double total;
}
