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
//Thoi gian hoc
@Entity
@Table(name = "study_times")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;
	@Column(name = "number_of_day_study")
	private int number_of_day_study;
	@OneToMany(mappedBy = "study_times", cascade = CascadeType.ALL)
    private List<ClassificationClass> classification_classes = new ArrayList<>();
	public StudyTime(String name, int number_of_day_study) {
		super();
		this.name = name;
		this.number_of_day_study = number_of_day_study;
	}
	
}
