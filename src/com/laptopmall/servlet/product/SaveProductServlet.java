package com.laptopmall.servlet.product;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.laptopmall.bean.Product;
import com.laptopmall.dao.ProductDAO;

@WebServlet("/save_product")
public class SaveProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// 允许的图片类型
	private static String ALLOWED_IMAGE_TYPE = new String("png;PNG;jpg;JPG;jpeg;JPEG");
	private ProductDAO productDAO;

	@Override
	public void init() throws ServletException {
		productDAO = new ProductDAO();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		Product product = new Product();
		// 处理包含文件的表单数据
		try {
			Iterator<FileItem> iterator = upload.parseRequest(req).iterator();
			while (iterator.hasNext()) {
				FileItem item = iterator.next();
				if (!item.isFormField()) {
					// 判断文件名是否为空,为空代表是更新商品,但不更新图片
					// 前端进行了控制,只有更新商品才不用强制上传文件
					if (item.getName() == null || "".equals(item.getName())) {
						// 从数据库中找到该商品,将原图片给取出进行赋值
						product.setImage(productDAO.getProductById(product.getId()).getImage());
					} else {
						// 处理上传的图片
						String extendname = FilenameUtils.getExtension(item.getName());
						if (!Arrays.asList(ALLOWED_IMAGE_TYPE.split(";")).contains(extendname)) {
							// 图片格式不对，重新回到编辑页面进行提示，原本是编辑的也进行回显
							req.setAttribute("info", "请上传正确的图片格式！");
							String str = product.getId() == null ? "" : "?id=" + product.getId();
							req.getRequestDispatcher("edit_product" + str).forward(req, resp);
							return;
						}
						String storageName = UUID.randomUUID().toString() + "." + extendname;
						// 保存文件名
						product.setImage(storageName);
						String storageDir = req.getServletContext().getRealPath("/images");
						item.write(new File(storageDir, storageName));
					}
				} else {
					// 处理其余字段
					if (item.getFieldName().equals("product_id")) { // 商品id
						String idStr = item.getString("utf-8");
						if (idStr != null && !"".equals(idStr)) {
							product.setId(Integer.parseInt(item.getString("utf-8")));
						}
					} else if (item.getFieldName().equals("product_name")) { // 商品名
						product.setName(item.getString("utf-8"));
					} else if (item.getFieldName().equals("brand_id")) { // 品牌id
						product.setBrandId(Integer.parseInt(item.getString("utf-8")));
					} else if (item.getFieldName().equals("detail")) { // 描述
						product.setDetail(item.getString("utf-8"));
					} else if (item.getFieldName().equals("stock")) { // 库存
						product.setStock(Integer.parseInt(item.getString("utf-8")));
					} else if (item.getFieldName().equals("price")) { // 单价
						product.setPrice(new BigDecimal(item.getString("utf-8")));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(product.getId()==null) {
			//新增商品
			productDAO.insertProduct(product);
			req.setAttribute("info", "新增商品成功");
		}else {
			//更新商品
			productDAO.updateProduct(product);
			req.setAttribute("info", "更新商品成功");
		}
		String str = product.getId() == null ? "" : "?id=" + product.getId();
		req.getRequestDispatcher("edit_product" + str).forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
