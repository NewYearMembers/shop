package cn.hsl.dao;

import java.sql.SQLException;
import java.util.Map;

import cn.hsl.vo.Item;

public interface IItemDAO extends IBaseDAO<Integer,Item>{
	/**
	 * 以Map集合方式返回分类信息
	 * @param gid
	 * @return key=iid,value=title
	 * @throws SQLException
	 */
	public abstract Map<Integer,String> findByMap()throws SQLException;
	
	/**
	 * 根据gid查询出对应的信息
	 * @param gid
	 * @return
	 * @throws SQLException
	 */
	public abstract Item findByGid(Integer gid)throws SQLException;
	
}
