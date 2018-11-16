package org.simple.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import org.simple.API.NetModel;
import org.simple.API.SerializeUtils;

public class RPCServer {

	public static void main(String[] args) {
		try {
			openServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void openServer() throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("������");
		try {
			while (true) {
				//������Ϣ
				Socket socket = serverSocket.accept();
				System.out.println(socket.getInetAddress()+"-connected");
				InputStream in = socket.getInputStream();
				byte[] buf = new byte[1024];
				in.read(buf);
				//ִ������ó������л���õ����
				byte[] formatData = formatData(buf);
				OutputStream out = socket.getOutputStream();
				//��������ؿͻ���
				out.write(formatData);
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			serverSocket.close();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static byte[] formatData(byte[] bs) {
		try {
			//���յ���byte���鷴���л���NetModel���ͣ�Ȼ��ͨ���������HelloServiceImplʵ����ķ���
			NetModel netModel = (NetModel)SerializeUtils.deSerialize(bs);
			String className = netModel.getClassName();
			String[] types = netModel.getTypes();
			Object[] args = netModel.getArgs();
			
			
			HashMap<String,String> map = new HashMap<String,String>();
			//���ͻ��˵Ľӿ�HelloService�ӿ���ΪHashMap��key,Ҫ���÷���˵�HelloServiceImpl����Ϊvalue
			map.put("org.simple.API.HelloService", "org.simple.server.HelloServiceImpl");
			Class<?> clazz = Class.forName(map.get(className));
			Class<?> [] typeClazzs = null;
			if (types != null) {
				typeClazzs = new Class[types.length];
				for (int  i = 0; i < typeClazzs.length; i++) {
					typeClazzs[i] = Class.forName(types[i]);
				}
			}
			//���÷������HelloServiceImpl
			Method method = clazz.getMethod(netModel.getMethod(), typeClazzs);
			Object object = method.invoke(clazz.newInstance(), args);
			//��������л�
			byte[] byteArray = SerializeUtils.serialize(object);
			return byteArray;
			
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

