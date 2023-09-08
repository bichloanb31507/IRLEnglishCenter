package com.graduate.IRLEnglishcenter.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.graduate.IRLEnglishcenter.constants.Constants;
import com.graduate.IRLEnglishcenter.dto.CourseRequest;
import com.graduate.IRLEnglishcenter.dto.CourseResponse;
import com.graduate.IRLEnglishcenter.entity.Course;
import com.graduate.IRLEnglishcenter.entity.EStatusCourse;
import com.graduate.IRLEnglishcenter.exception.ResourceNotFoundException;
import com.graduate.IRLEnglishcenter.message.Message;
import com.graduate.IRLEnglishcenter.repository.CourseRepository;
import com.graduate.IRLEnglishcenter.service.CourseService;
import com.graduate.IRLEnglishcenter.dto.EducationProgramResponse;
import com.graduate.IRLEnglishcenter.dto.RegisterCourseRequest;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<CourseResponse> getAllCourse(String code) {
		List<Course> courseData = new ArrayList<Course>();

		if (code == null)
			courseRepository.findAll().forEach(courseData::add);
		else
			courseRepository.findByCodeContaining(code).forEach(courseData::add);

		List<CourseResponse> response = new ArrayList<>();

		for (Course item : courseData) {
			EducationProgramResponse educationProgramDTO = new EducationProgramResponse();
			educationProgramDTO.setId(item.getEducation_programs().getId());
			educationProgramDTO.setName(item.getEducation_programs().getName());
			educationProgramDTO.setDescription(item.getEducation_programs().getDescription());
//			response.add(new CourseResponse(item.getId(), item.getCode(), item.getName(), item.getTuition(),
//					item.getDay_opening(), item.getStatus(), item.getCourse_completion_time(), educationProgramDTO,
//					item.getCreated_at()));
		}

		return response;
	}

	@Override
	public CourseResponse getCourseById(Long id) {
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Course with id = " + id));

		EducationProgramResponse educationProgramDTO = new EducationProgramResponse();
		educationProgramDTO.setId(course.getEducation_programs().getId());
		educationProgramDTO.setName(course.getEducation_programs().getName());
		educationProgramDTO.setDescription(course.getEducation_programs().getDescription());

//		return new CourseResponse(course.getId(), course.getCode(), course.getName(), course.getTuition(),
//				course.getDay_opening(), course.getStatus(), course.getCourse_completion_time(), educationProgramDTO,
//				course.getCreated_at());
		return null;
	}

	@Override
	public ResponseEntity<String> createCourse(CourseRequest courseRequest) {
		try {
			if (!isValidData(courseRequest)) {
				return Message.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
			}
			Course course = new Course();
			course.setName(courseRequest.getName());
			course.setTuition(courseRequest.getTuition());
			course.setDay_opening(courseRequest.getDay_opening());
			course.setCourse_completion_time(courseRequest.getCourse_completion_time());
			course.setEducation_programs(courseRequest.getEducation_programs());
			course.setStatus(EStatusCourse.DANG_CHO);

			// Tạo mã bắt đầu bằng "CO_"
			StringBuilder codeBuilder = new StringBuilder("CO_");

			// Lấy giá trị của name sau dấu "_"
			String[] nameParts = courseRequest.getEducation_programs().getName().split("_");
			if (nameParts.length > 1) {
				codeBuilder.append(nameParts[1]);
			}

			// Lấy ngày, tháng và năm từ day_opening
			int day = courseRequest.getDay_opening().getDayOfMonth();
			int month = courseRequest.getDay_opening().getMonthValue();
			int year = courseRequest.getDay_opening().getYear() % 100; // Lấy hai chữ số cuối của năm

			// Định dạng chuỗi dayMonthYear có dạng "ddMMyy"
			String dayMonthYear = String.format("%02d%02d%02d", day, month, year);
			codeBuilder.append(dayMonthYear);

			course.setCode(codeBuilder.toString());

			courseRepository.save(course);
			return Message.getResponseEntity("Successfully create course.", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Message.getResponseEntity(Constants.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean isValidData(CourseRequest courseRequest) {
		// Kiểm tra dữ liệu đầu vào
		if (courseRequest == null) {
			return false;
		}

		// Kiểm tra các thuộc tính của đối tượng registerCourseRequest
		if (courseRequest.getName() == null || courseRequest.getCourse_completion_time() == null
				|| courseRequest.getDay_opening() == null || courseRequest.getEducation_programs() == null) {
			return false;
		}
		return true;
	}

	@Override
	public Course updateCourse(Long id, CourseRequest courseRequest) {
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Course with id = " + id));

		course.setName(courseRequest.getName());
		course.setTuition(courseRequest.getTuition());
		course.setDay_opening(courseRequest.getDay_opening());
		course.setCourse_completion_time(courseRequest.getCourse_completion_time());
		course.setEducation_programs(courseRequest.getEducation_programs());

		return courseRepository.save(course);
	}

	@Override
	public List<CourseResponse> getCoursesByStatus(EStatusCourse status) {
		List<Course> courses = courseRepository.findByStatus(status);
		return convertToCourseResponses(courses);
	}

	private List<CourseResponse> convertToCourseResponses(List<Course> courses) {
	    List<CourseResponse> courseResponses = new ArrayList<>();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	    for (Course course : courses) {
	        CourseResponse courseResponse = new CourseResponse();
	        courseResponse.setId(course.getId());
	        courseResponse.setCode(course.getCode());
	        courseResponse.setName(course.getName());
	        courseResponse.setTuition(course.getTuition());
	        courseResponse.setDay_opening(course.getDay_opening());
	        courseResponse.setStatus_course(course.getStatus());
	        courseResponse.setCourse_completion_time(course.getCourse_completion_time());

	        // Format LocalDateTime to string
	        String formattedDateTime = course.getCreated_at().format(formatter);
	        courseResponse.setCreated_at(formattedDateTime);

	        EducationProgramResponse educationProgramDTO = new EducationProgramResponse();
	        educationProgramDTO.setId(course.getEducation_programs().getId());
	        educationProgramDTO.setName(course.getEducation_programs().getName());
	        educationProgramDTO.setDescription(course.getEducation_programs().getDescription());
	        courseResponse.setEducation_programs(educationProgramDTO);

	        courseResponses.add(courseResponse);
	    }
	    return courseResponses;
	}

}