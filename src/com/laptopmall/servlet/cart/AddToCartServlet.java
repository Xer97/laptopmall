package com.laptopmall.servlet.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptopmall.bean.Cart;
import com.laptopmall.bean.User;
import com.laptopmall.dao.CartDAO;

@WebServlet("/add_to_cart")
public class AddToCartServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private CartDAO cartDAO;
	@Override
	public void init() throws ServletException {
		cartDAO = new CartDAO();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		User user = (User)req.getSession().getAttribute("CURRENT_USER");
		String prodIdStr = req.getParameter("product_id");
		String quantityStr = req.getParameter("quantity");
		// 查询数据库，查看当前用户的购物车是否已经有该商品
		Cart cart = cartDAO.getCartByProductIdAndUserId(user.getId(), Integer.parseInt(prodIdStr));
		if(cart==null) {
			//购物车没该商品,添加进购物车
			Cart newCart = new Cart();
			newCart.setProductId(Integer.parseInt(prodIdStr));
			newCart.setQuantity(Integer.parseInt(quantityStr));
			newCart.setUserId(user.getId());
			cartDAO.insertCart(newCart);
		}else {
			//购物车已有该商品,修改其数量
			cartDAO.updateQuantityByCartId(cart.getId(), cart.getQuantity()+Integer.parseInt(quantityStr));
		}
		req.getRequestDispatcher("/portal/add_cart_success.jsp").forward(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
