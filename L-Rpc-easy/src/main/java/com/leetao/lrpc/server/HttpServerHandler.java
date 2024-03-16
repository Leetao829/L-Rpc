package com.leetao.lrpc.server;

import com.leetao.lrpc.model.RpcRequest;
import com.leetao.lrpc.model.RpcResponse;
import com.leetao.lrpc.registry.LocalRegistry;
import com.leetao.lrpc.serializer.JdkSerializer;
import com.leetao.lrpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Http请求处理
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {

	@Override
	public void handle(HttpServerRequest request) {

		//指定序列化器
		final Serializer serializer = new JdkSerializer();
		//记录日志
		System.out.println("Received request :" + request.method()+" " + request.uri());

		//异步处理http请求
		request.bodyHandler(body -> {
			byte[] bytes = body.getBytes();
			RpcRequest rpcRequest = null;
			try {
				rpcRequest = serializer.deserializer(bytes,RpcRequest.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

			//构造响应结果对象
			RpcResponse rpcResponse = new RpcResponse();
			//请求结果为Null，直接返回
			if(rpcRequest == null){
				rpcResponse.setMessage("RpcRequest is null");
				doResponse(request,rpcResponse,serializer);
				return;
			}

			//使用反射调用服务实现类
			String serviceName = rpcRequest.getServiceName();
			try {
				Class<?> implClass = LocalRegistry.get(serviceName);
				Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
				Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
				//封装返回结果
				rpcResponse.setData(result);
				rpcResponse.setDataType(method.getReturnType());
				rpcResponse.setMessage("ok");

			} catch (Exception e) {
				e.printStackTrace();
				rpcResponse.setMessage(e.getMessage());
				rpcResponse.setException(e);
			}
			doResponse(request,rpcResponse,serializer);

		});

	}

	/**
	 * 响应
	 * @param request  请求
	 * @param rpcResponse  rpc响应
	 * @param serializer  序列化器
	 */
	private void doResponse(HttpServerRequest request,RpcResponse rpcResponse,Serializer serializer){
		HttpServerResponse httpServerResponse =
				request.response().putHeader("content-type", "application/json");

		//序列化
		try {
			byte[] bytes = serializer.serializer(rpcResponse);
			httpServerResponse.end(Buffer.buffer(bytes));
		} catch (IOException e) {
			e.printStackTrace();
			httpServerResponse.end(Buffer.buffer());
		}

	}


}
