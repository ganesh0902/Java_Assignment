package com.custom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.custom.entities.User;

@Service
public class UserService {

	private List<User> user = new ArrayList<>();

	public UserService() {
		user.add(new User(UUID.randomUUID().toString(), "Ganesh", "ganeshs2987@gmail.com"));
	}
}