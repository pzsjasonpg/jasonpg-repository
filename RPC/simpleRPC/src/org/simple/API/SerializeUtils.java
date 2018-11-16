package org.simple.API;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtils {

	//序列化
	public static byte[] serialize(Object object) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream outputStream = new ObjectOutputStream(os);
		outputStream.writeObject(object);
		outputStream.flush();
		//获取数据
		byte[] byteArray = os.toByteArray();
		outputStream.close();
		os.close();
		return byteArray;
	}
	
	//反序列化
	public static Object deSerialize(byte[] buf) throws IOException, ClassNotFoundException {
		ByteArrayInputStream is = new ByteArrayInputStream(buf);
		ObjectInputStream inputStream = new ObjectInputStream(is);
		Object object = inputStream.readObject();
		inputStream.close();
		is.close();
		return object;
	}
	
}
