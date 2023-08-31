package com.graduate.IRLEnglishcenter.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//cham cong
@Entity
@Table(name = "timesheets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@OneToMany(mappedBy = "timesheets", cascade = CascadeType.ALL)
	private List<AttendanceTrackingEmployee> attendanceTrackingEmployees = new ArrayList<>();
	@OneToMany(mappedBy = "timesheets", cascade = CascadeType.ALL)
	private List<AttendanceTrackingLecture> attendanceTrackingLectures = new ArrayList<>();
}
