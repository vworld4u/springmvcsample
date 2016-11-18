package com.vworld4u.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String email;
	private String password;

//	@Enumerated(EnumType.STRING)
	@JsonDeserialize(using=UserTypeDeserializer.class)
	private Enum<UserType> userType;
	
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Enum<UserType> getUserType() {
		return userType;
	}

	public void setUserType(Enum<UserType> userType) {
		this.userType = userType;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", userType=" + userType + ", dateOfBirth="
				+ dateOfBirth + "]";
	}

	public void copyFrom(User user) {
		if (user.getName() != this.name) {
			setName(user.getName());
		}
		if (user.getDateOfBirth() != this.dateOfBirth) {
			setDateOfBirth(user.getDateOfBirth());
		}
		if (user.getUserType() != this.userType) {
			setUserType(user.getUserType());
		}
		if (user.getPassword() != this.password) {
			setPassword(user.getPassword());
		}
	}

}
