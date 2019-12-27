package com.laptopmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptopmall.bean.User;
import com.laptopmall.util.JdbcUtil;

public class UserDAO {
	
	/**
	 * 	根据登录名获取用户信息
	 * @param loginName
	 * @return
	 */
	public User getUserByLoginName(String loginName) {
		String sql = "select * from user where login_name=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginName);
			rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLoginName(rs.getString("login_name"));
				user.setPassword("");
				user.setRole(rs.getInt("role"));
				user.setRealName(rs.getString("real_name"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}

		return null;
	}
	
	/**
	 * 根据登录名和密码获取用户信息
	 * 
	 * @param loginName 登录名
	 * @param password  密码
	 * @return
	 */
	public User getUserByLoginNameAndPassword(String loginName, String password) {
		String sql = "select * from user where login_name=? and password=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLoginName(rs.getString("login_name"));
				user.setPassword("");
				user.setRole(rs.getInt("role"));
				user.setRealName(rs.getString("real_name"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}

		return null;
	}

	/**
	 * 新增用户信息
	 * 
	 * @param user 用户对象
	 * @return
	 */
	public boolean insertUser(User user) {
		String sql = "insert into user(login_name,password,role,real_name,phone,address) values(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getLoginName());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getRole()==null? 0 : user.getRole());
			ps.setString(4, user.getRealName());
			ps.setString(5, user.getPhone());
			ps.setString(6, user.getAddress());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
		return false;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param newUser 新的用户对象
	 * @return
	 */
	public boolean updateUser(User newUser) {
		String sql = "update user set login_name=?,role=?,real_name=?,phone=?,address=? where id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, newUser.getLoginName());
			ps.setInt(2, newUser.getRole());
			ps.setString(3, newUser.getRealName());
			ps.setString(4, newUser.getPhone());
			ps.setString(5, newUser.getAddress());
			ps.setInt(6, newUser.getId());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
		return false;
	}

	/**
	 * 删除指定id的用户信息
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteUserById(Integer id) {
		String sql = "delete from user where id=?";
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
}
