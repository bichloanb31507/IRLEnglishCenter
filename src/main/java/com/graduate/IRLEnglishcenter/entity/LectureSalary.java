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
//luong giang vien
@Entity
@Table(name = "lecture_salary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureSalary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "salary_id")
	@JsonIgnore
	private Salary salaries;
	@ManyToOne
	@JoinColumn(name = "lecture_id")
	@JsonIgnore
	private Lecture lectures;
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
