package org.simple.API;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtils {

	//���л�
	public static byte[] serialize(Object object) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream outputStream = new ObjectOutputStream(os);
		outputStream.writeObject(object);
		outputStream.flush();
		//��ȡ����
		byte[] byteArray = os.toByteArray();
		outputStream.close();
		os.close();
		return byteArray;
	}
	
	//�����л�
	public static Object deSerialize(byte[] buf) throws IOException, ClassNotFoundException {
		ByteArrayInputStream is = new ByteArrayInputStream(buf);
		ObjectInputStream inputStream = new ObjectInputStream(is);
		Object object = inputStream.readObject();
		inputStream.close();
		is.close();
		return object;
	}
	
}
