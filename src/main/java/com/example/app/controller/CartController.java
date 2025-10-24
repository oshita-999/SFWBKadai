package com.example.app.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.CartLine;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
	
	//セッションオブジェクト
	private final HttpSession session;
	
	//localhost:8080/cartにリクエストがあったときに
	//showCart()が呼び出される
	@GetMapping
	public String showCart(Model model) {
		
		//現在のカート情報をセッションから取得
		List<CartLine> cart = (List<CartLine>)session.getAttribute("cart");
		//カートができていない場合新規作成
		if (cart == null) cart = new ArrayList<>();

		//商品ごとの小計から合計を計算する
        int total = cart.stream().mapToInt(CartLine::getSubtotal).sum();

        //modelにカート情報を保存
        model.addAttribute("cart", cart);
        //modelに合計を保存
        model.addAttribute("total", total);
        
        //cart.htmlを返す
		return "cart";
		
	}
	
	//フォームからlocalhost:8080/cartにリクエストがあったとき
	//（購入ボタンが押されたとき）proceedPayment()が呼び出される
	@PostMapping
	public String proceedPayment() {
		
		//セッション情報を破棄
		session.invalidate();
		//決済完了の処理に移行する
		return "redirect:/cart/done";
	}
	
	//localhost:8080/cart/doneにリクエストがあったとき
	//showCompleted()が呼び出される
	@GetMapping("/done")
	public String showCompleted() {
		//cartDone.htmlを返す
		return "cartDone";
	}

}
