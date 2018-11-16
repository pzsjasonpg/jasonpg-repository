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
		System.out.println("服务开启");
		try {
			while (true) {
				//接受消息
				Socket socket = serverSocket.accept();
				System.out.println(socket.getInetAddress()+"-connected");
				InputStream in = socket.getInputStream();
				byte[] buf = new byte[1024];
				in.read(buf);
				//执行完调用程序，序列化后得到结果
				byte[] formatData = formatData(buf);
				OutputStream out = socket.getOutputStream();
				//将结果发回客户端
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
			//将收到的byte数组反序列化成NetModel类型，然后通过反射调用HelloServiceImpl实现类的方法
			NetModel netModel = (NetModel)SerializeUtils.deSerialize(bs);
			String className = netModel.getClassName();
			String[] types = netModel.getTypes();
			Object[] args = netModel.getArgs();
			
			
			HashMap<String,String> map = new HashMap<String,String>();
			//将客户端的接口HelloService接口作为HashMap的key,要调用服务端的HelloServiceImpl类作为value
			map.put("org.simple.API.HelloService", "org.simple.server.HelloServiceImpl");
			Class<?> clazz = Class.forName(map.get(className));
			Class<?> [] typeClazzs = null;
			if (types != null) {
				typeClazzs = new Class[types.length];
				for (int  i = 0; i < typeClazzs.length; i++) {
					typeClazzs[i] = Class.forName(types[i]);
				}
			}
			//利用发射调用HelloServiceImpl
			Method method = clazz.getMethod(netModel.getMethod(), typeClazzs);
			Object object = method.invoke(clazz.newInstance(), args);
			//将结果序列化
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

