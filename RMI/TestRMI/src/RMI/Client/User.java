package RMI.Client;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 41L;
	
	String name;
	int id;
	
	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + "]";
	}
	
	

}
