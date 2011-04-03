package com.retwis.service;

import java.util.List;

import com.retwis.Status;

public interface IStatusService {
	void save(long userId, String value, String userIp);

	Status get(long id);

	void init();
	
	List<Status> page(int page);
}
