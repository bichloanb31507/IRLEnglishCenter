package com.graduate.IRLEnglishcenter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduate.IRLEnglishcenter.entity.EducationProgram;
import com.graduate.IRLEnglishcenter.repository.EducationProgramRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EducationProgramController {
@Autowired 
EducationProgramRepository educationProgramRepository;
@GetMapping("/educationprogram")
public ResponseEntity<List<EducationProgram>> getAllEducationProgram(@RequestParam(required = false) String name) {
  try {
    List<EducationProgram> educationPrograms = new ArrayList<EducationProgram>();

    if (name == null)
    	educationProgramRepository.findAll().forEach(educationPrograms::add);
    else
    	educationProgramRepository.findByNameContaining(name).forEach(educationPrograms::add);

    if (educationPrograms.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(educationPrograms, HttpStatus.OK);
  } catch (Exception e) {
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
@GetMapping("/educationprogram/{id}")
public ResponseEntity<EducationProgram> getEducationProgramById(@PathVariable("id") long id) {
  Optional<EducationProgram> programData = educationProgramRepository.findById(id);

  if (programData.isPresent()) {
    return new ResponseEntity<>(programData.get(), HttpStatus.OK);
  } else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
}
