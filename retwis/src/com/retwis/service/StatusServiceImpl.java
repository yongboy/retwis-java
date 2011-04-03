package com.retwis.service;

import java.util.List;

import com.retwis.Status;
import com.retwis.service.base.BaseServiceImpl;

public class StatusServiceImpl extends BaseServiceImpl<Status> implements
		IStatusService {

	public void save(long userId, String value, String userIp) {
		Status status = new Status();
		status.setId(getNextId());
		status.setUid(userId);
		status.setIp(userIp);
		status.setValue(value);

		super.save(getFormatId(status.getId()), status);
	}

	public Status get(long id) {
		return super.get(getFormatId(id));
	}

	private String getFormatId(long id) {
		return String.format(STATUS_ID_FORMAT, id);
	}

	private long getNextId() {
		return super.incr(GLOBAL_STATUS_ID);
	}

	public void init() {
		String currStatusId = getStr(GLOBAL_STATUS_ID);
		long currUid = 0L;
		if (currStatusId == null || currStatusId.length() == 0)
			currUid = -1L;

		if (currUid < 1000L) {
			saveStr(GLOBAL_STATUS_ID, Long.toString(currUid));
		}
	}

	public List<Status> page(int page) {
		if(page < 1)page = 1;
		
		int startIndex = (page-1) * 10;
		int endIndex = page * 10;
		
		return null;
	}

	private static final String GLOBAL_STATUS_ID = "global:nextStatusId";
	private static final String STATUS_ID_FORMAT = "status:id:%s";
	//user:id:#{id}:posts"
	//user:id:#{id}:timeline
	//user:id:#{id}:mentions
}