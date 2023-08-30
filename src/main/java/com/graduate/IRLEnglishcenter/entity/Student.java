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
//Hoc vien
@Entity
@Table(name = "students", uniqueConstraints = { @UniqueConstraint(columnNames = "citizenId"),@UniqueConstraint(columnNames = "email") })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "code")
	private String code;
	@Column(name = "fullname")
	private String fullname;
	@Column(name = "email")
    private String email;
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
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account accounts;
	
	@OneToMany(mappedBy = "students")
	private Set<RegisterCourse> registerCourses = new HashSet<>();
	@OneToMany(mappedBy = "students")
	private Set<StudentClassification> student_classifications = new HashSet<>();
}
