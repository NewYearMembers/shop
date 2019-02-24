package cn.hsl.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.hsl.dao.IGoodsDAO;
import cn.hsl.dao.IItemDAO;
import cn.hsl.dao.ITitleDAO;
import cn.hsl.factory.Factory;
import cn.hsl.service.IGoodsService;
import cn.hsl.vo.Goods;

public class GoodsServiceImpl extends AbstractServiceImpl implements IGoodsService {

	@Override
	public Map<String, Object> addGoodsPre() throws Exception {
		Map<String,Object> all = new HashMap<>();
		IItemDAO iid = Factory.getDAOInstance("dao.item");
		ITitleDAO itd = Factory.getDAOInstance("dao.title");
		all.put("allItems",iid.findAll());
		all.put("allTitles",itd.findAll());
		return all;
	}

	@Override
	public boolean insert(Goods vo, Set<Integer> tids) throws Exception {
		IGoodsDAO igd = Factory.getDAOInstance("dao.goods");
		if(vo.getIid() == null || vo.getIid().equals(0) 
				|| vo.getTitle() == null || "".equals(vo.getTitle()) 
				|| vo.getPrice() <= 0 || vo.getPhoto() == null 
				|| "".equals(vo.getPhoto())){
			return false;
		}
		if(igd.findByTitle(vo.getTitle()) == null){
			if(igd.doCreate(vo)){
				int gid = igd.findGid();
				ITitleDAO itd = Factory.getDAOInstance("dao.title");
				return itd.doCreateBygidtid(gid, tids);
			}
		}
		return false;
	}

	@Override
	public Map<String, Object> list() throws Exception {
		Map<String,Object> all = new HashMap<>();
		IGoodsDAO igd = Factory.getDAOInstance("dao.goods");
		IItemDAO iid = Factory.getDAOInstance("dao.item");
		all.put("allGoods", igd.findAll());
		all.put("allItems", iid.findByMap());
		return all;
	}

	@Override
	public Map<String, Object> editPre(Integer gid) throws Exception {
		Map<String,Object> all = new HashMap<>();
		IGoodsDAO igd = Factory.getDAOInstance("dao.goods");
		IItemDAO iid = Factory.getDAOInstance("dao.item");
		ITitleDAO itd = Factory.getDAOInstance("dao.title");
		all.put("items",iid.findByGid(gid));
		all.put("titles",igd.findByTitle(gid));
		all.put("goods",igd.findById(gid));
		all.put("allItems",iid.findAll());
		all.put("allTitles",itd.findAll());
		return all;
	}

	@Override
	public boolean update(Goods vo,Set<Integer> tids) throws Exception {
		IGoodsDAO igd = Factory.getDAOInstance("dao.goods");
		if(vo.getIid() == null || vo.getIid().equals(0) 
				|| vo.getTitle() == null || "".equals(vo.getTitle()) 
				|| vo.getPrice() <= 0 ){
			return false;
		}
		System.out.println(123);
		Goods g = igd.findByTitle(vo.getTitle());
		if(g == null||g.getTitle().equals(vo.getTitle())){
			if(igd.doUpdate(vo)){
				ITitleDAO itd = Factory.getDAOInstance("dao.title");
				itd.doRemovebygt(vo.getGid());
				return itd.doCreateBygidtid(vo.getGid(), tids);
			}
		}
		return false;
	}

}
