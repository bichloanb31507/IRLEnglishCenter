package com.graduate.IRLEnglishcenter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduate.IRLEnglishcenter.entity.Shift;
import com.graduate.IRLEnglishcenter.repository.ShiftRepository;

@RestController
@RequestMapping("/api")
public class ShiftController {
	@Autowired
	ShiftRepository shiftRepository;
	@GetMapping("/shifts")
	 public ResponseEntity<List<Shift>> getAllShift(@RequestParam(required = false) String name) {
	   try {
	     List<Shift> shift = new ArrayList<Shift>();

	     if (name == null)
	    	 shiftRepository.findAll().forEach(shift::add);
	     else
	    	 shiftRepository.findByNameContaining(name).forEach(shift::add);

	     if (shift.isEmpty()) {
	       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	     }

	     return new ResponseEntity<>(shift, HttpStatus.OK);
	   } catch (Exception e) {
	     return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }

	 @GetMapping("/shift/{id}")
	 public ResponseEntity<Shift> getShiftById(@PathVariable("id") long id) {
	   Optional<Shift> shiftData = shiftRepository.findById(id);

	   if (shiftData.isPresent()) {
	     return new ResponseEntity<>(shiftData.get(), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }

	 @PostMapping("/shift")
	 public ResponseEntity<Shift> createShift(@Valid @RequestBody Shift shift) {
	   try {
		   Shift _shift = shiftRepository
	         .save(new Shift(shift.getName(), shift.getStudy_duration()));
	     return new ResponseEntity<>(_shift, HttpStatus.CREATED);
	   } catch (Exception e) {
	     return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }

	 @PutMapping("/shift/{id}")
	 public ResponseEntity<Shift> updateShift(@PathVariable("id") long id, @RequestBody Shift shift) {
	   Optional<Shift> shiftData = shiftRepository.findById(id);

	   if (shiftData.isPresent()) {
		   Shift _shift = shiftData.get();
		   _shift.setName(shift.getName());
		   _shift.setStudy_duration(shift.getStudy_duration());
	     return new ResponseEntity<>(shiftRepository.save(_shift), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }

}
