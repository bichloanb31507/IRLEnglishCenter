package com.graduate.IRLEnglishcenter.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
	private Long id;
	private String code;
	private String fullname;
	 private String email;
	 private LocalDate dateOfBirth;
	 private String gender;
	 private String citizenId;
	 private String address;
}
