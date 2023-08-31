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
//cham cong nhan vien
@Entity
@Table(name = "attendancetracking_employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceTrackingEmployee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "timesheet_id")
	@JsonIgnore
	private TimeSheet timesheets;
	@ManyToOne
	@JoinColumn(name = "employee_id")
	@JsonIgnore
	private Employee employees;
	@Column(name = "numberOfWorking")
    private double numberOfWorking;
}
