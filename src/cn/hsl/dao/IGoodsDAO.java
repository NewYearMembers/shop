package cn.hsl.dao;

import java.sql.SQLException;
import java.util.Set;

import cn.hsl.vo.Goods;
import cn.hsl.vo.Title;

public interface IGoodsDAO extends IBaseDAO<Integer,Goods>{
	/**
	 * 此方法用于判断增加的商品名称是否相同
	 * @param title 商品名称
	 * @return	返回为空表示不相同，有对象表示有相同名称的商品
	 * @throws SQLException
	 */
	public abstract Goods findByTitle(String title)throws SQLException;
	
	/**
	 * 用于取得增加商品后增长的gid
	 * @return
	 * @throws SQLException
	 */
	public abstract int findGid()throws SQLException;
	
	/**
	 * 根据gid查找对应的Title信息
	 * @param gid
	 * @return
	 * @throws SQLException
	 */
	public abstract Set<Integer> findByTitle(Integer gid)throws SQLException;
}
