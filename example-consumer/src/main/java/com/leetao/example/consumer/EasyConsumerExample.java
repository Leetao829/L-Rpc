package com.leetao.example.consumer;

import com.leetao.example.common.model.User;
import com.leetao.example.common.service.UserService;
import com.leetao.lrpc.proxy.ServiceProxyFactory;

/**
 * 服务消费者启动类
 *
 * @author leetao
 */
public class EasyConsumerExample {

	public static void main(String[] args) throws Exception {

		//通过代理获取UserService代理对象
		UserService userService = ServiceProxyFactory.getProxy(UserService.class);
		User user = new User();
		user.setName("李涛");
		User newUser = userService.getUser(user);
		if(newUser != null){
			System.out.println(newUser.getName());
		}else {
			System.out.println("newUser == null !!!");
		}
	}

}
