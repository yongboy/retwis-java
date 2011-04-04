package com.retwis.service;

import java.util.List;

import com.retwis.Status;

public interface IStatusService {
	void save(long userId, String value, String userIp);

	Status load(long id);

	void init();
	
	List<Status> timeline(int page);

	List<Status> timeline(long id, int page);
	
//	List<Status> timeline(long userId, int page);
}
