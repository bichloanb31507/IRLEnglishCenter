package com.graduate.IRLEnglishcenter.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transcripts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transcript {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="listening1")
	private Double listening1;
	@Column(name="speaking1")
	private Double speaking1;
	@Column(name="reading1")
	private Double reading1;
	@Column(name="writing1")
	private Double writing1;
	@Column(name="listening2")
	private Double listening2;
	@Column(name="speaking2")
	private Double speaking2;
	@Column(name="reading2")
	private Double reading2;
	@Column(name="writing2")
	private Double writing2;
	@Column(name="listening_exam")
	private Double listening_exam;
	@Column(name="reading_exam")
	private Double reading_exam;
	@Column(name="speaking_exam")
	private Double speaking_exam;
	@Column(name="writing_exam")
	private Double writing_exam;
}
