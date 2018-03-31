package me.wei.broaditem.broad.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.wei.broaditem.broad.BroadType;

@SuppressWarnings( {"deprecation"} )
public class MyWeiBroadItem extends WeiBroadItem {		
	
	// ���G����
	private ItemStack result_item;

	// �غc�l
	public MyWeiBroadItem(String key, ConfigurationSection section) {
		
		// �]�wkey
		this.key = key;
		
		// �]�w���~�s������
		this.setBroadType(section.getStringList("BroadType"));
		
		// �]�w���~��ItemStack
		int id = section.getInt("ID", 1);
		this.result_item = new ItemStack(Material.getMaterial(id));
		
		// �]�w�lID
		short data = (short)section.getInt("Data", 0);
		result_item.setDurability(data);
		
		// ���oItemMeta
		ItemMeta item_meta = result_item.getItemMeta();
		
		// �]�w���~�W��
		String display = section.getString("Display", "��f�Q�B���k�긹");
		item_meta.setDisplayName(display);
		
		// �]�w������r
		List<String> lores = section.getStringList("Lores");
		List<String> lst = new ArrayList<>();
		if (lores != null && lores.size() > 0) {
			for (String s : lores) { lst.add(s); }
		}
		item_meta.setLore(lst);
		
		// �j��[�J���]
		List<String> enchantments = section.getStringList("Enchantments");
		if (enchantments != null && enchantments.size() > 0)
			for (String str : enchantments) {
				// ���j
				String[] strs = str.split(":");
			
				// ���]�N�X
				String enc = strs[0];
			
				// ���]����
				int level = (strs.length > 1 ? Integer.valueOf(strs[1]) : 1);
			
				// ���ը��o���]
				Enchantment e = Enchantment.getByName(enc);
			
				// �P�_�O�_�����o��
				if (e != null) {
					// �NItem_meta�[�J���]
					item_meta.addEnchant(e, level, true);
			};
		}
		
		// �]�w�s���榡��g
		this.setFormatOverrideMap(section);
		
		// �NItemMeta�]�w�^ItemStack��
		result_item.setItemMeta(item_meta);
	}

	// ���o�o�e�C��
	public List<BroadType> getBroadTypeList() {
		return this.broad_type;
	}
	
	public ItemStack toItemStack() {
		// �ƻs�@��
		// org.bukkit.inventory.ItemStack �w��@ Cloneable
		ItemStack item = this.result_item.clone();
		
		// �^��
		return item;
	}
}
