package com.example.app.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.CartLine;
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
	//セッションオブジェクト
	private final HttpSession session;
	
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
		int unit = 0;
		int subTotal = 0;
		
		//商品がnullの時は/(index)にリダイレクトされる
		if(item == null) {
			return "redirect:/";
		}
		
		
		//modelに商品を保存
		model.addAttribute("item", item);
		//modelに個数を保存
		model.addAttribute("unit", unit);
		//modelに金額を保存
		model.addAttribute("subtotal", subTotal);
		//item.htmlを返す
		return "item";
	}
	
	//フォームからlocalhost:8080/itemにリクエストがあったときに
	//calcSubtotal()が呼び出される
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
		//金額の計算（単価*数量）
		int subtotal = item.getPrice() * unit;
		
		//modelに商品を保存
		model.addAttribute("item", item);
		//modelに個数を保存
		model.addAttribute("unit", unit);
		//modelに金額を保存
		model.addAttribute("subtotal", subtotal);
		
		//現在のカート情報をセッションから取得
		List<CartLine> cart = (List<CartLine>) session.getAttribute("cart");
		//cartができていない場合新規作成
		if(cart == null) {
			cart = new ArrayList<>();
			//セッションに保存
			session.setAttribute("cart", cart);
		}
		
		//同じ商品が既に存在しているか調べる
		CartLine existing = null;
		//cartの各行でループ
	    for (CartLine line : cart) {
	    	//入力された商品番号と同じ番号の商品が既に存在している場合
	    	//その行の値を代入してループを終了
	        if (line.getId() == id) {
	            existing = line;
	            break;
	        }
	    }
	    
	    //入力された商品番号と同じ番号の商品が既に存在している場合
	    if (existing != null) {
	    	//数量を加算する
	        existing.setUnit(existing.getUnit() + unit);
	    //入力された商品番号と同じ番号の商品がなかった場合    
	    } else {
	    	//カートに商品情報を追加する
	        cart.add(new CartLine(id, item.getName(), item.getPrice(), unit));
	    }
		
		//item.htmlを返す
		return "item";
	}

}
