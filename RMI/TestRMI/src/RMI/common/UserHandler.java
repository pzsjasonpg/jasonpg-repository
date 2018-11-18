package RMI.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import RMI.User;

/**
 * �ӽӿڵ�ÿ�����������������׳�java.rmi.RemoteException�쳣�����쳣��ʹ��RMIʱ�����׳��Ĵ�����쳣�ĸ��ࡣ
 * �ӽӿڵ�ʵ����Ӧ��ֱ�ӻ��߼�Ӽ̳�java.rmi.server.UnicastRemoteObject�࣬
 * �����ṩ�˺ܶ�֧��RMI�ķ�����������˵����Щ��������ͨ��JRMPЭ�鵼��һ��Զ�̶�������ã�
 * ��ͨ����̬������һ�����Ժ�Զ�̶��󽻻���Stub���󡣾����ʵ�ֿ����µ����ӡ�
 *
 */

public interface UserHandler extends Remote {
	String getUserName(int id) throws RemoteException;
	int getUserCount() throws RemoteException;
	User getUserByName(String name) throws RemoteException;
}
