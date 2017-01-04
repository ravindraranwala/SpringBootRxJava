package com.example.repository;

import com.example.domain.dto.BaseStudentDTO;
import com.example.domain.dto.StudentDTO;
import com.mongodb.rx.client.Success;

import rx.Observable;

/**
 * This defines the Data Access interface for Student Entity in our system.
 *
 */
public interface StudentRepository {
	/**
	 * Creates a new Student document in the database.
	 * 
	 * @param student
	 *            new {@link Student} instance to be created.
	 * @return The status of the operation.
	 */
	Observable<Success> createStudent(BaseStudentDTO student);

	/**
	 * Fetches a Student with the given name.
	 * 
	 * @param name
	 *            name of the student to be fetched.
	 * @return The student with the specified name.
	 */
	Observable<StudentDTO> findByName(String name);
}
