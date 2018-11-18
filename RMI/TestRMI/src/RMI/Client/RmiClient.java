package RMI.Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import RMI.common.UserHandler;

public class RmiClient {

	public static void main(String[] args) {
		
		try {
			//获取该远程对象的引用
			UserHandler handler = (UserHandler)Naming.lookup("user");
			
			//客户端远程调用服务端的程序
			int count = handler.getUserCount();
			String name = handler.getUserName(1);
			System.out.println("name " + name);
			System.out.println("count " + count);
			System.out.println("user " + handler.getUserByName("imy86263"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
