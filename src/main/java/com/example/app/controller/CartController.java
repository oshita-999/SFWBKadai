package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
	
	//private final ItemService service;
	//private final HttpSession session;
	
	@GetMapping
	public String showCart(Model model) {
		
		
		return "cart";
		
	}
	
	@PostMapping
	public String proceedPayment() {
		return "";
	}

}
