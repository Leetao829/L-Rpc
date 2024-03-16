package com.leetao.lrpc.serializer;

import java.io.*;

/**
 * jdk序列化器
 * @author leetao
 */
public class JdkSerializer implements Serializer {

	@Override
	public <T> byte[] serializer(T object) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeObject(object);
		objectOutputStream.close();
		return outputStream.toByteArray();
	}

	@Override
	public <T> T deserializer(byte[] bytes, Class<T> type) throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		ObjectInputStream objectInputStream = new ObjectInputStream(in);
		try {
			return (T)objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}finally {
			objectInputStream.close();
		}
	}

}
