package com.graduate.IRLEnglishcenter.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
//Nhan vien
@Entity
@Table(name = "employees", uniqueConstraints = { @UniqueConstraint(columnNames = "citizenId") })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
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
	@Column(name = "position")
    private String position;
	@Column(name = "qualification")
    private String qualification;
	@Column(name = "day_start")
	private LocalDate day_start;
	@Column(name = "basicSalary")
    private double basicSalary;
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account accounts;
	@OneToMany(mappedBy = "employees")
	private Set<TuitionFeeReceipt> tuitionFeeReceipts = new HashSet<>();
	@OneToMany(mappedBy = "employees")
	private Set<EmployeeSalary> employeeSalaries = new HashSet<>();
	@OneToMany(mappedBy = "employees")
	private Set<AttendanceTrackingEmployee> attendanceTrackingEmployees = new HashSet<>();
}
