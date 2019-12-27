package com.laptopmall.servlet.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptopmall.dao.CartDAO;

@WebServlet("/delete_cart")
public class DeleteFromCartServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private CartDAO cartDAO;
	@Override
	public void init() throws ServletException {
		cartDAO = new CartDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String idStr = req.getParameter("id");
		// 从数据库中删除当前cart
		cartDAO.deleteByCartId(Integer.parseInt(idStr));
		resp.sendRedirect("list_cart");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
