package org.simple.API;

import java.io.Serializable;
import java.util.Arrays;

//��������ͨ���࣬ͨ�����л�����,���ͻ��˵��ýӿڡ��������������������ͷ�װ��
//Ȼ�����˷����л�,��ͨ������,��ȡ��Ӧʵ����ķ�����

public class NetModel implements Serializable {

	private String className; //�ӿ���
	private String method; //������
	private Object[] args; //����
	private String[] types; //��������
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	@Override
	public String toString() {
		return "NetModel [className=" + className + ", method=" + method + ", args=" + Arrays.toString(args)
				+ ", types=" + Arrays.toString(types) + "]";
	}
	
	
	
}
