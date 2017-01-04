package com.example.transformer;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.example.domain.dto.BaseStudentDTO;
import com.example.domain.dto.StudentDTO;

public class DocumentToStudentTransformer implements Transformer<Document, BaseStudentDTO> {

	private static final String ID = "_id";
	public static final String STREAM = "stream";
	public static final String GPA = "gpa";
	public static final String AGE = "age";
	public static final String NAME = "name";

	@Override
	public StudentDTO transform(Document source) {
		return new StudentDTO(source.getString(NAME), source.getInteger(AGE), source.getDouble(GPA),
				source.getString(STREAM), ((ObjectId) source.get(ID)).toString());
	}
}
