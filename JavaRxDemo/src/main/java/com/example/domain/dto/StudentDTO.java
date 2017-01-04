package com.example.domain.dto;

/**
 * This domain class represents a Student entity in our system.
 *
 */
public class StudentDTO extends BaseStudentDTO {
	private String id;

	public StudentDTO(String name, int age, double gpa, String stream) {
		super(name, age, gpa, stream);
	}

	public StudentDTO(String name, int age, double gpa, String stream, String id) {
		super(name, age, gpa, stream);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
