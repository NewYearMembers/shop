package cn.hsl.service;

import java.util.Map;
import java.util.Set;

import cn.hsl.vo.Goods;

public interface IGoodsService {
	/**
	 * 进行商品添加前的信息查找
	 * @return
	 * 1.key=allTitles ,value=所有商品标题信息<br>
	 * 2.key=allItems ,value=所有商品类别信息
	 * @throws Exception
	 */
	public abstract Map<String,Object> addGoodsPre()throws Exception;
	
	/**
	 * 进行商品添加：1.进行商品的添加操作。2.查找增长后的gid。3.进行多对多关系的设置
	 * @param vo 
	 * @param tids
	 * @return
	 * @throws Exception
	 */
	public abstract boolean insert(Goods vo,Set<Integer> tids)throws Exception;
	
	/**
	 * 进行商品分页列表操作：1.判断是否需要模糊查询。2.保存对应数据
	 * @param cp
	 * @param ls
	 * @param col
	 * @param kw
	 * @return key = "allGoods",key=商品集合。key="goodsCount",value="商品数量"
	 * @throws Exception
	 */
	public abstract Map<String,Object> list()throws Exception;
	
	/**
	 * 进行商品的修改操作
	 * @param vo
	 * @return
	 * 1.key="Item",value=根据gid查找到对应Item的方法
	 * 2.key="Title",value=根据gid查找到所有对应的TItle的方法
	 * 3.key="goods",value=根据gid查找对应的goods的方法
	 * @throws Exception
	 */
	public abstract Map<String,Object> editPre(Integer gid)throws Exception;
	
	public abstract boolean update(Goods vo,Set<Integer> tids)throws Exception;
}
