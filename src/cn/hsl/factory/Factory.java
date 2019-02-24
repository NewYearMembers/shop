package cn.hsl.factory;

import cn.hsl.proxy.ServiceProxy;
import cn.hsl.util.MessageUtil;

public class Factory {
	private static final MessageUtil dmu = new MessageUtil("cn.hsl.message.dao");
	private static final MessageUtil smu = new MessageUtil("cn.hsl.message.service");
	@SuppressWarnings("unchecked")
	public static <T> T getDAOInstance(String keyName){
		String className = dmu.getText(keyName);
		T t = null;
		try {
			t = (T)Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	@SuppressWarnings("unchecked")
	public static <T> T getServiceInstance(String keyName){
		String className = smu.getText(keyName);
		T t = null;
		try {
			t = (T)new ServiceProxy().bind(Class.forName(className).newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}
