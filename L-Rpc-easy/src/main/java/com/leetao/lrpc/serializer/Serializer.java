package com.leetao.lrpc.serializer;

import java.io.IOException;

/**
 * 序列化器接口
 *
 * @author leetao
 */
public interface Serializer {

	/**
	 * 将对象进行序列化
	 * @param object 对象
	 * @param <T>
	 * @return
	 * @throws IOException
	 */
	<T> byte[] serializer(T object) throws IOException;

	/**
	 * 反序列化
	 * @param bytes 字节数组
	 * @param type
	 * @param <T>
	 * @return
	 * @throws IOException
	 */
	<T> T deserializer(byte[] bytes,Class<T> type) throws IOException;

}
