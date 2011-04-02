package com.retwis.service;

import com.retwis.User;

public interface IUserService {

	long getNextUid();

	void save(User user);

	User get(long id);
	
	long getIdByName(String userName);
	
	boolean checkExistByName(String userName);
}