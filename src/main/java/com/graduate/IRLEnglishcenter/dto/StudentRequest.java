package com.graduate.IRLEnglishcenter.dto;

import java.time.LocalDate;

import com.graduate.IRLEnglishcenter.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest{
	private String code;
	private String fullname;
	 private String email;
	 private LocalDate dateOfBirth;
	 private String gender;
	 private String phone;
	 private String citizenId;
	 private String address;
	 private Account account;
}