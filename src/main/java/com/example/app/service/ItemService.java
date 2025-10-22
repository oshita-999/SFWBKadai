package com.example.app.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.app.domain.Item;

//サービスクラス
@Service
public class ItemService {
	
	//商品番号と商品のマップ
	private Map<Integer, Item> itemMap;
	
	//サービスクラスのコンストラクタ
	public ItemService() {
		//マップを初期化
		itemMap = new HashMap<>();
		//マップに商品番号1和風レターセットを追加
		itemMap.put(1, new Item("和風レターセット", 780));
		//マップに商品番号2毛筆ペンを追加
		itemMap.put(2, new Item("毛筆ペン", 280));
		//マップに商品番号3簡単万年筆を追加
		itemMap.put(3, new Item("簡単万年筆", 480));
	}
	
	//商品番号から商品を取得する
	public Item getItemById(int id) {
		//商品番号から商品を返す
		return itemMap.get(id);
	}

}