package com.furniture.dao.impl;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.furniture.domain.Furniture;
import com.furniture.exception.FurnitureDaoException;
import com.furniture.utils.JdbcUtils;
public class FurnitureDaoImpl {
	public void AddFurniture(Furniture furniture) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into furniture(fname,fsummary,fnum,fprice,fpic) values(?,?,?,?,?)";
			Object[] params = new Object[] {furniture.getFname(),furniture.getFsummary(),furniture.getFnum(),furniture.getFprice(),furniture.getFpic()};
			runner.update(sql, params);
		} catch (Exception e) {
			throw new FurnitureDaoException(e);
		}
	}

	public void DeleteFurniture(Furniture furniture) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "delete from furniture where fid=?";
			runner.update(sql, furniture.getFid());
		} catch (Exception e) {
			throw new FurnitureDaoException(e);
		}
	}

	public void UpdateFurniture(Furniture furniture) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update furniture set fname=?,fsummary=?,fnum=?,fprice=?,fpic=? where fid=?";
			Object[] params = new Object[] {furniture.getFname(),furniture.getFsummary(),furniture.getFnum(),furniture.getFprice(),furniture.getFpic(),furniture.getFid()};
			runner.update(sql, params);
		} catch (Exception e) {
			throw new FurnitureDaoException(e);
		}
	}

	public List<Furniture> getFurniture(Furniture furniture) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from furniture where fname=?";
			List<Furniture> furniture1 = runner.query(sql, new BeanListHandler<>(Furniture.class),furniture.getFname());
			return furniture1;
		} catch (Exception e) {
			throw new FurnitureDaoException(e);
		}
	}
	
	public List<Furniture> getDetail(String fid) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from furniture where fid=?";
			List<Furniture> furniture1 = runner.query(sql, new BeanListHandler<>(Furniture.class),fid);
			return furniture1;
		} catch (Exception e) {
			throw new FurnitureDaoException(e);
		}
	}
	
	public List<Furniture> getAllFur() {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from furniture";
			List<Furniture> furniture1 = runner.query(sql, new BeanListHandler<>(Furniture.class));
			return furniture1;
		} catch (Exception e) {
			throw new FurnitureDaoException(e);
		}
	}
	
	/**
	 * 分页个数
	 * @param startindex 起始页
	 * @param pagesize 页面大小
	 * @return list 家具分页
	 */
	public List<Furniture> getPageData(int startindex,int pagesize){
		
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from furniture limit ?,?";
			Object[] params = new Object[]{startindex,pagesize};
			List<Furniture> furniture1 = runner.query(sql, new BeanListHandler<>(Furniture.class),params);
			return furniture1;
		} catch (Exception e) {
			throw new FurnitureDaoException(e);
		}
	}
	
	/**
	 * 得到数据条总数量
	 * @return 总数量
	 */
	public int getTotalrecord(){

		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql="select count(*) from furniture";
			Object[] objects = runner.query(sql, new ArrayHandler());
			
			
			return Integer.parseInt(objects[0].toString());
		} catch (Exception e) {
			throw new FurnitureDaoException(e);
		}
		
	}
}
