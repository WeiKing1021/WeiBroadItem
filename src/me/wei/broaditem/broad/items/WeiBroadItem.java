package me.wei.broaditem.broad.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import me.wei.broaditem.broad.BroadType;

public abstract class WeiBroadItem {
	
	// 物品key
	public String key;
	
	// 廣播類型
	public List<BroadType> broad_type = new ArrayList<>();
	
	// 格式改寫
	public HashMap<Integer, String> formatOverrideMap = new HashMap<>(); 
	
	public void setBroadType(List<String> broad_type) {
		// 設定廣播類型
		if (broad_type != null && broad_type.size() > 0) {
			for (String type : broad_type) {
				BroadType t = BroadType.valueOf(type);
				if (t != null) { this.broad_type.add(t); }
			}
		}	
	}
	
	public void setFormatOverrideMap(ConfigurationSection section) {
		// 迴圈加入格式改寫
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
		
		// 如果不為空, 就將BroadItem物品的改寫格式加入到回傳資料中
		if (map != null && map.size() > 0) { override_format.putAll(map); }
		
		return override_format;
	}
	
	// 改寫equals()方法, 自訂物品判斷方式
	@Override
	public boolean equals(Object obj) {
		
		// 如果該Object為空值, 判斷為不同物品
		if (obj == null) {return false;}
		
		// 如果該Object並不是ItemStack的實例, 判斷為不同物品
		if ( !(obj instanceof ItemStack) ) {return false;}
		
		// 轉型
		ItemStack item = (ItemStack)obj;
		
		// 取廣播物品
		ItemStack result = this.toItemStack();
		
		// 如果抓到的廣播物品是空值, 判斷為不同物品
		if (result == null) {return false;}
		
		// 如果參數物品和廣播物品ID, SubID不同, 判斷為不同物品
		if (item.getType() != result.getType() || item.getDurability() != result.getDurability()) {return false;}
		
		// 如果參數物品沒有自定義名稱, 判斷為不同物品
		if (!item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) {return false;}
		
		// 如果廣播物品沒有自定義名稱, 判斷為不同物品
		if (!result.hasItemMeta() || !item.getItemMeta().hasDisplayName()) {return false;}
		
		// 如果參數物品和廣播物品名稱不同, 判斷為不同物品
		if (!item.getItemMeta().getDisplayName().equals(result.getItemMeta().getDisplayName())) {return false;}
		
		// 看起來一樣呢!
		return true;
	}
	
	public abstract ItemStack toItemStack();
}
