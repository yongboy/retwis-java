package com.retwis.service;

import java.util.List;
import java.util.Set;

import com.retwis.User;

public interface IUserService {

//	long getNextUid();
//	
//	long getCurrUid();
//	
//	void setCurrUid(long userId);

	void save(User user);

	User load(long id);
	
	long getIdByName(String userName);
	
	boolean checkExistByName(String userName);

	User checkLogin(String username, String password);
	
	void addFollowee(long currUserId, long followeeUserId);
//	
//	void addFollowees(String ... userId);
	
	Set<String> getFollowers(long userId);
	
	void init();

	Set<String> getFollowees(long userId);
	
	List<String> getNewUsers(int page);

	User loadByName(String userName);

	boolean checkFlollowing(long currUserId, long targeUserId);
}