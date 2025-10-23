package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.Item;
import com.example.app.service.ItemService;

import lombok.RequiredArgsConstructor;

//引数ありのコンストラクタを自動生成
@RequiredArgsConstructor
//コントローラクラス
@Controller
public class ItemController {
	
	//サービスクラスのオブジェクト
	private final ItemService service;
	
	//localhost:8080/にリクエストがあったときに
	//itemList()が呼び出される
	@GetMapping("/")
	public String itemList() {
		//index.htmlを返す
		return "index";
	}
	
	//localhost:8080/itemにリクエストがあったときに
	//showItem()が呼び出される
	@GetMapping("/item")
	public String showItem(
			//idがnullを一時的に許可する
			@RequestParam(required = false) Integer id,
			//データの一時的保存場所
			Model model) {
		//idがnullの時は/(index)にリダイレクトされる
		if(id == null) {
			return "redirect:/";
		}
		
		//サービスオブジェクトのメソッド呼び出し
		//商品番号から商品を返す
		Item item = service.getItemById(id);
		
		//商品がnullの時は/(index)にリダイレクトされる
		if(item == null) {
			return "redirect:/";
		}
		//modelに商品を保存
		model.addAttribute("item", item);
		//modelに個数0を保存
		model.addAttribute("unit", 0);
		//modelに金額0を保存
		model.addAttribute("subtotal", 0);
		//item.htmlを返す
		return "item";
	}
	
	//フォームからlocalhost:8080/itemにリクエストがあったときに
	//calcTotal()が呼び出される
	@PostMapping("/item")
	public String calcSubtotal(
			//商品番号
			@RequestParam int id,
			//個数
			@RequestParam int unit,
			//一時的データ保存場所
			Model model) {
		
		//サービスオブジェクトのメソッド呼び出し
		//商品番号から商品を返す
		Item item = service.getItemById(id);
		//金額の計算
		int subtotal = item.getPrice() * unit;
		
		//modelに商品を保存
		model.addAttribute("item", item);
		//modelに個数を保存
		model.addAttribute("unit", unit);
		//modelに金額を保存
		model.addAttribute("subtotal", subtotal);
		//item.htmlを返す
		return "item";
	}

}
