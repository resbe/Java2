package com.yedam.prod.service;

import java.util.List;

import com.yedam.prod.domain.ProductVO;


public interface ProductService {
	public List<ProductVO> products();
	public ProductVO getProduct(int productId);
	

}
