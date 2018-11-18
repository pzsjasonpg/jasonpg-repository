package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import RMI.common.UserHandler;

public class UserHandlerImpl extends UnicastRemoteObject implements UserHandler {
	
	//该构造器必须存在，因为继承了UnicastRemoteObject类，其构造器要抛出RemoteException
	public UserHandlerImpl() throws RemoteException {
		super();
	}

	@Override
	public String getUserName(int id) throws RemoteException {
		return "imy86263";
	}

	@Override
	public int getUserCount() throws RemoteException {
		return 1;
	}

	@Override
	public User getUserByName(String name) throws RemoteException {
		return new User("imy86263",1);
	}
	
}
