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
	
	// 結果物件
	private ItemStack result_item;

	// 建構子
	public MyWeiBroadItem(String key, ConfigurationSection section) {
		
		// 設定key
		this.key = key;
		
		// 設定物品廣播類型
		this.setBroadType(section.getStringList("BroadType"));
		
		// 設定物品的ItemStack
		int id = section.getInt("ID", 1);
		this.result_item = new ItemStack(Material.getMaterial(id));
		
		// 設定子ID
		short data = (short)section.getInt("Data", 0);
		result_item.setDurability(data);
		
		// 取得ItemMeta
		ItemMeta item_meta = result_item.getItemMeta();
		
		// 設定物品名稱
		String display = section.getString("Display", "§f魏丁的法國號");
		item_meta.setDisplayName(display);
		
		// 設定說明文字
		List<String> lores = section.getStringList("Lores");
		List<String> lst = new ArrayList<>();
		if (lores != null && lores.size() > 0) {
			for (String s : lores) { lst.add(s); }
		}
		item_meta.setLore(lst);
		
		// 迴圈加入附魔
		List<String> enchantments = section.getStringList("Enchantments");
		if (enchantments != null && enchantments.size() > 0)
			for (String str : enchantments) {
				// 分隔
				String[] strs = str.split(":");
			
				// 附魔代碼
				String enc = strs[0];
			
				// 附魔等級
				int level = (strs.length > 1 ? Integer.valueOf(strs[1]) : 1);
			
				// 嘗試取得附魔
				Enchantment e = Enchantment.getByName(enc);
			
				// 判斷是否有取得到
				if (e != null) {
					// 將Item_meta加入附魔
					item_meta.addEnchant(e, level, true);
			};
		}
		
		// 設定廣播格式改寫
		this.setFormatOverrideMap(section);
		
		// 將ItemMeta設定回ItemStack中
		result_item.setItemMeta(item_meta);
	}

	// 取得發送列表
	public List<BroadType> getBroadTypeList() {
		return this.broad_type;
	}
	
	public ItemStack toItemStack() {
		// 複製一個
		// org.bukkit.inventory.ItemStack 已實作 Cloneable
		ItemStack item = this.result_item.clone();
		
		// 回傳
		return item;
	}
}
