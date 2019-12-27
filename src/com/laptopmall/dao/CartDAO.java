package com.laptopmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptopmall.bean.Cart;
import com.laptopmall.util.JdbcUtil;

public class CartDAO {
	/**
	 * 查询指定用户id的购物车全部信息
	 * 
	 * @param userId 用户id
	 * @return List of Cart
	 */
	public List<Cart> listCartByUserId(Integer userId) {
		String sql = "select * from cart where user_id=?";
		List<Cart> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()) {
				Cart cart = new Cart();
				cart.setId(rs.getInt("id"));
				cart.setProductId(rs.getInt("product_id"));
				cart.setQuantity(rs.getInt("quantity"));
				cart.setUserId(rs.getInt("user_id"));
				list.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return list;
	}

	public Cart getCartByProductIdAndUserId(Integer userId,Integer productId) {
		String sql = "select * from cart where user_id=? and product_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, productId);
			rs = ps.executeQuery();
			if (rs.next()) {
				Cart cart = new Cart();
				cart.setId(rs.getInt("id"));
				cart.setProductId(rs.getInt("product_id"));
				cart.setQuantity(rs.getInt("quantity"));
				cart.setUserId(rs.getInt("user_id"));
				return cart;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}

		return null;
	}
	
	/**
	 * 删除指定某一的购物车项
	 * 
	 * @param cartId 购物车项id
	 * @return
	 */
	public boolean deleteByCartId(Integer cartId) {
		String sql = "delete from cart where id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cartId);
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
		return false;
	}

	/**
	 * 新增商品到购物车
	 * 
	 * @param cart
	 * @return
	 */
	public boolean insertCart(Cart cart) {
		String sql = "insert into cart(product_id,user_id,quantity) values(?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cart.getProductId());
			ps.setInt(2, cart.getUserId());
			ps.setInt(3, cart.getQuantity());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
		return false;
	}

	/**
	 * 更新购物车某件商品的数量
	 * 
	 * @param cartId
	 * @param quantity
	 * @return
	 */
	public boolean updateQuantityByCartId(Integer cartId, Integer quantity) {
		String sql = "update cart set quantity=? where id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, quantity);
			ps.setInt(2, cartId);
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
		return false;
	}

	/**
	 * 清空购物车
	 * @param userId
	 * @return
	 */
	public boolean deleteCartByUserId(Integer userId) {
		String sql = "delete from cart where user_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
		return false;
	}
}
