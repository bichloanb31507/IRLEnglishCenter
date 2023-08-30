package com.graduate.IRLEnglishcenter.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//Lop_phong
@Entity
@Table(name = "classification_rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassificationRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassRoom classrooms;

    @ManyToOne
    @JoinColumn(name = "classification_classe_id")
    @JsonIgnore
    private ClassificationClass classification_classes;
	@Column(name = "day_study")
	private LocalDate day_study;
	@Column(name = "time_study")
	private LocalDate time_study;
	@Column(name="lecture_id")
    private Long lectureId;
	
	

}
