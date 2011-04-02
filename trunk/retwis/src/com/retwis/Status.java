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
	private long date;

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

	public Date getSaveDate() {
		return new Date(date);
	}
}