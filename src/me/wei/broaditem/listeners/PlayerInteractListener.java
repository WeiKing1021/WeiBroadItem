package me.wei.broaditem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.wei.broaditem.BroadItemManager;
import me.wei.broaditem.Manager;
import me.wei.broaditem.broad.items.WeiBroadItem;
import me.wei.broaditem.lang.Lang;

public class PlayerInteractListener implements Listener {
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {

		// 如果不是又鍵點方塊和點空氣
		if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}

		// 如果沒有拿東西右鍵
		if (!event.hasItem()) {
			return;
		}

		// 取得玩家
		Player player = event.getPlayer();
		
		// 取得物品
		ItemStack click_item = event.getItem();

		// 判斷是否是副手點擊
		if (player.getInventory().getItemInOffHand().equals(click_item)) {
			return;
		}
		
		// 嘗試取得點選的物品, 判斷是否屬於廣播物品
		WeiBroadItem broad_item = BroadItemManager.getBroadItemByItemStack(click_item);
		
		// 如果不是甚麼東西
		if (broad_item == null) {
			return;
		}
		
		// 如果不一樣
		if (!broad_item.equals(click_item)) {
			return;
		}
		
		// 關閉事件
		event.setCancelled(true);
		
		// 如果已經是等待輸入訊息模式, 就取消
		if (BroadItemManager.isPlayerMessageInput(player) != null) {
			BroadItemManager.setPlayerMessageInput(player, null);
			Manager.send(player, Lang.getLang("INPUT_CANCEL"));
			return;
		}
		
		// 將玩家設為訊息輸入模式
		BroadItemManager.setPlayerMessageInput(player, broad_item.key);
		
		// 請玩家輸入訊息
		Manager.send(player, Lang.getLang("INPUT_ENTER"));
	}
}
