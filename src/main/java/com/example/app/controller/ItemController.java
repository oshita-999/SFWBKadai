package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.Item;
import com.example.app.service.ItemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ItemController {
	
	private final ItemService service;
	
	@GetMapping("/")
	public String itemList() {
		return "index";
	}
	
	@GetMapping("/item")
	public String showItem(
			@RequestParam(required = false) Integer id,
			Model model) {
		if(id == null) {
			return "redirect:/";
		}
		
		Item item = service.getItemById(id);
		
		if(item == null) {
			return "redirect:/";
		}
		model.addAttribute("item", item);
		model.addAttribute("unit", 0);
		model.addAttribute("subtotal", 0);
		return "item";
	}
	
	@PostMapping("/item")
	public String calcSubtotal(
			@RequestParam int id,
			@RequestParam int unit,
			Model model) {
		Item item = service.getItemById(id);
		int subtotal = item.getPrice() * unit;
		
		model.addAttribute("item", item);
		model.addAttribute("unit", unit);
		model.addAttribute("subtotal", subtotal);
		return "item";
	}

}
