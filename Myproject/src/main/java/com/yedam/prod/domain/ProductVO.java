package com.yedam.prod.domain;

import lombok.Data;

@Data
public class ProductVO {
	private int id;
	private String name;
	private String info;
	private int price;
	private int sprice;
	private String grade;
	private String image;
}
