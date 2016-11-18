package com.vworld4u.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vworld4u.models.User;
import com.vworld4u.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class RestApiController {
	private static final Logger log = LoggerFactory.getLogger(RestApiController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(method=RequestMethod.POST, path="/login")
	public User login(@RequestBody User user, HttpServletResponse response) throws Exception {
		User loggedInUser = userService.getUserByEmail(user.getEmail());
 		if (loggedInUser == null) throw new RuntimeException("No user registered with Email: " + user.getEmail());
 		if (!loggedInUser.getPassword().equals(user.getPassword())) throw new RuntimeException("Invalid Credentials");
 		String token = Jwts.builder().setSubject(loggedInUser.getEmail()).claim("user", loggedInUser).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretpass").compact();
 		response.setHeader("Authorization", "Bearer " + token);
 		return loggedInUser;
	}

	@RequestMapping(method=RequestMethod.POST, path="/register")
	public User register(@RequestBody User user) throws Exception {
		log.info("register: " + user);
		return userService.register(user);
	}

	@RequestMapping(method=RequestMethod.GET, path="/profile")
	public User profile() throws Exception {
		throw new RuntimeException("Not yet implemented");
	}

	@RequestMapping(method=RequestMethod.PUT, path="/profile")
	public User profile(User user) throws Exception {
		throw new RuntimeException("Not yet implemented");
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/hello")
	public String hello(@RequestParam(value="name", defaultValue="World") String name) {
		return new StringBuilder("Hello ").append(name).append('!').toString();
	}
}
