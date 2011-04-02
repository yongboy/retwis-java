package com.retwis.service;

import com.retwis.User;
import com.retwis.service.base.BaseServiceImpl;

public class UserServiceImpl extends BaseServiceImpl<User> implements
		IUserService {

	public void save(User user) {
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

	private static final String USER_ID_FORMAT = "user:id:%s";
	private static final String USER_NAME_FORMAT = "user:name:%s";

	public static void main(String[] args) {
		long id = 1000L;
		User user = new User();
		user.setId(id);
		user.setName("xiaoi");
		user.setPass("ipass");
		user.setDate(System.currentTimeMillis());

		UserServiceImpl service = new UserServiceImpl();
		service.save(user);

		System.out.println("save done ...");

		User u = service.get(id);

		if (u == null) {
			System.out.println("get user is null !");
		} else {
			System.out.println(u + " with date : " + u.getSaveDate());
		}
	}

	public long getNextUid() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getIdByName(String userName) {
		String id = super.getStr(userName);

		if (id == null || id.endsWith(""))
			return -1L;

		return Long.valueOf(id);
	}

	public boolean checkExistByName(String userName) {
		return getIdByName(userName) > 0L;
	}
}