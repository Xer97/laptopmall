package com.laptopmall.servlet.order;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptopmall.bean.Cart;
import com.laptopmall.bean.Order;
import com.laptopmall.bean.OrderItem;
import com.laptopmall.bean.Product;
import com.laptopmall.bean.User;
import com.laptopmall.dao.CartDAO;
import com.laptopmall.dao.OrderDAO;
import com.laptopmall.dao.OrderItemDAO;
import com.laptopmall.dao.ProductDAO;

@WebServlet("/submit_order")
public class SubmitOrderServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CartDAO cartDAO;
	private ProductDAO productDAO;
	private OrderDAO orderDAO;
	private OrderItemDAO orderItemDAO;
	@Override
	public void init() throws ServletException {
		cartDAO = new CartDAO();
		productDAO = new ProductDAO();
		orderDAO = new OrderDAO();
		orderItemDAO = new OrderItemDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		// 获取当前用户购物车信息
		User user = (User)req.getSession().getAttribute("CURRENT_USER");
		List<Cart> carts = cartDAO.listCartByUserId(user.getId());
		// 创建并插入新订单(此时为了先获取订单号)
		Order order = new Order();
		order.setUserId(user.getId());
		orderDAO.insertOder(order);
		// 获取购物车每件商品信息
		BigDecimal payment = new BigDecimal("0");
		for (Cart cart : carts) {
			Product product = productDAO.getProductById(cart.getProductId());
			// 为每件商品创建orderItem,并保存到数据库中
			OrderItem orderItem = new OrderItem();
			orderItem.setUser_id(user.getId());
			orderItem.setOrderId(order.getId());
			orderItem.setProductName(product.getName());
			orderItem.setProductImage(product.getImage());
			orderItem.setProductPrice(product.getPrice());
			orderItem.setQuantity(cart.getQuantity());
			// 单件商品总价小计 单价*数量
			BigDecimal sum = product.getPrice().multiply(new BigDecimal(cart.getQuantity().toString()));
			orderItem.setTotalPrice(sum);
			orderItemDAO.insertOrderItem(orderItem);
			// 累计总支付金额
			payment = payment.add(sum);
			// 商品减库存
			product.setStock(product.getStock()-cart.getQuantity());
			productDAO.updateProduct(product);
		}
		// 订单总金额更新到Order中
		order.setPayment(payment);
		orderDAO.updateOrder(order);
		// 清空当前用户的购物车
		cartDAO.deleteCartByUserId(user.getId());
		// 传递orderId,用于跳转到订单详情
		req.setAttribute("orderId", order.getId());
		req.getRequestDispatcher("/portal/submit_order_success.jsp").forward(req, resp);
	}
	
	
}
