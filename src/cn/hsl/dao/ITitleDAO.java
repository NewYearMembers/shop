package cn.hsl.dao;

import java.sql.SQLException;
import java.util.Set;

import cn.hsl.vo.Title;

public interface ITitleDAO extends IBaseDAO<Integer,Title>{
	/**
	 * 在增加商品的同时进行商品与商品标签一对多的信息设置
	 * @param gid	单个商品
	 * @param tids	对应的商品标签
	 * @return 设置成功返回true，反之返回false
	 * @throws SQLException
	 */
	public abstract boolean doCreateBygidtid(Integer gid,Set<Integer> tids)throws SQLException;
	
	/**
	 * 在修改时需要先进行关系表的删除
	 * @param gid
	 * @return
	 * @throws SQLException
	 */
	public abstract boolean doRemovebygt(Integer gid)throws SQLException;
}
