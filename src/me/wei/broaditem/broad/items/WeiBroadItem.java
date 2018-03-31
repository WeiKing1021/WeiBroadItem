package me.wei.broaditem.broad.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import me.wei.broaditem.broad.BroadType;

public abstract class WeiBroadItem {
	
	// ���~key
	public String key;
	
	// �s������
	public List<BroadType> broad_type = new ArrayList<>();
	
	// �榡��g
	public HashMap<Integer, String> formatOverrideMap = new HashMap<>(); 
	
	public void setBroadType(List<String> broad_type) {
		// �]�w�s������
		if (broad_type != null && broad_type.size() > 0) {
			for (String type : broad_type) {
				BroadType t = BroadType.valueOf(type);
				if (t != null) { this.broad_type.add(t); }
			}
		}	
	}
	
	public void setFormatOverrideMap(ConfigurationSection section) {
		// �j��[�J�榡��g
		ConfigurationSection format_section = section.getConfigurationSection("FormatOverride");
		Set<String> keys = null;
		if (format_section != null && (keys = format_section.getKeys(false)) != null) {
			for (String s : keys) {
				int n = Integer.valueOf(s);
				this.formatOverrideMap.put(n, format_section.getString(s));
			}
		}		
	}
	
	public HashMap<Integer, String> getFormatOverrideMap() {
		HashMap<Integer, String> override_format = new HashMap<>();
		
		HashMap<Integer, String> map = this.formatOverrideMap;
		
		// �p�G������, �N�NBroadItem���~����g�榡�[�J��^�Ǹ�Ƥ�
		if (map != null && map.size() > 0) { override_format.putAll(map); }
		
		return override_format;
	}
	
	// ��gequals()��k, �ۭq���~�P�_�覡
	@Override
	public boolean equals(Object obj) {
		
		// �p�G��Object���ŭ�, �P�_�����P���~
		if (obj == null) {return false;}
		
		// �p�G��Object�ä��OItemStack�����, �P�_�����P���~
		if ( !(obj instanceof ItemStack) ) {return false;}
		
		// �૬
		ItemStack item = (ItemStack)obj;
		
		// ���s�����~
		ItemStack result = this.toItemStack();
		
		// �p�G��쪺�s�����~�O�ŭ�, �P�_�����P���~
		if (result == null) {return false;}
		
		// �p�G�Ѽƪ��~�M�s�����~ID, SubID���P, �P�_�����P���~
		if (item.getType() != result.getType() || item.getDurability() != result.getDurability()) {return false;}
		
		// �p�G�Ѽƪ��~�S���۩w�q�W��, �P�_�����P���~
		if (!item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) {return false;}
		
		// �p�G�s�����~�S���۩w�q�W��, �P�_�����P���~
		if (!result.hasItemMeta() || !item.getItemMeta().hasDisplayName()) {return false;}
		
		// �p�G�Ѽƪ��~�M�s�����~�W�٤��P, �P�_�����P���~
		if (!item.getItemMeta().getDisplayName().equals(result.getItemMeta().getDisplayName())) {return false;}
		
		// �ݰ_�Ӥ@�˩O!
		return true;
	}
	
	public abstract ItemStack toItemStack();
}
