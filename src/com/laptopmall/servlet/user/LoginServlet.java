package com.laptopmall.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptopmall.bean.User;
import com.laptopmall.dao.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;
	
	@Override
	public void init() throws ServletException {
		// user 数据库操作类
		userDAO = new UserDAO();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		// 获取请求参数
		String loginName = req.getParameter("login_name");
		String password = req.getParameter("password");
		// 根据登录名和密码查询查询数据库表user
		User user = userDAO.getUserByLoginNameAndPassword(loginName, password);
		if(user == null) {
			// 返回错误信息
			req.setAttribute("errMsg", "账号或密码错误");
			// 请求转发到登录页面
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}else {
			// 将用户信息保存在Session中
			req.getSession().setAttribute("CURRENT_USER", user);
			// 重定向到商品展示页面
			resp.sendRedirect("product_list");
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
