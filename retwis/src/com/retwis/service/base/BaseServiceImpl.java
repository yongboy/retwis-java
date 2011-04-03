package com.retwis.service.base;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import redis.clients.jedis.Jedis;

public abstract class BaseServiceImpl<V extends Serializable> implements
		IBaseService<V> {
	private static final String REDIS_HOST = "192.168.0.199";
	Jedis jedis = new Jedis(REDIS_HOST);

	public V get(String key) {
		return byte2Object(jedis.get(getKey(key)));
	}

	public void save(String key, V value) {
		jedis.set(getKey(key), object2Bytes(value));
	}

	public void update(String key, V value) {
		this.save(key, value);
	}

	public void remove(String key) {
		jedis.del(getKey(key));
	}
	
	public String getStr(String key) {
		return this.jedis.get(key);
	}

	public void saveStr(String key, String value) {
		this.jedis.set(key, value);
	}

	public void updateStr(String key, String value) {
		saveStr(key, value);
	}

	public List<String> find(int pageNum, int pageSize) {
		return null;
	}

	public void removeStr(String key) {
		this.jedis.del(key);
	}

	private byte[] getKey(String key) {
		return key.getBytes();
	}

	public Long incr(String key) {
		return this.jedis.incr(key);
	}
	
	public void addHeadList(String key, String oneValue){
		this.jedis.lpush(key, oneValue);
	}

	@SuppressWarnings("unchecked")
	private V byte2Object(byte[] bytes) {
		try {
			ObjectInputStream inputStream;
			inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
			Object obj = inputStream.readObject();

			return (V) obj;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	private byte[] object2Bytes(V value) {
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream outputStream;
		try {
			outputStream = new ObjectOutputStream(arrayOutputStream);

			outputStream.writeObject(value);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				arrayOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return arrayOutputStream.toByteArray();
	}
}