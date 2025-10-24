package com.example.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//カート1行分のデータ
public class CartLine {
	//商品番号
	private int id;
	//商品名
	private String name;
	//単価
	private int price;
	//個数
	private int unit;
	
	//小計の計算
	public int getSubtotal() {
		return price * unit;
	}

}
