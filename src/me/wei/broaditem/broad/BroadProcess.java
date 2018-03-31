package me.wei.broaditem.broad;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.wei.broaditem.BroadItemManager;
import me.wei.broaditem.broad.items.WeiBroadItem;

public class BroadProcess {
	
	// ���a
	private Player player;
	
	// ��l�T��
	private String mes;
	
	// �s���D�㪺�N�X
	private String broad_item_key;
	
	// �غc�l
	public BroadProcess(Player player, String mes, String broad_item_key) {
		this.player = player;
		this.mes = mes;
		this.broad_item_key = broad_item_key;
	}
	
	// ���o���a
	public Player getPlayer() {
		return this.player;
	}
	
	// ���o��l�T��
	public String getMessage() {
		return this.mes;
	}
	
	// ���o�s���Ϊ��D��
	public WeiBroadItem getBroadItem() {
		return BroadItemManager.getBroadItemByKey(broad_item_key);
	}
	
	// ���o�s������
	public List<BroadType> getBroadType() {
		List<BroadType> lst = new ArrayList<>();
		
		List<BroadType> broad_type = getBroadItem().broad_type;

		// �p�G�s���D�㦳����s������
		if (broad_type != null && broad_type.size() > 0) {
			lst.addAll(broad_type);
		}

		return lst;
	}
}
