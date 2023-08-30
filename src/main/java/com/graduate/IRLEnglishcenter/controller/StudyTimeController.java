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

import com.graduate.IRLEnglishcenter.entity.StudyTime;
import com.graduate.IRLEnglishcenter.repository.StudyTimeRepository;


@RestController
@RequestMapping("/api")
public class StudyTimeController {
 @Autowired
 StudyTimeRepository studyTimeRepository;
 @GetMapping("/timestudies")
 public ResponseEntity<List<StudyTime>> getAllStudyTime(@RequestParam(required = false) String name) {
   try {
     List<StudyTime> timestudy = new ArrayList<StudyTime>();

     if (name == null)
    	 studyTimeRepository.findAll().forEach(timestudy::add);
     else
    	 studyTimeRepository.findByNameContaining(name).forEach(timestudy::add);

     if (timestudy.isEmpty()) {
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }

     return new ResponseEntity<>(timestudy, HttpStatus.OK);
   } catch (Exception e) {
     return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
   }
 }

 @GetMapping("/timestudy/{id}")
 public ResponseEntity<StudyTime> getStudyTimeById(@PathVariable("id") long id) {
   Optional<StudyTime> timeData = studyTimeRepository.findById(id);

   if (timeData.isPresent()) {
     return new ResponseEntity<>(timeData.get(), HttpStatus.OK);
   } else {
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
 }

 @PostMapping("/timestudy")
 public ResponseEntity<StudyTime> createStudyTime(@Valid @RequestBody StudyTime studytime) {
   try {
	   StudyTime _time = studyTimeRepository
         .save(new StudyTime(studytime.getName(), studytime.getNumber_of_day_study()));
     return new ResponseEntity<>(_time, HttpStatus.CREATED);
   } catch (Exception e) {
     return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
   }
 }

 @PutMapping("/timestudy/{id}")
 public ResponseEntity<StudyTime> updateCategory(@PathVariable("id") long id, @RequestBody StudyTime studytime) {
   Optional<StudyTime> timeData = studyTimeRepository.findById(id);

   if (timeData.isPresent()) {
	   StudyTime _time = timeData.get();
	   _time.setName(studytime.getName());
	   _time.setNumber_of_day_study(studytime.getNumber_of_day_study());
     return new ResponseEntity<>(studyTimeRepository.save(_time), HttpStatus.OK);
   } else {
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
 }

}
