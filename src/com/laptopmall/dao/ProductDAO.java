package com.laptopmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptopmall.bean.Product;
import com.laptopmall.page.PageInfo;
import com.laptopmall.page.QueryObject;
import com.laptopmall.util.JdbcUtil;

public class ProductDAO {
	public Product getProductById(Integer id) {
		String sql = "select * from product where id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setBrandId(rs.getInt("brand_id"));
				product.setName(rs.getString("name"));
				product.setImage(rs.getString("image"));
				product.setDetail(rs.getString("detail"));
				product.setStock(rs.getInt("stock"));
				product.setPrice(rs.getBigDecimal("price"));
				return product;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return null;
	}

	public boolean deleteProductById(Integer id) {
		String sql = "delete from product where id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
		return false;
	}

	public boolean insertProduct(Product product) {
		String sql = "insert into product(brand_id,name,image,detail,stock,price) values(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, product.getBrandId());
			ps.setString(2, product.getName());
			ps.setString(3, product.getImage());
			ps.setString(4, product.getDetail());
			ps.setInt(5, product.getStock());
			ps.setBigDecimal(6, product.getPrice());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
		return false;
	}

	public boolean updateProduct(Product newProduct) {
		String sql = "update product set brand_id=?,name=?,image=?,detail=?,stock=?,price=? where id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, newProduct.getBrandId());
			ps.setString(2, newProduct.getName());
			ps.setString(3, newProduct.getImage());
			ps.setString(4, newProduct.getDetail());
			ps.setInt(5, newProduct.getStock());
			ps.setBigDecimal(6, newProduct.getPrice());
			ps.setInt(7, newProduct.getId());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
		return false;
	}

	// TODO listProducts() 分页显示
	public PageInfo<Product> listProducts(QueryObject qo) {
		int totalCount = getProductCount(qo);
		String sql = "select * from product where";
		// brandId为0,表示全选
		if (qo.getBrandId() != 0) {
			sql += " brand_id=? and";
		}
		sql += " (name like ? or detail like ?) limit ?,?";
		List<Product> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			int count = 1;
			if (qo.getBrandId() != 0) {
				ps.setInt(count++, qo.getBrandId());
			}
			ps.setString(count++, "%" + qo.getKeyword() + "%");
			ps.setString(count++, "%" + qo.getKeyword() + "%");
			ps.setInt(count++, (qo.getCurrentPage() - 1) * qo.getPageSize());
			ps.setInt(count++, qo.getPageSize());
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setBrandId(rs.getInt("brand_id"));
				product.setName(rs.getString("name"));
				product.setImage(rs.getString("image"));
				product.setDetail(rs.getString("detail"));
				product.setStock(rs.getInt("stock"));
				product.setPrice(rs.getBigDecimal("price"));
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		PageInfo<Product> pageInfo = new PageInfo<>(list, qo.getCurrentPage(), qo.getPageSize(), totalCount);
		return pageInfo;
	}

	/**
	 * 获取查询结果的行数
	 * 
	 * @param qo
	 * @return
	 */
	private int getProductCount(QueryObject qo) {
		String sql = "select count(1) from product where";
		//
		if (qo.getBrandId() != 0) {
			sql += " brand_id=? and";
		}
		sql += " (name like ? or detail like ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			int count = 1;
			if (qo.getBrandId() != 0) {
				ps.setInt(count++, qo.getBrandId());
			}
			ps.setString(count++, "%" + qo.getKeyword() + "%");
			ps.setString(count++, "%" + qo.getKeyword() + "%");
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return 0;
	}

	public List<Product> listProducts() {
		String sql = "select * from product";
		List<Product> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setBrandId(rs.getInt("brand_id"));
				product.setName(rs.getString("name"));
				product.setImage(rs.getString("image"));
				product.setDetail(rs.getString("detail"));
				product.setStock(rs.getInt("stock"));
				product.setPrice(rs.getBigDecimal("price"));
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return list;
	}

}
