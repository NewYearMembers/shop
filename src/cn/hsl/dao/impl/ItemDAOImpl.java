package cn.hsl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.hsl.dao.IItemDAO;
import cn.hsl.vo.Item;

public class ItemDAOImpl extends AbstractDAOImpl implements IItemDAO {

	@Override
	public boolean doCreate(Item v) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Item v) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Item> findAll() throws SQLException {
		List<Item> all = new ArrayList<>();
		String sql = "select iid,title from goods_item";
		super.ps = super.conn.prepareStatement(sql);
		ResultSet rs = super.ps.executeQuery();
		while(rs.next()){
			Item vo = new Item();
			vo.setIid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Item findById(Integer k) throws SQLException {
		Item vo = null;
		String sql = "select iid,title from goods_item where iid=?";
		super.ps = super.conn.prepareStatement(sql);
		super.ps.setInt(1,k);
		ResultSet rs = super.ps.executeQuery();
		if(rs.next()){
			vo = new Item();
			vo.setIid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
		}
		return vo;
	}

	@Override
	public List<Item> findAll(Integer currentPage, Integer lineSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> findAllBySplit(Integer currentPage, Integer lineSize, String column, String keyWord)
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

	@Override
	public Map<Integer,String> findByMap() throws SQLException {
		Map<Integer,String> all = new HashMap<>();
		String sql = "select iid,title from goods_item";
		super.ps = super.conn.prepareStatement(sql);
		ResultSet rs = super.ps.executeQuery();
		while(rs.next()){
			all.put(rs.getInt(1),rs.getString(2));
		}
		return all;
	}

	@Override
	public Item findByGid(Integer gid) throws SQLException {
		Item vo = null;
		String sql = "select iid,title from goods_item where iid=("
				+ "select iid from goods where gid=?)";
		super.ps = super.conn.prepareStatement(sql);
		super.ps.setInt(1,gid);
		ResultSet rs = super.ps.executeQuery();
		if(rs.next()){
			vo = new Item();
			vo.setIid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
		}
		return vo;
	}


	
}
