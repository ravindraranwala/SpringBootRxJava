package com.example.repository;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.example.domain.dto.BaseStudentDTO;
import com.example.domain.dto.StudentDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.rx.client.MongoClient;
import com.mongodb.rx.client.MongoClients;
import com.mongodb.rx.client.MongoCollection;
import com.mongodb.rx.client.MongoDatabase;

import rx.Observable;

@Repository
public class StudentDAO implements StudentRepository {
	private static final String STUDENT_COLLECTION = "student";
	private static final String ID = "_id";
	public static final String STREAM = "stream";
	public static final String GPA = "gpa";
	public static final String AGE = "age";
	public static final String NAME = "name";

	MongoCollection<Document> collection;
	private static final Logger log = LoggerFactory.getLogger(StudentDAO.class);
	private static ObjectMapper mapper = new ObjectMapper();

	@Autowired
	StudentDAO(@Value("${spring.data.mongodb.uri}") String connectionUrl,
			@Value("${spring.data.mongodb.database}") String dbName) {
		MongoClient mongoClient = MongoClients.create(connectionUrl);
		MongoDatabase database = mongoClient.getDatabase(dbName);
		collection = database.getCollection(STUDENT_COLLECTION);
	}

	public Observable<?> createStudent(BaseStudentDTO student) {
		try {
			return collection.insertOne(
					mapper.readValue(mapper.writeValueAsString(student), new TypeReference<Map<String, Object>>() {
					})).doOnNext(s -> log.debug("Student was created successfully."))
					.doOnError(e -> log.error("An ERROR occurred while creating a new Student", e));
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public Observable<StudentDTO> findByName(String name) {
		log.debug("Fetching the student with name: " + name);
		return collection.find(eq(NAME, name)).toObservable()
				.map(doc -> new StudentDTO(doc.getString(NAME), doc.getInteger(AGE), doc.getDouble(GPA),
						doc.getString(STREAM), ((ObjectId) doc.get(ID)).toString()))
				.doOnNext(s -> log.debug("Student with the given name was retrieved."))
				.doOnError(e -> log.error("An ERROR occurred while fetching the student", e));
	}
}
