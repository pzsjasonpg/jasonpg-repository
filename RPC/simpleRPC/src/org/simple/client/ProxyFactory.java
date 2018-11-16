package org.simple.client;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.simple.API.NetModel;
import org.simple.API.SerializeUtils;


public class ProxyFactory {

	private static InvocationHandler handler = new InvocationHandler() {
		//此处调用的获取的是接口HelloService的信息
		public Object invoke(Object proxy, Method method, Object[] args) throws IOException {
			System.out.println("客户端代理程序启动");
			NetModel netModel = new NetModel();
			
			Class<?>[] classes = proxy.getClass().getInterfaces();
			String className = classes[0].getName();
			
			
			netModel.setClassName(className);
			netModel.setArgs(args);
			netModel.setMethod(method.getName());
			
			String [] types = null;
			if (args != null) {
				types = new String [args.length];
				for (int i = 0; i < types.length; i++) {
					types[i] = args[i].getClass().getName();
				}
			}
			
			//打印信息
			System.out.println("classname:"+className);
			System.out.println("method:"+method);
			for (Object string : args) {
				System.out.println("args:"+string);
			}
			for (String string : types) {
				System.out.println("paramaters:"+string);
			}
			
			/**
			 *	客户端代理程序启动
				classname:org.simple.API.HelloService
				method:public abstract java.lang.String org.simple.API.HelloService.sayHello(java.lang.String)
				args:zhangsan
				paramaters:java.lang.String
				sayhello,zhangsan
				客户端代理程序启动
				classname:org.simple.API.HelloService
				method:public abstract org.simple.API.Person org.simple.API.HelloService.getPerson(java.lang.String)
				args:zahngsan
				paramaters:java.lang.String
				PersonPerson [name=zahngsan, age=20]
			 */
			
			netModel.setTypes(types);

            byte[] byteArray = SerializeUtils.serialize(netModel);
            Object send = RPCClient.send(byteArray);
            return send;
        }
    };

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> clazz) {
    	//此处调用newProxyInstance方法会自动运行上面的InvocationHandler接口里的invoke方法。
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), 
                new Class[]{clazz}, handler );

			
		
	}
	
	
}