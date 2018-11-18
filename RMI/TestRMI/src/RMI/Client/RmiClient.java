package RMI.Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import RMI.common.UserHandler;

public class RmiClient {

	public static void main(String[] args) {
		
		try {
			//��ȡ��Զ�̶��������
			UserHandler handler = (UserHandler)Naming.lookup("user");
			
			//�ͻ���Զ�̵��÷���˵ĳ���
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
