package org.simple.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.simple.API.HelloService;
import org.simple.API.SerializeUtils;

public class RPCClient {

	//Socket发送消息给服务器，并发序列化服务端返回的数据，返回给方法调用者
	public static Object send(byte[] bs) {
		
		try {
			Socket socket = new Socket("127.0.0.1",9999);
			OutputStream outputStream = socket.getOutputStream();
			//将已经序列化的信息数组发送到服务端
			outputStream.write(bs);
			
			InputStream in = socket.getInputStream();
			byte[] buf = new byte[1024];
			in.read(buf);
			Object formatData = SerializeUtils.deSerialize(buf);
			socket.close();
			return formatData;
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static void main(String[] args) {
		HelloService helloService = ProxyFactory.getInstance(HelloService.class);
		System.out.println("say"+helloService.sayHello("zhangsan"));
		System.out.println("Person"+helloService.getPerson("zahngsan"));
	}
	
	
}
