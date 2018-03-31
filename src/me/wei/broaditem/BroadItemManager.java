package me.wei.broaditem;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.wei.broaditem.broad.items.WeiBroadItem;

@SuppressWarnings( {"unlikely-arg-type"} )
public class BroadItemManager {
	
	// �z�Lkey���o�s�����~
	public static WeiBroadItem getBroadItemByKey(String key) {
		return GlobalVar.broaditem_map.get(key);
	}
		
	public static WeiBroadItem getBroadItemByItemStack(ItemStack item_stack) {
		// �p�G�ǤJ�����~null, �^��null
		if (item_stack == null) {return null;}
		
		// �p�G�ǤJ�����~ItemMeta�Ϊ̨S���W�r, �^��null
		if (!item_stack.hasItemMeta() || !item_stack.getItemMeta().hasDisplayName()) {return null;}
		
		// �^�ǳz�L�W�r��쪺���G
		return getBroadItemByDisplay(item_stack.getItemMeta().getDisplayName());
	}
	
	public static WeiBroadItem getBroadItemByDisplay(String display) {
		// �j��d����������~
		// �p�G�Υt�~�@��map�ӰO��<display,  key>�O�_����u? �O���骬�p?
		for (WeiBroadItem broad_item : GlobalVar.broaditem_map.values()) {
			
			// ��l��
			ItemStack item = null;
			
			// �p�Gnull�N���L
			if (broad_item == null || (item = broad_item.toItemStack()) == null) { continue; }
			
			// �p�G��o��ItemMeta�MDisplay
			if (!item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) { continue; }
			
			// ���o�ؼ�Display
			String item_display = item.getItemMeta().getDisplayName();
			
			if (item_display.equals(display)) {return broad_item;}
		}
		
		// �^�Ǫŭ�
		return null;
	}
	
	public static void setPlayerMessageInput(Player player, String key) {
		// ���o���a�W��
		String player_name = player.getName();
		
		// �p�G�O�]����J���A
		if (key != null) {
			if (!GlobalVar.playerInputMap.containsKey(player_name)) {
				GlobalVar.playerInputMap.put(player_name, key);
			}
		}
		// �p�G�O�]���D��J���A
		else {
			if (GlobalVar.playerInputMap.containsKey(player_name)) {
				GlobalVar.playerInputMap.remove(player_name);
			}		
		}
	}
	
	public static String isPlayerMessageInput(Player player) {
		// ���o���a�W��
		String player_name = player.getName();
		
		return GlobalVar.playerInputMap.get(player_name);
	}
	
	public static boolean consumeBroadItem(Player player, WeiBroadItem broad_item) {
		
		// ���o���~
		ItemStack item = broad_item.toItemStack();
		
		// �p�G�S��즨�~, �N���P�S������
		if (item == null) { return false; }
		
		// �����ծ��Ӥ⤤��
		ItemStack hand_item = player.getInventory().getItemInMainHand();
		
		if (hand_item != null && hand_item.hasItemMeta() && hand_item.getItemMeta().hasDisplayName()) {
			// �P�_�⤤���~�O�_�O�s�����~
			if (broad_item.equals(hand_item)) {
				hand_item.setAmount(hand_item.getAmount() - 1);
				return true;
			}
			
		}
		
		// �S�����, �q�]�]�j��}�l��
		for (ItemStack pack_item : player.getInventory().getContents()) {
			
			// �P�_���
			if (pack_item != null && pack_item.hasItemMeta() && pack_item.getItemMeta().hasDisplayName()) {
				
				// �P�_�]�]���Ӫ��~�O�_�O�s�����~
				if (broad_item.equals(pack_item)) {
					pack_item.setAmount(pack_item.getAmount() - 1);
					return true;
				}
				
			}			
		}
		
		return false;
	}
}
