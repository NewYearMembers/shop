package cn.hsl.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.hsl.database.DataBaseDriver;

public class ServiceProxy implements InvocationHandler {
	private Object target;
	public Object bind(Object target){
		this.target = target;
		return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object obj = null;
		if(method.getName().startsWith("insert") || method.getName().startsWith("update") 
				|| method.getName().startsWith("delete")){
			DataBaseDriver.getConnection().setAutoCommit(false);
			try {
				obj = method.invoke(this.target, args);
				DataBaseDriver.getConnection().commit();
			} catch (Exception e) {
				DataBaseDriver.getConnection().rollback();
			}finally{
				DataBaseDriver.close();
			}
		}else{
			try {
				obj = method.invoke(this.target, args);
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				DataBaseDriver.close();
			}
		}
		return obj;
	}

}
