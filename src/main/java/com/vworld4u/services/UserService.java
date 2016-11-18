package com.vworld4u.services;

import com.vworld4u.models.User;

public interface UserService {
	public User register(User user) throws Exception;
	public User editUser(User user) throws Exception;
	public User getUserByEmail(String email) throws Exception;
	public User getUserById(long id) throws Exception;
}
