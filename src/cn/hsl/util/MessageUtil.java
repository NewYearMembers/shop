package cn.hsl.util;

import java.util.ResourceBundle;

public class MessageUtil {
	private ResourceBundle rb = null;
	public MessageUtil(String baseName){
		this.rb = ResourceBundle.getBundle(baseName);
	}
	public String getText(String key){
		return this.rb.getString(key);
	}
}
