/**
 * 版权：zcc
 * 作者：c0z00k8
 * @data 2018年7月5日
 */
package com.zcc.price.mapper;

import com.zcc.price.model.ProductPrice;

/**
 * @author c0z00k8
 *
 */
public interface ProductPriceMapper {

	public void add(ProductPrice productPrice);
	
	public void update(ProductPrice productPrice);
	
	public void delete(Long id);
	
	public ProductPrice findById(ProductPrice price);
}
