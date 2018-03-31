package me.wei.broaditem;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.wei.broaditem.broad.items.WeiBroadItem;

@SuppressWarnings( {"unlikely-arg-type"} )
public class BroadItemManager {
	
	// 透過key取得廣播物品
	public static WeiBroadItem getBroadItemByKey(String key) {
		return GlobalVar.broaditem_map.get(key);
	}
		
	public static WeiBroadItem getBroadItemByItemStack(ItemStack item_stack) {
		// 如果傳入的物品null, 回傳null
		if (item_stack == null) {return null;}
		
		// 如果傳入的物品ItemMeta或者沒有名字, 回傳null
		if (!item_stack.hasItemMeta() || !item_stack.getItemMeta().hasDisplayName()) {return null;}
		
		// 回傳透過名字找到的結果
		return getBroadItemByDisplay(item_stack.getItemMeta().getDisplayName());
	}
	
	public static WeiBroadItem getBroadItemByDisplay(String display) {
		// 迴圈查找對應的物品
		// 如果用另外一個map來記錄<display,  key>是否比較優? 記憶體狀況?
		for (WeiBroadItem broad_item : GlobalVar.broaditem_map.values()) {
			
			// 初始值
			ItemStack item = null;
			
			// 如果null就略過
			if (broad_item == null || (item = broad_item.toItemStack()) == null) { continue; }
			
			// 如果找得到ItemMeta和Display
			if (!item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) { continue; }
			
			// 取得目標Display
			String item_display = item.getItemMeta().getDisplayName();
			
			if (item_display.equals(display)) {return broad_item;}
		}
		
		// 回傳空值
		return null;
	}
	
	public static void setPlayerMessageInput(Player player, String key) {
		// 取得玩家名稱
		String player_name = player.getName();
		
		// 如果是設為輸入狀態
		if (key != null) {
			if (!GlobalVar.playerInputMap.containsKey(player_name)) {
				GlobalVar.playerInputMap.put(player_name, key);
			}
		}
		// 如果是設為非輸入狀態
		else {
			if (GlobalVar.playerInputMap.containsKey(player_name)) {
				GlobalVar.playerInputMap.remove(player_name);
			}		
		}
	}
	
	public static String isPlayerMessageInput(Player player) {
		// 取得玩家名稱
		String player_name = player.getName();
		
		return GlobalVar.playerInputMap.get(player_name);
	}
	
	public static boolean consumeBroadItem(Player player, WeiBroadItem broad_item) {
		
		// 取得成品
		ItemStack item = broad_item.toItemStack();
		
		// 如果沒找到成品, 就視同沒有消耗
		if (item == null) { return false; }
		
		// 先嘗試消耗手中的
		ItemStack hand_item = player.getInventory().getItemInMainHand();
		
		if (hand_item != null && hand_item.hasItemMeta() && hand_item.getItemMeta().hasDisplayName()) {
			// 判斷手中物品是否是廣播物品
			if (broad_item.equals(hand_item)) {
				hand_item.setAmount(hand_item.getAmount() - 1);
				return true;
			}
			
		}
		
		// 沒有找到, 從包包迴圈開始找
		for (ItemStack pack_item : player.getInventory().getContents()) {
			
			// 判斷資料
			if (pack_item != null && pack_item.hasItemMeta() && pack_item.getItemMeta().hasDisplayName()) {
				
				// 判斷包包中該物品是否是廣播物品
				if (broad_item.equals(pack_item)) {
					pack_item.setAmount(pack_item.getAmount() - 1);
					return true;
				}
				
			}			
		}
		
		return false;
	}
}
