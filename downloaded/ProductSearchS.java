package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.ProductDAO;
import dto.ProductDTO;

public class ProductSearchS {

	public List<ProductDTO> productList(HttpServletRequest request) {
		ProductDAO pdao = new ProductDAO();
		pdao.dbconnection();
		String search = request.getParameter("psearch");
		List<ProductDTO> plist = pdao.ProductList(search);
		pdao.dbClose();
		return plist;
	}

}
