package cn.hsl.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface IBaseDAO<K,V> {
	public abstract boolean doCreate(V v)throws SQLException;
	public abstract boolean doUpdate(V v)throws SQLException;
	public abstract boolean doRemove(Set<K> ids)throws SQLException;
	public abstract List<V> findAll()throws SQLException;
	public abstract V findById(K k)throws SQLException;
	public abstract List<V> findAll(Integer currentPage,Integer lineSize)throws SQLException;
	public abstract List<V> findAllBySplit(Integer currentPage,Integer lineSize,String column,String keyWord)throws SQLException;
	public abstract Integer getAllCount()throws SQLException;
	public abstract Integer getAllCount(String column,String keyWord)throws SQLException;
}
