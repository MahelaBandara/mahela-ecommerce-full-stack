package com.mahela.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mahela.exceptions.ProductException;
import com.mahela.model.Product;
import com.mahela.model.Review;
import com.mahela.request.CreateProductRequest;


public interface ProductService {
	
	public Product createProduct(CreateProductRequest req);
	
	public String deleteProduct(Long productId) throws ProductException;
	
	public Product updateProduct(Long productId,Product req) throws ProductException;
	
	public Product findProductById(Long Id) throws ProductException;
	
	public List<Product> findProductByCategory(String category);
	
	public Page<Product> getAllProduct(String category,List<String>colours,List<String>sizes
			,Integer minPrice,Integer maxPrice,Integer minDiscount, String sort,String stock
			,Integer pageNumber,Integer pageSize);
	
	public List<Product>findAllProducts();
	
}
