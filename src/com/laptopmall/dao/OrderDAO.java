package com.laptopmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.laptopmall.bean.Order;
import com.laptopmall.page.PageInfo;
import com.laptopmall.util.JdbcUtil;

public class OrderDAO {

	/**
	 * 获取某一用户全部订单信息 分页显示
	 * 
	 * @param userId
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Order> listOrders(Integer userId, Integer currentPage, Integer pageSize) {
		String sql = "select * from `order` where user_id=? order by pay_time desc limit ?,?";
		List<Order> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, (currentPage - 1) * pageSize);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setUserId(rs.getInt("user_id"));
				order.setPayment(rs.getBigDecimal("payment"));
				order.setPayTime(new Date(rs.getTimestamp("pay_time").getTime()));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return new PageInfo<>(list, currentPage, pageSize, getOrderCount(userId));
	}

	// 计算当前用户有多少订单记录
	private int getOrderCount(Integer userId) {
		String sql = "select count(1) from `order` where user_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return 0;
	}

	/**
	 * 获取系统全部订单信息 分页显示
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Order> listOrders(Integer currentPage, Integer pageSize) {
		String sql = "select * from `order` order by pay_time desc limit ?,?";
		List<Order> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (currentPage - 1) * pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setUserId(rs.getInt("user_id"));
				order.setPayment(rs.getBigDecimal("payment"));
				order.setPayTime(new Date(rs.getTimestamp("pay_time").getTime()));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return new PageInfo<>(list, currentPage, pageSize, getOrderCount());
	}

	// 计算当前系统内有多少订单记录
	private int getOrderCount() {
		String sql = "select count(1) from `order`";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return 0;
	}

	/**
	 * 根据订单号获取某一订单信息
	 * 
	 * @param id
	 * @return
	 */
	public Order getOrderById(Integer id) {
		String sql = "select * from `order` where id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setUserId(rs.getInt("user_id"));
				order.setPayment(rs.getBigDecimal("payment"));
				order.setPayTime(new Date(rs.getTimestamp("pay_time").getTime()));
				return order;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return null;
	}

	/**
	 * 新增订单
	 * 
	 * @param order
	 * @return
	 */
	public boolean insertOder(Order order) {
		String sql = "insert into `order`(user_id,payment,pay_time) values(?,?,now())";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			// 自动返回主键
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, order.getUserId());
			ps.setBigDecimal(2, order.getPayment());
//			ps.setDate(3, new Date(order.getPayTime().getTime()));
			boolean result = ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			order.setId(rs.getInt(1));
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
		return false;
	}

	/**
	 * 更新Order信息
	 * 
	 * @param newOrder
	 * @return
	 */
	public boolean updateOrder(Order newOrder) {
		String sql = "update `order` set user_id=?,payment=?,pay_time=now() where id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, newOrder.getUserId());
			ps.setBigDecimal(2, newOrder.getPayment());
			ps.setInt(3, newOrder.getId());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
		return false;
	}

	// 查看某个用户的全部订单信息 不分页
	public List<Order> listOrders(Integer userId) {
		String sql = "select * from `order` where user_id=? order by pay_time desc";
		List<Order> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setUserId(rs.getInt("user_id"));
				order.setPayment(rs.getBigDecimal("payment"));
				order.setPayTime(new Date(rs.getTimestamp("pay_time").getTime()));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return list;
	}

	// 查看全部订单信息 不分页
	public List<Order> listOrders() {
		String sql = "select * from `order` order by pay_time desc";
		List<Order> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setUserId(rs.getInt("user_id"));
				order.setPayment(rs.getBigDecimal("payment"));
				order.setPayTime(new Date(rs.getTimestamp("pay_time").getTime()));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return list;
	}
}
