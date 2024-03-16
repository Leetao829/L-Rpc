package com.leetao.lrpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地服务注册器
 */
public class LocalRegistry {
	/**
	 * 注册信息存储，使用同步阻塞队列
	 */
	private static final Map<String,Class<?>> map = new ConcurrentHashMap<>();

	/**
	 * 注册服务
	 * @param serviceName 服务名称
	 * @param implClass  实现服务
	 */
	public static void register(String serviceName,Class<?> implClass){
		map.put(serviceName,implClass);
	}

	/**
	 * 获取服务
	 * @param serviceName
	 * @return
	 */
	public static Class<?> get(String serviceName){
		return map.get(serviceName);
	}

	/**
	 * 删除服务
	 * @param serviceName
	 */
	public static void removeService(String serviceName){
		map.remove(serviceName);
	}


}
