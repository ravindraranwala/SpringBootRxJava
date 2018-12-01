package com.example.domain.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to capture input data for a Student from the user.
 *
 */
public class BaseStudentDTO {
	@NotNull
	@NotEmpty
	private final String name;
	@Min(value = 1, message = "Age should be a positive number")
	private final int age;
	@Min(value = 0, message = "Invalid gpa value was given")
	private final double gpa;
	@NotNull
	@NotEmpty
	private final String stream;

	@JsonCreator
	public BaseStudentDTO(@JsonProperty("name") String name, @JsonProperty("age") int age,
			@JsonProperty("gpa") double gpa, @JsonProperty("stream") String stream) {
		super();
		this.name = name;
		this.age = age;
		this.gpa = gpa;
		this.stream = stream;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public double getGpa() {
		return gpa;
	}

	public String getStream() {
		return stream;
	}
}
