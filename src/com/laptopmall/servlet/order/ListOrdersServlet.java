package com.laptopmall.servlet.order;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptopmall.bean.Order;
import com.laptopmall.bean.User;
import com.laptopmall.dao.OrderDAO;
import com.laptopmall.page.PageInfo;

@WebServlet("/list_order")
public class ListOrdersServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO;
	
	@Override
	public void init() throws ServletException {
		orderDAO = new OrderDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		User user = (User)req.getSession().getAttribute("CURRENT_USER");
		String curPageStr = req.getParameter("currentPage");
		curPageStr = curPageStr == null ? "1" : curPageStr;
		// 判断当前用户的角色
		if(user.getRole()==1) {
			// 后台管理页面
			// 查询全部订单信息
			PageInfo<Order> pageInfo = orderDAO.listOrders(Integer.parseInt(curPageStr), 10);
			// 计算总销售额
			BigDecimal salesValue = new BigDecimal("0");
			for (Order order : orderDAO.listOrders()) {
				salesValue = salesValue.add(order.getPayment());
			}
			
			req.setAttribute("pageInfo", pageInfo);
			req.setAttribute("salesValue", salesValue);
			req.getRequestDispatcher("/backend/order_list.jsp").forward(req, resp);
		}else {
			// 用户页面
			// 程序当前用户的订单信息
			PageInfo<Order> pageInfo = orderDAO.listOrders(user.getId(), Integer.parseInt(curPageStr), 10);
			req.setAttribute("pageInfo", pageInfo);
			req.getRequestDispatcher("/portal/order_list.jsp").forward(req, resp);
		}
	}

}
