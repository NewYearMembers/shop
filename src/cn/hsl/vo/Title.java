package cn.hsl.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Title implements Serializable {
	private Integer tid;
	private String title;
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Goods_Title [tid=" + tid + ", title=" + title + "]";
	}
	
}
