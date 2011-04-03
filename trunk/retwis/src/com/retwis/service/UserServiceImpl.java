package com.retwis.service;

import com.retwis.User;
import com.retwis.service.base.BaseServiceImpl;
import com.retwis.util.MD5;

public class UserServiceImpl extends BaseServiceImpl<User> implements
		IUserService {

	public void save(User user) {
		user.setId(getNextUid());
		user.setPass(MD5.encode(user.getPass()));
		super.save(getId(user.getId()), user);

		super.saveStr(String.format(USER_NAME_FORMAT, user.getName()),
				Long.toString(user.getId()));
	}

	public User get(long id) {
		return super.get(getId(id));
	}

	private String getId(long id) {
		return String.format(USER_ID_FORMAT, id);
	}

	private String getName(String name) {
		return String.format(USER_NAME_FORMAT, name);
	}

	private static final String GLOBAL_USER_ID = "global:nextUserId";
	private static final String USER_ID_FORMAT = "user:id:%s";
	private static final String USER_NAME_FORMAT = "user:name:%s";
	//user:id:#{id}:followees
	//user:id:#{id}:followers

	public static void main(String[] args) {
		User user = new User();
		user.setName("xiaoi");
		user.setPass("ipass");
		user.setDate(System.currentTimeMillis());

		UserServiceImpl service = new UserServiceImpl();
		service.save(user);

		System.out.println("save done ...");

		User u = service.checkLogin(user.getName(), user.getPass());

		if (u == null) {
			System.out.println("get user is null !");
		} else {
			System.out.println(u + " with date : " + u.getSaveDate());
			System.out.println("password : " + u.getPass());
		}
	}

	private long getNextUid() {
		return super.incr(GLOBAL_USER_ID);
	}

	public void init() {
		String currUserId = getStr(GLOBAL_USER_ID);
		long currUid = 0L;
		if (currUserId == null || currUserId.length() == 0)
			currUid = -1L;

		if(currUid < 1000L){
			saveStr(GLOBAL_USER_ID, Long.toString(currUid));
		}
	}

	public long getIdByName(String userName) {
		String id = super.getStr(getName(userName));

		if (id == null || id.equals(""))
			return -1L;

		return Long.valueOf(id);
	}

	public boolean checkExistByName(String userName) {
		return getIdByName(userName) > 0L;
	}

	public User checkLogin(String username, String password) {
		long userId = getIdByName(username);

		if (userId < 1L)
			return null;

		return this.get(userId);
	}
}