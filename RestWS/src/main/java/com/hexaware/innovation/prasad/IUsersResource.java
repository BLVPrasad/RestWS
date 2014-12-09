package com.hexaware.innovation.prasad;

import java.util.List;

import javax.ws.rs.PathParam;

public interface IUsersResource {
	
	public List<User> getUsersAll();
	
	public User createUser(User user);
	
	public List<User> getUser(String username);
}
