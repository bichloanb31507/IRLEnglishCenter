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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//phieu thu hoc phi
@Entity
@Table(name = "tuitionfee_receipt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TuitionFeeReceipt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	@Column(name = "datecreated")
	private LocalDate deatecreated;
	@Column(name = "discount")
	private Double discount;
	@Column(name = "total")
	private Double total;
	@ManyToOne
    @JoinColumn(name = "student_id")
	 @JsonIgnore
    private Student students;
	@ManyToOne
    @JoinColumn(name = "employee_id")
	 @JsonIgnore
    private Employee employees;
	
	
}
