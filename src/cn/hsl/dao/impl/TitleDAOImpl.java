package cn.hsl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.hsl.dao.ITitleDAO;
import cn.hsl.vo.Title;

public class TitleDAOImpl extends AbstractDAOImpl implements ITitleDAO {
	@Override
	public boolean doRemovebygt(Integer gid) throws SQLException {
		String sql = "delete from goods_goods_title where gid=?";
		super.ps = super.conn.prepareStatement(sql);
		super.ps.setInt(1,gid);
		return super.ps.executeUpdate() > 0;
	}
	@Override
	public boolean doCreateBygidtid(Integer gid, Set<Integer> tids) throws SQLException {
		String sql = "insert into goods_goods_title(tid,gid) values(?,?)";
		Iterator<Integer> it = tids.iterator();
		super.ps = super.conn.prepareStatement(sql);
		while(it.hasNext()){
			super.ps.setInt(1, it.next());
			super.ps.setInt(2, gid);
			super.ps.addBatch();
		}
		int [] len = super.ps.executeBatch();
		for(int i:len){
			return i > 0;
		}
		return false;
	}
	@Override
	public boolean doCreate(Title v) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Title v) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Title> findAll() throws SQLException {
		List<Title> all = new ArrayList<>();
		String sql = "select tid,title from goods_title";
		super.ps = super.conn.prepareStatement(sql);
		ResultSet rs = super.ps.executeQuery();
		while(rs.next()){
			Title vo = new Title();
			vo.setTid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Title findById(Integer k) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Title> findAll(Integer currentPage, Integer lineSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Title> findAllBySplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
