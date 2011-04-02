package com.retwis.service.base;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<V extends Serializable> {

	V get(String key);
	
	void save(String key, V value);	
	void update(String key, V value);
	
	void remove(String key);
	
	List<String> find(int pageNum, int pageSize);
}
