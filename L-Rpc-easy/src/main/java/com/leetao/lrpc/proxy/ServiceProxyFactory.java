package com.leetao.lrpc.proxy;

import java.lang.reflect.Proxy;

/**
 * 服务代理工厂，用于创建服务代理对象
 *
 * @author leetao
 */
public class ServiceProxyFactory {

	/**
	 * 根据服务类创建代理对象
	 * @param serviceClass  服务类
	 * @param <T>  泛型
	 * @return  代理对象
	 */
	public static <T> T getProxy(Class<T> serviceClass){

		return (T) Proxy.newProxyInstance(
				serviceClass.getClassLoader(),
				new Class[]{serviceClass},
				new ServiceProxy());

	}

}
