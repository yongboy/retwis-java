package com.retwis.service.base;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<V extends Serializable> {
	
	String getStr(String key);	
	void saveStr(String key, String value);	
	void updateStr(String key, String value);
	void remove(String key);
	
	V get(String key);
	void save(String key, V value);	
	void update(String key, V value);
	void removeStr(String key);
	
	
	List<String> find(int pageNum, int pageSize);
}
