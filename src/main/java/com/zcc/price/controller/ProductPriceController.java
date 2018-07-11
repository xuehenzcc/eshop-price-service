/**
 * 版权：zcc
 * 作者：c0z00k8
 * @data 2018年7月5日
 */
package com.zcc.price.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcc.price.model.ProductPrice;
import com.zcc.price.service.ProductPriceService;

/**
 * @author c0z00k8
 *
 */
@Controller
@RequestMapping("/product-price")
public class ProductPriceController {

	@Autowired
	private ProductPriceService productPriceService;
	
	@RequestMapping("/add") 
	@ResponseBody
	public String add(ProductPrice productPrice) {
		try {
			productPriceService.add(productPrice);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/update") 
	@ResponseBody
	public String update(ProductPrice productPrice) {
		try {
			productPriceService.update(productPrice); 
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/delete") 
	@ResponseBody
	public String delete(Long id) {
		try {
			productPriceService.delete(id); 
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/findById") 
	@ResponseBody
	public ProductPrice findById(Long id){
		ProductPrice price = new ProductPrice();
		price.setId(id);
		try {
			return productPriceService.findById(price);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new ProductPrice();
	}
	
	@RequestMapping("/findByProductId") 
	@ResponseBody
	public ProductPrice findByProductId(Long productId){
		ProductPrice price = new ProductPrice();
		price.setProductId(productId);
		try {
			return productPriceService.findByProductId(price);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new ProductPrice();
	}
}
