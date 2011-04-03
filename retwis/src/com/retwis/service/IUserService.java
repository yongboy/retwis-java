package com.retwis.service;

import com.retwis.User;

public interface IUserService {

//	long getNextUid();
//	
//	long getCurrUid();
//	
//	void setCurrUid(long userId);

	void save(User user);

	User get(long id);
	
	long getIdByName(String userName);
	
	boolean checkExistByName(String userName);

	User checkLogin(String username, String password);
	
	void init();
}