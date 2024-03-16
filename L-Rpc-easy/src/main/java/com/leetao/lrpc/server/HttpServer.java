package com.leetao.lrpc.server;

/**
 * 定义统一服务器启动接口
 *
 * @author leetao
 */
public interface HttpServer {

	/**
	 * 启动服务器
	 * @param port 启动端口
	 */
	void doStart(int port);

}
