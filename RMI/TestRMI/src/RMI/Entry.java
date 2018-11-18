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
			//��ȡע���
			registry = LocateRegistry.createRegistry(1099);
			
			userHandler = new UserHandlerImpl();
			//ͨ��һ������ӳ�䵽��Զ�̶�������ã��ͻ���ͨ�������ƻ�ȡ��Զ�̶�������á�
			Naming.rebind("user", userHandler);
			System.out.println("rmi server is ready ...");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		
	}

}
