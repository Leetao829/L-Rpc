package com.leetao.lrpc.server;

import io.vertx.core.Vertx;

/**
 * 服务器启动实例
 * @author leetao
 */
public class VertxHttpServer implements HttpServer{

	@Override
	public void doStart(int port) {
		//创建vertx实例
		Vertx vertx = Vertx.vertx();
		//创建Http服务器
		io.vertx.core.http.HttpServer httpServer = vertx.createHttpServer();
		//监听端口并处理请求
		httpServer.requestHandler(new HttpServerHandler());
		//启动服务器并监听端口
		httpServer.listen(port,result->{
			if(result.succeeded()){
				System.out.println("Server is now listening port "+port);
			}else {
				System.out.println("Failed to start server:"+result.cause());
			}
		});
	}

}
