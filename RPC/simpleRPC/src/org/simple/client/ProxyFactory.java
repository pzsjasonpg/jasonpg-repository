package org.simple.client;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.simple.API.NetModel;
import org.simple.API.SerializeUtils;


public class ProxyFactory {

	private static InvocationHandler handler = new InvocationHandler() {
		//�˴����õĻ�ȡ���ǽӿ�HelloService����Ϣ
		public Object invoke(Object proxy, Method method, Object[] args) throws IOException {
			System.out.println("�ͻ��˴����������");
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
			
			//��ӡ��Ϣ
			System.out.println("classname:"+className);
			System.out.println("method:"+method);
			for (Object string : args) {
				System.out.println("args:"+string);
			}
			for (String string : types) {
				System.out.println("paramaters:"+string);
			}
			
			/**
			 *	�ͻ��˴����������
				classname:org.simple.API.HelloService
				method:public abstract java.lang.String org.simple.API.HelloService.sayHello(java.lang.String)
				args:zhangsan
				paramaters:java.lang.String
				sayhello,zhangsan
				�ͻ��˴����������
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
    	//�˴�����newProxyInstance�������Զ����������InvocationHandler�ӿ����invoke������
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), 
                new Class[]{clazz}, handler );

			
		
	}
	
	
}