package com.laptopmall.servlet.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptopmall.bean.Brand;
import com.laptopmall.dao.BrandDAO;

@WebServlet("/save_brand")
public class SaveBrandServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BrandDAO brandDAO;

	@Override
	public void init() throws ServletException {
		brandDAO = new BrandDAO();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String brandName = req.getParameter("brand_name");
		if (brandDAO.getBrandByName(brandName) != null) {
			req.setAttribute("info", "该品类已存在");
			req.getRequestDispatcher("/backend/brand_edit.jsp").forward(req, resp);
		} else {
			Brand brand = new Brand();
			brand.setName(brandName);
			brandDAO.insertBrand(brand);
			req.setAttribute("info", "添加品类成功");
			req.getRequestDispatcher("/backend/brand_edit.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
