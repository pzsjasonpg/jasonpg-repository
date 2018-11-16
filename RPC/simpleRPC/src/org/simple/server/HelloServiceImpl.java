package org.simple.server;

import org.simple.API.HelloService;
import org.simple.API.Person;

//简单实现类
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		return "hello,"+name;
	}

	@Override
	public Person getPerson(String name) {
		Person person = new Person();
		person.setName(name);
		person.setAge(20);
		return person;
	}

	
}
