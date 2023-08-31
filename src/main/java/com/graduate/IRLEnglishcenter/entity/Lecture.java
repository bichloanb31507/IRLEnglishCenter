package com.graduate.IRLEnglishcenter.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Giang vien
@Entity
@Table(name = "lectures", uniqueConstraints = { @UniqueConstraint(columnNames = "citizenId") })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "code")
	private String code;
	@Column(name = "fullname")
	private String fullname;
	@Column(name = "dateOfBirth")
	private LocalDate dateOfBirth;
	@Column(name = "gender")
    private String gender;
	@Column(name = "citizenId")
    private String citizenId;
	@Column(name = "phoneNumber")
    private String phoneNumber;
	@Column(name = "address")
    private String address;
	@Column(name = "qualification")
    private String qualification;
	@Column(name = "day_start")
	private LocalDate day_start;
	@Column(name = "rate_per_hour")
    private double rate_per_hour;
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account accounts;
	@OneToMany(mappedBy = "lectures", cascade = CascadeType.ALL)
	private List<ClassificationClass> classification_classes = new ArrayList<>();
	@OneToMany(mappedBy = "lectures", cascade = CascadeType.ALL)
	private List<LectureSalary> lectureSalaries = new ArrayList<>();
	@OneToMany(mappedBy = "lectures")
	private Set<AttendanceTrackingLecture> attendanceTrackingLectures = new HashSet<>();
}
