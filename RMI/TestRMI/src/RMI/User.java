package RMI;

import java.io.Serializable;

//测试RMI的序列化问题，引入User类

public class User implements Serializable {

	private static final long serialVersionUID = 41L;
	
	String name;
	int id;
	
	public User(String name,int id) {
		this.name = name;
		this.id = id;
	}
	
}
