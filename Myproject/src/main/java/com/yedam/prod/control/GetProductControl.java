package com.yedam.prod.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.prod.domain.ProductVO;
import com.yedam.prod.service.ProductService;
import com.yedam.prod.service.ProductServiceImpl;


public class GetProductControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		ProductService service = new ProductServiceImpl();	
		ProductVO vo = service.getProduct(Integer.parseInt(id));
		
		req.setAttribute("getProduct", vo);
		
		List<ProductVO> list = service.products();
		req.setAttribute("list", list);
		
		return "prod/prodMain.tiles";
	}

}
