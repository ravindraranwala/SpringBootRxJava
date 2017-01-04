package com.example.domain.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * This class is used to capture input data for a Student from the user.
 *
 */
public class BaseStudentDTO {
	@NotNull
	@NotEmpty
	private String name;
	@Min(value = 1, message = "Age should be a positive number")
	private int age;
	@Min(value = 0, message = "Invalid gpa value was given")
	private double gpa;
	@NotNull
	@NotEmpty
	private String stream;

	private BaseStudentDTO() {
		super();
	}

	public BaseStudentDTO(String name, int age, double gpa, String stream) {
		super();
		this.name = name;
		this.age = age;
		this.gpa = gpa;
		this.stream = stream;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}
}
