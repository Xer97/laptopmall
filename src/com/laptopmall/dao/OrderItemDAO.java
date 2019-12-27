package com.laptopmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptopmall.bean.OrderItem;
import com.laptopmall.util.JdbcUtil;

public class OrderItemDAO {
	/**
	 * 	查询出当前用户的某一订单下的所有订单项
	 * @param userId 用户id
	 * @param orderId 订单号
	 * @return
	 */
	public List<OrderItem> ListOrderItemsByUserIdAndOrderId(Integer userId, Integer orderId) {
		String sql = "select * from order_item where user_id=? and order_id=?";
		List<OrderItem> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, orderId);
			rs = ps.executeQuery();
			while (rs.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("id"));
				orderItem.setOrderId(rs.getInt("order_id"));
				orderItem.setUser_id(rs.getInt("user_id"));
				orderItem.setProductName(rs.getString("product_name"));
				orderItem.setProductImage(rs.getString("product_image"));
				orderItem.setProductPrice(rs.getBigDecimal("product_price"));
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setTotalPrice(rs.getBigDecimal("total_price"));
				list.add(orderItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return list;
	}

	/**
	 * 插入订单项
	 * 
	 * @param orderItem
	 * @return
	 */
	public boolean insertOrderItem(OrderItem orderItem) {
		String sql = "insert into "
				+ "order_item(order_id,user_id,product_name,product_image,product_price,quantity,total_price) "
				+ "values(?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, orderItem.getOrderId());
			ps.setInt(2, orderItem.getUser_id());
			ps.setString(3, orderItem.getProductName());
			ps.setString(4, orderItem.getProductImage());
			ps.setBigDecimal(5, orderItem.getProductPrice());
			ps.setInt(6, orderItem.getQuantity());
			ps.setBigDecimal(7, orderItem.getTotalPrice());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
		return false;
	}

	/**
	 * 删除某一订单下的所有订单项
	 * 
	 * @param orderId
	 * @return
	 */
	public boolean deleteOrderItemByOrderId(Integer orderId) {
		String sql = "delete from order_item where order_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, orderId);
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
		return false;
	}
}
