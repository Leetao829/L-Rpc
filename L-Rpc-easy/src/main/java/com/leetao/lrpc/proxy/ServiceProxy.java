package com.leetao.lrpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.leetao.lrpc.model.RpcRequest;
import com.leetao.lrpc.model.RpcResponse;
import com.leetao.lrpc.serializer.JdkSerializer;
import com.leetao.lrpc.serializer.Serializer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类
 *
 * @author leetao
 */
public class ServiceProxy implements InvocationHandler {

	/**
	 * 调用代理
	 * @param proxy  代理类
	 * @param method  执行方法
	 * @param args  方法参数
	 * @return  执行结果
	 * @throws Throwable  异常
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//指定序列化器
		final Serializer serializer = new JdkSerializer();
		//构造请求
		RpcRequest rpcRequest = RpcRequest.builder()
				.serviceName(method.getDeclaringClass().getName())
				.methodName(method.getName())
				.parameterTypes(method.getParameterTypes())
				.args(args)
				.build();
		//序列化
		byte[] bodyBytes = serializer.serializer(rpcRequest);
		//发送请求
		//todo 请求的地址被硬编码了，需要使用注册中心
		HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
				.body(bodyBytes)
				.execute();
		byte[] result = httpResponse.bodyBytes();
		RpcResponse rpcResponse = serializer.deserializer(result, RpcResponse.class);

		return rpcResponse.getData();
	}

}
