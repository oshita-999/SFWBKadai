package com.example.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

//全フィールドを引数とするコンストラクタを自動生成
@AllArgsConstructor
//setter,getterを自動生成
@Data
//商品クラス
public class Item {
	//商品名
	private String name;
	//単価
	private int price;
}
