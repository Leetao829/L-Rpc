package com.leetao.example.provider;

import com.leetao.example.common.service.UserService;
import com.leetao.lrpc.registry.LocalRegistry;
import com.leetao.lrpc.server.HttpServer;
import com.leetao.lrpc.server.VertxHttpServer;

/**
 * 提供服务启动类
 *
 * @author leetao
 */
public class EasyProviderExample {

	public static void main(String[] args) {

		//注册服务
		LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);
		HttpServer httpServer = new VertxHttpServer();
		httpServer.doStart(8080);


	}

}
