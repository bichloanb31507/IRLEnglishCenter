package com.graduate.IRLEnglishcenter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduate.IRLEnglishcenter.dto.EducationProgramResponse;
import com.graduate.IRLEnglishcenter.entity.EducationProgram;
import com.graduate.IRLEnglishcenter.exception.ResourceNotFoundException;
import com.graduate.IRLEnglishcenter.repository.EducationProgramRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EducationProgramController {
@Autowired 
EducationProgramRepository educationProgramRepository;
@GetMapping("/educationprogram")
public ResponseEntity<List<EducationProgramResponse>> getAllEducationProgram(@RequestParam(required = false) String name) {
  try {
    List<EducationProgram> educationPrograms = new ArrayList<EducationProgram>();

    if (name == null)
    	educationProgramRepository.findAll().forEach(educationPrograms::add);
    else
    	educationProgramRepository.findByNameContaining(name).forEach(educationPrograms::add);

    if (educationPrograms.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    List<EducationProgramResponse> responses = new ArrayList<>();
    for(EducationProgram item: educationPrograms) {
    	responses.add(new EducationProgramResponse(item.getId(),item.getName(),item.getDescription()));
    }
    return new ResponseEntity<>(responses, HttpStatus.OK);
  } catch (Exception e) {
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
@GetMapping("/educationprogram/{id}")
public ResponseEntity<EducationProgramResponse> getEducationProgramById(@PathVariable("id") long id) {
	EducationProgram item = educationProgramRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = " + id));
	EducationProgramResponse response = new EducationProgramResponse(item.getId(),item.getName(),item.getDescription());

	return new ResponseEntity<>(response, HttpStatus.OK);
}
@PostMapping("/educationprogram")
public ResponseEntity<EducationProgram> createEducationProgram(@Valid @RequestBody EducationProgram educationProgram) {
  try {
	  EducationProgram _edEducationProgram = educationProgramRepository
        .save(new EducationProgram(educationProgram.getName(), educationProgram.getDescription()));
    return new ResponseEntity<>(_edEducationProgram, HttpStatus.CREATED);
  } catch (Exception e) {
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
@PutMapping("/educationprogram/{id}")
public ResponseEntity<EducationProgram> updateEducationProgram(@PathVariable("id") long id, @RequestBody EducationProgram educationProgram) {
	Optional<EducationProgram> programData= educationProgramRepository.findById(id);

  if (programData.isPresent()) {
	  EducationProgram _program = programData.get();
	  _program.setName(educationProgram.getName());
	  _program.setDescription(educationProgram.getDescription());
    return new ResponseEntity<>(educationProgramRepository.save(_program), HttpStatus.OK);
  } else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
}
