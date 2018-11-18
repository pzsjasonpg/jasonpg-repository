package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import RMI.common.UserHandler;

public class Entry {
	
	public static void main(String[] args) {
		
		UserHandler userHandler = null;
		Registry registry = null;
		try {
			//获取注册表
			registry = LocateRegistry.createRegistry(1099);
			
			userHandler = new UserHandlerImpl();
			//通过一个名称映射到该远程对象的引用，客户端通过该名称获取该远程对象的引用。
			Naming.rebind("user", userHandler);
			System.out.println("rmi server is ready ...");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		
	}

}
