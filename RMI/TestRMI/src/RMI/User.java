package RMI;

import java.io.Serializable;

//����RMI�����л����⣬����User��

public class User implements Serializable {

	private static final long serialVersionUID = 41L;
	
	String name;
	int id;
	
	public User(String name,int id) {
		this.name = name;
		this.id = id;
	}
	
}
