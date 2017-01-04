package com.example.repository;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.example.domain.dto.BaseStudentDTO;
import com.example.domain.dto.StudentDTO;
import com.example.transformer.DocumentToStudentTransformer;
import com.mongodb.rx.client.MongoClient;
import com.mongodb.rx.client.MongoClients;
import com.mongodb.rx.client.MongoCollection;
import com.mongodb.rx.client.MongoDatabase;
import com.mongodb.rx.client.Success;

import rx.Observable;

@Repository
public class StudentDAO implements StudentRepository {
	private static final String STUDENT_COLLECTION = "student";

	MongoCollection<Document> collection;
	private static final Logger log = LoggerFactory.getLogger(StudentDAO.class);

	@Autowired
	StudentDAO(@Value("${spring.data.mongodb.uri}") String connectionUrl,
			@Value("${spring.data.mongodb.database}") String dbName) {
		MongoClient mongoClient = MongoClients.create(connectionUrl);
		MongoDatabase database = mongoClient.getDatabase(dbName);
		collection = database.getCollection(STUDENT_COLLECTION);
	}

	public Observable<Success> createStudent(BaseStudentDTO student) {
		log.debug("Inserting a new Student ...");
		return collection.insertOne(createStudentDocument(student));
	}

	private Document createStudentDocument(BaseStudentDTO student) {
		return new Document(DocumentToStudentTransformer.NAME, student.getName())
				.append(DocumentToStudentTransformer.AGE, student.getAge())
				.append(DocumentToStudentTransformer.GPA, student.getGpa())
				.append(DocumentToStudentTransformer.STREAM, student.getStream());
	}

	public Observable<StudentDTO> findByName(String name) {
		log.debug("Fetching the student with name: " + name);
		return collection.find(eq(DocumentToStudentTransformer.NAME, name)).toObservable()
				.map(sub -> new DocumentToStudentTransformer().transform(sub));
	}
}
