package com.mahela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahela.exceptions.CartItemException;
import com.mahela.exceptions.UserException;
import com.mahela.model.CartItem;
import com.mahela.model.User;
import com.mahela.response.ApiResponse;
import com.mahela.service.CartItemService;
import com.mahela.service.UserService;

@RestController
@RequestMapping("/api/cart_item")
public class CartItemController {
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;
	
	@DeleteMapping("/{cartItemId}")
	
	public  ResponseEntity<ApiResponse>deleteCartItem(@PathVariable Long cartItemId,
			@RequestHeader("Authorization")String jwt)throws UserException,CartItemException{
				
		User user = userService.findUserProfileByJwt(jwt);
		cartItemService.removeCartItem(user.getId(), cartItemId);
		
		ApiResponse res = new ApiResponse();
		res.setMessage("item remove from cart");
		res.setStatus(true);
		
		return new ResponseEntity<>(res,HttpStatus.OK);
			
	}
	
	@PutMapping("/{cartItemId}")
	
	public ResponseEntity<CartItem>updateCartItem(
			@RequestBody CartItem cartItem,
			@PathVariable Long cartItemId,
			@RequestHeader("Authorization") String jwt)throws UserException,CartItemException{
				
		User user = userService.findUserProfileByJwt(jwt);
		
		CartItem updateCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
		
		return new ResponseEntity<>(updateCartItem,HttpStatus.OK);
		
	}
	
}
