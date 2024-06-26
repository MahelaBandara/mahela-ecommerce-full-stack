package com.mahela.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mahela.exceptions.ProductException;
import com.mahela.model.Product;
import com.mahela.model.Review;
import com.mahela.model.User;
import com.mahela.repository.ProductRepository;
import com.mahela.repository.ReviewRepository;
import com.mahela.request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService{
	
	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;
	
	public ReviewServiceImplementation(ReviewRepository reviewRepository,ProductService productService,ProductRepository productRepository) {
		this.reviewRepository = reviewRepository;
		this.productRepository = productRepository;
		this.productService = productService;
	}
	
	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		Product product = productService.findProductById(req.getProductId());
		Review review = new Review();
		
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		
		return reviewRepository.getAllProductsReview(productId);
	}

}
