package com.hexaware.innovation.prasad;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class UserResourceSample {
	public static final String USER_URI = "http://localhost:7080/RestWS/users";

	public String testGetUsersAll() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource resource = client.resource(USER_URI);
		ClientResponse response = resource.type(MediaType.APPLICATION_XML).get(
				ClientResponse.class);
		String en = response.getEntity(String.class);
		return en;
	}

	public String testGetUsers() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource resource = client.resource(USER_URI);
		ClientResponse response = resource.type(MediaType.APPLICATION_XML).get(
				ClientResponse.class);
		String en = response.getEntity(String.class);
		return en;
	}

	public static User testCreateUser() {
		User user = new User("John", "john@");
		Client client = Client.create();
		System.out.println("=====before response");
		WebResource r = client.resource(USER_URI);
		System.out.println("=====before response");
		ClientResponse response = r.post(
				ClientResponse.class, user);
		System.out.println("=====after responsese"+response);
		return user;
	}
}
