package com.laptopmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptopmall.bean.Brand;
import com.laptopmall.util.JdbcUtil;

public class BrandDAO {
	/**
	 * 列出所有品牌
	 * @return 
	 */
	public List<Brand> listBrands(){
		String sql = "select * from brand";
		List<Brand> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Brand brand = new Brand();
				brand.setId(rs.getInt("id"));
				brand.setName(rs.getString("name"));
				list.add(brand);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return list;
	}
	
	public boolean insertBrand(Brand brand) {
		String sql = "insert into brand(name) values(?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, brand.getName());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
		return false;
	}
	
	public Brand getBrandByName(String name) {
		String sql = "select * from brand where `name`=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next()) {
				Brand brand = new Brand();
				brand.setId(rs.getInt("id"));
				brand.setName(rs.getString("name"));
				return brand;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return null;
	}
	
}
