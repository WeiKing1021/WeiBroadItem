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

		// �p�G���O�S���I����M�I�Ů�
		if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}

		// �p�G�S�����F��k��
		if (!event.hasItem()) {
			return;
		}

		// ���o���a
		Player player = event.getPlayer();
		
		// ���o���~
		ItemStack click_item = event.getItem();

		// �P�_�O�_�O�Ƥ��I��
		if (player.getInventory().getItemInOffHand().equals(click_item)) {
			return;
		}
		
		// ���ը��o�I�諸���~, �P�_�O�_�ݩ�s�����~
		WeiBroadItem broad_item = BroadItemManager.getBroadItemByItemStack(click_item);
		
		// �p�G���O�ƻ�F��
		if (broad_item == null) {
			return;
		}
		
		// �p�G���@��
		if (!broad_item.equals(click_item)) {
			return;
		}
		
		// �����ƥ�
		event.setCancelled(true);
		
		// �p�G�w�g�O���ݿ�J�T���Ҧ�, �N����
		if (BroadItemManager.isPlayerMessageInput(player) != null) {
			BroadItemManager.setPlayerMessageInput(player, null);
			Manager.send(player, Lang.getLang("INPUT_CANCEL"));
			return;
		}
		
		// �N���a�]���T����J�Ҧ�
		BroadItemManager.setPlayerMessageInput(player, broad_item.key);
		
		// �Ъ��a��J�T��
		Manager.send(player, Lang.getLang("INPUT_ENTER"));
	}
}
