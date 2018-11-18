package RMI.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import RMI.User;

/**
 * 子接口的每个方法都必须声明抛出java.rmi.RemoteException异常，该异常是使用RMI时可能抛出的大多数异常的父类。
 * 子接口的实现类应该直接或者间接继承java.rmi.server.UnicastRemoteObject类，
 * 该类提供了很多支持RMI的方法，具体来说，这些方法可以通过JRMP协议导出一个远程对象的引用，
 * 并通过动态代理构建一个可以和远程对象交互的Stub对象。具体的实现看如下的例子。
 *
 */

public interface UserHandler extends Remote {
	String getUserName(int id) throws RemoteException;
	int getUserCount() throws RemoteException;
	User getUserByName(String name) throws RemoteException;
}
