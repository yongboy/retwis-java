package com.retwis;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author yongboy
 * @date 2011-4-2
 * @version 1.0
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String pass;

	private long date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
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

	public String toString() {
		return "User[id:" + getId() + "; name:" + getName() + "; date:"
				+ getDate() + "]";
	}
}