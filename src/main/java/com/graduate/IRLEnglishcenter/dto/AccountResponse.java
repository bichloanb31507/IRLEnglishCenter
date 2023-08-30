package com.graduate.IRLEnglishcenter.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.graduate.IRLEnglishcenter.entity.Employee;
import com.graduate.IRLEnglishcenter.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
	private Long id;
	private String username;
	private Role role;
}
