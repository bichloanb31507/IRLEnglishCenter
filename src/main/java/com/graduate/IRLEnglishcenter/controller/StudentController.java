package com.graduate.IRLEnglishcenter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.time.LocalDate;

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

import com.graduate.IRLEnglishcenter.dto.StudentRequest;
import com.graduate.IRLEnglishcenter.dto.StudentResponse;
import com.graduate.IRLEnglishcenter.entity.Student;
import com.graduate.IRLEnglishcenter.exception.ResourceNotFoundException;
import com.graduate.IRLEnglishcenter.repository.StudentRepository;

@RestController
@RequestMapping("/api")
public class StudentController {
	@Autowired
	StudentRepository studentRepository;

	@GetMapping("/students")
	public ResponseEntity<List<StudentResponse>> getAllStudent(@RequestParam(required = false) String code) {
		try {
			List<Student> courseData = new ArrayList<Student>();

			if (code == null)
				studentRepository.findAll().forEach(courseData::add);
			else
				studentRepository.findByCodeContaining(code).forEach(courseData::add);

			if (courseData.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<StudentResponse> response = new ArrayList<>();

			for (Student item : courseData) {
				response.add(new StudentResponse(item.getId(), item.getCode(), item.getFullname(), item.getEmail(),
						item.getDateOfBirth(), item.getGender(), item.getCitizenId(), item.getAddress()));
			}

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<StudentResponse> getStudentsById(@PathVariable(value = "id") Long id) {
		Student item = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Student with id = " + id));
		StudentResponse response = new StudentResponse(item.getId(), item.getCode(), item.getFullname(),
				item.getEmail(), item.getDateOfBirth(), item.getGender(), item.getCitizenId(), item.getAddress());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/students")
	public ResponseEntity<Student> createStudent(@RequestBody StudentRequest studentRequest) {
		try {
			Student student = new Student();
			String code = "SV" + studentRequest.getDateOfBirth().getYear() + generateRandomCode();
			student.setCode(code);
			student.setFullname(studentRequest.getFullname());
			student.setEmail(studentRequest.getEmail());
			student.setDateOfBirth(studentRequest.getDateOfBirth());
			student.setGender(studentRequest.getGender());
			student.setCitizenId(studentRequest.getCitizenId());
			student.setAddress(studentRequest.getAddress());
			student.setAccounts(studentRequest.getAccount());
			student.setPhoneNumber(studentRequest.getPhone());
			studentRepository.save(student);
			return new ResponseEntity<>(student, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	private String generateRandomCode() {
		Random random = new Random();
		StringBuilder codeBuilder = new StringBuilder();
		boolean isUnique = false;

		while (!isUnique) {
//			int randomNumber = random.nextInt(900) + 100; // Sinh số ngẫu nhiên từ 100 đến 999
//			codeBuilder.append(randomNumber);
//			String code = "SV" + codeBuilder.toString();
			int randomNumber = random.nextInt(999) + 1; // Sinh số ngẫu nhiên từ 1 đến 999
		    String code = String.format("SV%03d", randomNumber); // Định dạng mã code với 3 chữ số và số 0 đứng đầu (nếu cần)

			// Kiểm tra tính duy nhất của mã code
			if (!isCodeExists(code)) {
				isUnique = true;
			} else {
				codeBuilder.setLength(0); // Xóa nội dung StringBuilder để sinh mã mới
			}
		}

		return codeBuilder.toString();
	}

	private boolean isCodeExists(String code) {
		Student student = studentRepository.findByCode(code);
		return student != null; // Trả về true nếu tồn tại sinh viên với mã code đã cho
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateCourse(@PathVariable(value = "id") Long id,
			@RequestBody StudentRequest studentRequest) {
		try {
			// Tìm kiếm khóa học cần cập nhật
			Student student = studentRepository.findById(id)

					.orElseThrow(() -> new ResourceNotFoundException("Not found Student with id = " + id));

			// Cập nhật thông tin hoc vien
			student.setFullname(studentRequest.getFullname());
			student.setEmail(studentRequest.getEmail());
			student.setDateOfBirth(studentRequest.getDateOfBirth());
			student.setGender(studentRequest.getGender());
			student.setCitizenId(studentRequest.getCitizenId());
			student.setAddress(studentRequest.getAddress());
			student.setPhoneNumber(studentRequest.getPhone());

			// Lưu hoc vien đã được cập nhật vào cơ sở dữ liệu
			studentRepository.save(student);
			return new ResponseEntity<>(student, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
