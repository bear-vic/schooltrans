package com.example.schooltrans.service;

/**
 * @description 
 * @date 2016-8-23
 * @author hxg	
 */

import java.util.HashMap;

import com.example.schooltrans.entity.User;

public class UserManager {
	private HashMap<String, User> userMap;

	private UserManager() {
		userMap = new HashMap<String, User>();
	}

	static class Nest {
		private static UserManager instance = new UserManager();
	}

	public static UserManager getInstance() {
		return Nest.instance;
	}

	public void addUser(String tag, User user) {
		userMap.put(tag, user);
	}

	public User findUser(String tag) {
		return userMap.get(tag);
	}

	public boolean shouldLogin() {
		return userMap.isEmpty();
	}

	public void exitUser() {
		userMap.clear();
	}
}
