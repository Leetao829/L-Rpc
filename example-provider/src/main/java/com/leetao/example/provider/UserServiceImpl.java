package com.leetao.example.provider;

import com.leetao.example.common.model.User;
import com.leetao.example.common.service.UserService;

/**
 * 提供服务
 */
public class UserServiceImpl implements UserService {

	@Override
	public User getUser(User user) {
		System.out.println(user.getName());
		return user;
	}

}
