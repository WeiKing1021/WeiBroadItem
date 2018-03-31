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
		
		// ���o���a
		Player player = event.getPlayer();
		
		// �p�G���a���O�b��J�s���T��
		String key = null;
		if ( (key = BroadItemManager.isPlayerMessageInput(player)) == null) {
			return;
		}
		
		// �����ƥ�
		event.setCancelled(true);
		
		// �������a����J�T�����A
		BroadItemManager.setPlayerMessageInput(player, null);
		
		// ���o�s���T��
		String mes = event.getMessage().replace("&", "��");
		
		// ���o�s�����~
		WeiBroadItem broad_item = BroadItemManager.getBroadItemByKey(key);
		
		// ���ծ���
		boolean consume_result = BroadItemManager.consumeBroadItem(player, broad_item);
		
		// �p�G�S�����\����
		if (!consume_result) {
			Manager.send(player, Lang.getLang("ITEM_MISS"));
			return;
		} 
		
		// �ƥ�
		BroadItemCastEvent cast_event = new BroadItemCastEvent(player, broad_item, mes);
		
		// �I�s�ƥ�
		Manager.callEvent(cast_event);
		
		// �p�G�ƥ�Q����
		if (cast_event.isCancelled()) {
			return;
		}
		
		// �s���Ƶ{��
		BroadProcess broad = new BroadProcess(player, mes, broad_item.key);
		
		// ��m�s���Ƶ{
		ProcessManager.addBroadProcess(broad);
		
		// �i�����a�s���w�g���T�B�z
		Manager.send(player, Lang.getLang("BROAD_PUT_SUCCESSFUL"));
		
	}
}
