package com.vworld4u.models;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class UserTypeDeserializer extends JsonDeserializer<UserType>{

	@Override
	public UserType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		if (p.getValueAsString().equalsIgnoreCase("Student")) {
			return UserType.STUDENT;
		}
		return UserType.TEACHER;
	}

}