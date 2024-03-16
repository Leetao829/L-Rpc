package com.leetao.example.consumer;

import com.leetao.example.common.model.User;
import com.leetao.example.common.service.UserService;

/**
 * 服务消费者启动类
 *
 * @author leetao
 */
public class EasyConsumerExample {

	public static void main(String[] args) {

		//TODO 这里的对象需要从提供者那里获取
		UserService userService = null;
		User user = new User();
		user.setName("leetao");
		User newUser = userService.getUser(user);
		if(newUser != null){
			System.out.println(newUser.getName());
		}else {
			System.out.println("newUser == null !!!");
		}
	}

}
