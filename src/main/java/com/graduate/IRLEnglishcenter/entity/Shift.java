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
//Ca hoc
@Entity
@Table(name = "shifts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;
	@Column(name = "study_duration")
	private String study_duration;
	@OneToMany(mappedBy = "shifts", cascade = CascadeType.ALL)
    private List<ClassificationClass> classification_classes = new ArrayList<>();
	public Shift(String name, String study_duration) {
		super();
		this.name = name;
		this.study_duration = study_duration;
	}
	
}
