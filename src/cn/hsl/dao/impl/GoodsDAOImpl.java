package cn.hsl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.hsl.dao.IGoodsDAO;
import cn.hsl.vo.Goods;

public class GoodsDAOImpl extends AbstractDAOImpl implements IGoodsDAO {
	@Override
	public Goods findByTitle(String title) throws SQLException {
		String sql = "select gid,iid,title,price,photo from goods where title=?";
		super.ps = super.conn.prepareStatement(sql);
		super.ps.setString(1, title);
		ResultSet re = super.ps.executeQuery();
		Goods g = null;
		if(re.next()){
			g = new Goods();
			g.setGid(re.getInt(1));
			g.setIid(re.getInt(2));
			g.setTitle(re.getString(3));
			g.setPrice(re.getDouble(4));
			g.setPhoto(re.getString(5));
		}
		return g;
	}
	@Override
	public boolean doCreate(Goods v) throws SQLException {
		String sql = "insert into goods(iid,title,price,photo) values(?,?,?,?)";
		super.ps = super.conn.prepareStatement(sql);
		super.ps.setInt(1, v.getIid());
		super.ps.setString(2, v.getTitle());
		super.ps.setDouble(3, v.getPrice());
		super.ps.setString(4, v.getPhoto());
		return super.ps.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Goods v) throws SQLException {
		String sql = "update goods set title=?,price=?,iid=? where gid=?";
		super.ps = super.conn.prepareStatement(sql);
		super.ps.setString(1, v.getTitle());
		super.ps.setDouble(2, v.getPrice());
		super.ps.setInt(3, v.getIid());
		super.ps.setInt(4, v.getGid());
		return super.ps.executeUpdate() > 0;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Goods> findAll() throws SQLException {
		List<Goods> all = new ArrayList<>();
		String sql = "select gid,iid,title,price,photo from goods";
		super.ps = super.conn.prepareStatement(sql);
		ResultSet rs = super.ps.executeQuery();
		while(rs.next()){
			Goods vo = new Goods();
			vo.setGid(rs.getInt(1));
			vo.setIid(rs.getInt(2));
			vo.setTitle(rs.getString(3));
			vo.setPrice(rs.getDouble(4));
			vo.setPhoto(rs.getString(5));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Goods findById(Integer k) throws SQLException {
		String sql = "select gid,iid,title,price,photo from goods where gid=?";
		super.ps = super.conn.prepareStatement(sql);
		super.ps.setInt(1, k);
		ResultSet re = super.ps.executeQuery();
		Goods g = null;
		if(re.next()){
			g = new Goods();
			g.setGid(re.getInt(1));
			g.setIid(re.getInt(2));
			g.setTitle(re.getString(3));
			g.setPrice(re.getDouble(4));
			g.setPhoto(re.getString(5));
		}
		return g;
	}

	@Override
	public List<Goods> findAll(Integer currentPage, Integer lineSize) throws SQLException {
		List<Goods> all = new ArrayList<>();
 		String sql = "select gid,iid,title,price,photo from goods limit ?,?";
		super.ps = super.conn.prepareStatement(sql);
		super.ps.setInt(1, (currentPage-1) * lineSize);
		super.ps.setInt(2, currentPage * lineSize);
		ResultSet rs = super.ps.executeQuery();
		while(rs.next()){
			Goods vo = new Goods();
			vo.setGid(rs.getInt(1));
			vo.setIid(rs.getInt(2));
			vo.setTitle(rs.getString(3));
			vo.setPrice(rs.getDouble(4));
			vo.setPhoto(rs.getString(5));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Goods> findAllBySplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException {
		List<Goods> all = new ArrayList<>();
 		String sql = "select gid,iid,title,price,photo from goods where "+ column +" like ? limit ?,?";
		super.ps = super.conn.prepareStatement(sql);
		super.ps.setString(1, "%"+keyWord+"%");
		super.ps.setInt(2, (currentPage-1) * lineSize);
		super.ps.setInt(3, currentPage * lineSize);
		ResultSet rs = super.ps.executeQuery();
		while(rs.next()){
			Goods vo = new Goods();
			vo.setGid(rs.getInt(1));
			vo.setIid(rs.getInt(2));
			vo.setTitle(rs.getString(3));
			vo.setPrice(rs.getDouble(4));
			vo.setPhoto(rs.getString(5));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Integer getAllCount() throws SQLException {
		String sql = "select count(*) from goods";
		super.ps = super.conn.prepareStatement(sql);
		ResultSet rs = super.ps.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws SQLException {
		String sql = "select count(*) from goods where "+column+" like ?";
		super.ps = super.conn.prepareStatement(sql);
		super.ps.setString(1, "%"+keyWord+"%");
		ResultSet rs = super.ps.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	@Override
	public int findGid() throws SQLException {
		String sql = "select count(*) from goods";
		super.ps = super.conn.prepareStatement(sql);
		ResultSet rs = super.ps.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	@Override
	public Set<Integer> findByTitle(Integer gid) throws SQLException {
		Set<Integer> all = new HashSet<>();
		String sql = "select tid from goods_goods_title where gid=?";
		super.ps = super.conn.prepareStatement(sql);
		super.ps.setInt(1,gid);
		ResultSet re = super.ps.executeQuery();
		if(re.next()){
			all.add(re.getInt(1));
		}
		return all;
	}
	
}
