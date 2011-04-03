package com.retwis.service;

import com.retwis.Status;

public interface IStatusService {
	void save(long userId, String value, String userIp);

	Status get(long id);

	void init();
}
