package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.domain.dto.BaseStudentDTO;
import com.example.repository.StudentRepository;

@Controller
@RequestMapping("/api/students")
public class StudentResource {
	@Autowired
	private StudentRepository studentRepository;
	
	private static final Logger log = LoggerFactory.getLogger(StudentResource.class);

	@RequestMapping(value = "", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public DeferredResult<ResponseEntity<?>> createStudent(@RequestBody BaseStudentDTO student) {
		DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<ResponseEntity<?>>();
		log.debug("Creating a new Student.");
		studentRepository.createStudent(student).subscribe(
				sub -> deferredResult.setResult(new ResponseEntity<>(sub, HttpStatus.OK)),
				e -> deferredResult.setErrorResult(e));

		return deferredResult;
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public DeferredResult<ResponseEntity<BaseStudentDTO>> getStudentByName(@RequestParam String name) {
		DeferredResult<ResponseEntity<BaseStudentDTO>> deferredResult = new DeferredResult<ResponseEntity<BaseStudentDTO>>();
		log.debug("Fetching a new student with the Name: " + name);
		studentRepository.findByName(name).subscribe(sub -> deferredResult.setResult(ResponseEntity.ok(sub)),
				e -> deferredResult.setErrorResult(e));
		return deferredResult;
	}
}
