package com.laptopmall.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptopmall.bean.User;
import com.laptopmall.dao.UserDAO;
@WebServlet("/save_user")
public class SaveUserInfoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		// 从Session中获取当前用户信息
		User user = (User)req.getSession().getAttribute("CURRENT_USER");
		String realName = req.getParameter("real_name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		// 重新设置属性
		user.setRealName(realName);
		user.setPhone(phone);
		user.setAddress(address);
		// 更新数据库中当前用户的信息
		userDAO.updateUser(user);
		req.setAttribute("info", "保存成功");
		req.getRequestDispatcher("/portal/user_info.jsp").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
