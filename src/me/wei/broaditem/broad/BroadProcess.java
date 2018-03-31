package me.wei.broaditem.broad;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.wei.broaditem.BroadItemManager;
import me.wei.broaditem.broad.items.WeiBroadItem;

public class BroadProcess {
	
	// 玩家
	private Player player;
	
	// 原始訊息
	private String mes;
	
	// 廣播道具的代碼
	private String broad_item_key;
	
	// 建構子
	public BroadProcess(Player player, String mes, String broad_item_key) {
		this.player = player;
		this.mes = mes;
		this.broad_item_key = broad_item_key;
	}
	
	// 取得玩家
	public Player getPlayer() {
		return this.player;
	}
	
	// 取得原始訊息
	public String getMessage() {
		return this.mes;
	}
	
	// 取得廣播用的道具
	public WeiBroadItem getBroadItem() {
		return BroadItemManager.getBroadItemByKey(broad_item_key);
	}
	
	// 取得廣播類型
	public List<BroadType> getBroadType() {
		List<BroadType> lst = new ArrayList<>();
		
		List<BroadType> broad_type = getBroadItem().broad_type;

		// 如果廣播道具有任何廣播類型
		if (broad_type != null && broad_type.size() > 0) {
			lst.addAll(broad_type);
		}

		return lst;
	}
}
