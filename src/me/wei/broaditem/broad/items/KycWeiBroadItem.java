package me.wei.broaditem.broad.items;

import java.util.logging.Level;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import me.wei.broaditem.GlobalVar;
import me.wei.broaditem.Manager;

public class KycWeiBroadItem extends WeiBroadItem{
	
	public KycWeiBroadItem(String key, ConfigurationSection section) {
		// �]�w����r
		this.key = key;
		
		// �]�w���~�s������
		this.setBroadType(section.getStringList("BroadType"));
		
		// �]�w�s���榡��g
		this.setFormatOverrideMap(section);
	}
	
	@Override
	public ItemStack toItemStack() {
		
		// �P�_KyCraft�O�_�����J
		if (GlobalVar.kycraft_api == null) {
			Manager.log("��������KyCraft����!", Level.WARNING);
			return null;
		}
		
		// �qKyCraftAPI�����o�Ӫ��~
		return GlobalVar.kycraft_api.getKycAPI().getItemByItemKey(this.key);
	}

}
