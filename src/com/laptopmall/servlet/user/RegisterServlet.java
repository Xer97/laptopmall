package com.laptopmall.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptopmall.bean.User;
import com.laptopmall.dao.UserDAO;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	
	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String loginName = req.getParameter("login_name");
		String password = req.getParameter("password");
		String ck_password = req.getParameter("check_password");
		if(!password.equals(ck_password)) {
			req.setAttribute("pwdMsg", "前后密码不一致");
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			return;
		}
		// 根据登录名查询数据库，判断用户是否存在
		if(userDAO.getUserByLoginName(loginName)!=null) {
			req.setAttribute("loginNameMsg", "用户名已存在，请重新输入");
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			return;
		}
		User user = new User();
		user.setLoginName(loginName);
		user.setPassword(password);
		userDAO.insertUser(user);
		req.setAttribute("loginNameMsg", "注册成功");
		req.getRequestDispatcher("register.jsp").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
