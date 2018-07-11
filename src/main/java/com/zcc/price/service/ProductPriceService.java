/**
 * 版权：zcc
 * 作者：c0z00k8
 * @data 2018年7月5日
 */
package com.zcc.price.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zcc.price.mapper.ProductPriceMapper;
import com.zcc.price.model.ProductPrice;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author c0z00k8
 *
 */
@Service
public class ProductPriceService {

	@Autowired
	private ProductPriceMapper productPriceMapper;
	@Autowired
	private JedisPool jedisPool;
	
	public void add(ProductPrice productPrice) {
		productPriceMapper.add(productPrice); 
		Jedis jedis = jedisPool.getResource();
		jedis.set("product_price_" + productPrice.getProductId(), JSONObject.toJSONString(productPrice));
	}

	public void update(ProductPrice productPrice) {
		productPriceMapper.update(productPrice); 
		Jedis jedis = jedisPool.getResource();
		jedis.set("product_price_" + productPrice.getProductId(), JSONObject.toJSONString(productPrice));
	}

	public void delete(Long id) {
		ProductPrice price=new ProductPrice();
		price.setId(id);
		ProductPrice productPrice = findById(price);
		productPriceMapper.delete(id); 
		Jedis jedis = jedisPool.getResource();
		jedis.del("product_price_" + productPrice.getProductId());
	}

	public ProductPrice findById(ProductPrice price) {
		return productPriceMapper.findById(price);
	} 
	
	public ProductPrice findByProductId(ProductPrice price) {
		ProductPrice productPrice=null;
		Jedis jedis = jedisPool.getResource();
		String jsonObj=jedis.get("product_price_"+price.getProductId());
		if(null != jsonObj && !"".equals(jsonObj)){
			return JSONObject.parseObject(jsonObj, ProductPrice.class);
		}else{
			productPrice = productPriceMapper.findById(price);
			jedis.set("product_price_"+price.getProductId(), JSONObject.toJSONString(productPrice));
		}
		return productPrice;
	} 
}
