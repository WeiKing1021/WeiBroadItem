package me.wei.broaditem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.wei.broaditem.BroadItemManager;
import me.wei.broaditem.Manager;
import me.wei.broaditem.broad.BroadProcess;
import me.wei.broaditem.broad.ProcessManager;
import me.wei.broaditem.broad.items.WeiBroadItem;
import me.wei.broaditem.customEvents.BroadItemCastEvent;
import me.wei.broaditem.lang.Lang;

public class PlayerChatListener implements Listener {	
	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
		
		// 取得玩家
		Player player = event.getPlayer();
		
		// 如果玩家不是在輸入廣播訊息
		String key = null;
		if ( (key = BroadItemManager.isPlayerMessageInput(player)) == null) {
			return;
		}
		
		// 關閉事件
		event.setCancelled(true);
		
		// 關閉玩家的輸入訊息狀態
		BroadItemManager.setPlayerMessageInput(player, null);
		
		// 取得廣播訊息
		String mes = event.getMessage().replace("&", "§");
		
		// 取得廣播物品
		WeiBroadItem broad_item = BroadItemManager.getBroadItemByKey(key);
		
		// 嘗試消耗
		boolean consume_result = BroadItemManager.consumeBroadItem(player, broad_item);
		
		// 如果沒有成功消耗
		if (!consume_result) {
			Manager.send(player, Lang.getLang("ITEM_MISS"));
			return;
		} 
		
		// 事件
		BroadItemCastEvent cast_event = new BroadItemCastEvent(player, broad_item, mes);
		
		// 呼叫事件
		Manager.callEvent(cast_event);
		
		// 如果事件被關閉
		if (cast_event.isCancelled()) {
			return;
		}
		
		// 廣播排程序
		BroadProcess broad = new BroadProcess(player, mes, broad_item.key);
		
		// 放置廣播排程
		ProcessManager.addBroadProcess(broad);
		
		// 告知玩家廣播已經正確處理
		Manager.send(player, Lang.getLang("BROAD_PUT_SUCCESSFUL"));
		
	}
}
