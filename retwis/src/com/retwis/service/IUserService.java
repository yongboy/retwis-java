package com.retwis.service;

import com.retwis.User;
import com.retwis.service.base.IBaseService;

public interface IUserService extends IBaseService<User> {

	long getNextUid();

	void save(User user);

	User get(long id);
	
	long getIdByName(String userName);
}