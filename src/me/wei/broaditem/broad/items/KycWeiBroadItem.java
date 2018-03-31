package me.wei.broaditem.broad.items;

import java.util.logging.Level;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import me.wei.broaditem.GlobalVar;
import me.wei.broaditem.Manager;

public class KycWeiBroadItem extends WeiBroadItem{
	
	public KycWeiBroadItem(String key, ConfigurationSection section) {
		// 設定關鍵字
		this.key = key;
		
		// 設定物品廣播類型
		this.setBroadType(section.getStringList("BroadType"));
		
		// 設定廣播格式改寫
		this.setFormatOverrideMap(section);
	}
	
	@Override
	public ItemStack toItemStack() {
		
		// 判斷KyCraft是否有載入
		if (GlobalVar.kycraft_api == null) {
			Manager.log("未偵測到KyCraft插件!", Level.WARNING);
			return null;
		}
		
		// 從KyCraftAPI中取得該物品
		return GlobalVar.kycraft_api.getKycAPI().getItemByItemKey(this.key);
	}

}
