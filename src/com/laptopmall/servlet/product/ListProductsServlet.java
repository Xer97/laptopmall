package com.laptopmall.servlet.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptopmall.bean.Brand;
import com.laptopmall.bean.Product;
import com.laptopmall.bean.User;
import com.laptopmall.dao.BrandDAO;
import com.laptopmall.dao.ProductDAO;
import com.laptopmall.page.PageInfo;
import com.laptopmall.page.QueryObject;

@WebServlet("/product_list")
public class ListProductsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private BrandDAO brandDAO;

	@Override
	public void init() throws ServletException {
		productDAO = new ProductDAO();
		brandDAO = new BrandDAO();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		User user = (User) req.getSession().getAttribute("CURRENT_USER");
		String keyword = req.getParameter("keyword");
		String brandIdStr = req.getParameter("brand_id");
		String curPageStr = req.getParameter("current_page");
		keyword = keyword == null ? "" : keyword;
		brandIdStr = brandIdStr == null ? "0" : brandIdStr;
		// 封装查询信息
		QueryObject qo = new QueryObject(keyword, Integer.parseInt(brandIdStr));
		if (curPageStr != null) {
			qo.setCurrentPage(Integer.parseInt(curPageStr));
		}
		// 从数据库获取商品的分页信息
		PageInfo<Product> pageInfo = productDAO.listProducts(qo);
		req.setAttribute("pageInfo", pageInfo);
		// 品牌信息显示
		List<Brand> brands = brandDAO.listBrands();
		req.setAttribute("brands", brands);
		// 高级查询数据回显
		req.setAttribute("qo", qo);
		if (user.getRole() == 1) {
			// 跳转到后台管理界面
			req.getRequestDispatcher("/backend/product_list.jsp").forward(req, resp);
		} else {
			// 跳转到用户购物界面
			req.getRequestDispatcher("/portal/product_list.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
