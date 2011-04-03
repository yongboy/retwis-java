package com.retwis;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author yongboy
 * @date 2011-4-2
 * @version 1.0
 */
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String value;
	private long date = System.currentTimeMillis();
	private long uid;
	private String ip;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getSaveDate() {
		return new Date(date);
	}
}