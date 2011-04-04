package com.retwis.service;

import java.util.List;
import java.util.Set;

import com.retwis.User;

/**
 * 
 * @author y.nie
 * @date 2011-4-4
 * @version 1.0
 */
public interface IUserService {

	void save(User user);

	User load(long id);
	
	long getIdByName(String userName);
	
	boolean checkExistByName(String userName);

	User checkLogin(String username, String password);
	
	void addFollowee(long currUserId, long followeeUserId);
	
	Set<String> getFollowers(long userId);
	
	void init();

	Set<String> getFollowees(long userId);
	
	List<String> getNewUsers(int page);

	User loadByName(String userName);

	boolean checkFlollowing(long currUserId, long targeUserId);

	void addFollowee(String currUserName, String targetUserName);

	void removeFollowee(String currUserName, String targetUserName);
}