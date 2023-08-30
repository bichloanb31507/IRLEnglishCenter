package com.graduate.IRLEnglishcenter.dto;

import java.time.LocalDate;

import com.graduate.IRLEnglishcenter.entity.EducationProgram;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationProgramResponse {
	private Long id;
	private String name;
}
