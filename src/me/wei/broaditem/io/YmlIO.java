package me.wei.broaditem.io;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.configuration.file.YamlConfiguration;

import me.wei.broaditem.GlobalVar;
import me.wei.broaditem.broad.BroadType;
import me.wei.broaditem.broad.classes.BroadClass;
import me.wei.broaditem.broad.items.KycWeiBroadItem;
import me.wei.broaditem.broad.items.MyWeiBroadItem;

public class YmlIO implements IOInterface{
	
	// 基準
	public String root = "/yml";

	@Override
	public void loadBroadItem() throws IOException, SQLException {
		// 目標
		String path = root + "/broad_item.yml";
		File file = new File(GlobalVar.folder + path);	

		// 載入設定檔
		YamlConfiguration item_config = IOManager.getYamlConfig(file);
;
		// 迴圈讀取物品
		for (String key : item_config.getKeys(false)) {
			MyWeiBroadItem broad_item = new MyWeiBroadItem(key, item_config.getConfigurationSection(key));
			
			// 放置到map中
			GlobalVar.broaditem_map.put(key, broad_item);
		}

		// 目標
		String kyc = root + "/broad_kycitem.yml";
		File kyc_file = new File(GlobalVar.folder + kyc);	

		// 載入設定檔
		YamlConfiguration kycitem_config = IOManager.getYamlConfig(kyc_file);

		// 迴圈讀取物品
		for (String key : kycitem_config.getKeys(false)) {
			KycWeiBroadItem broad_kycitem = new KycWeiBroadItem(key, kycitem_config.getConfigurationSection(key));
			
			// 放置到map中
			GlobalVar.broaditem_map.put(key, broad_kycitem);
		}
	}

	@Override
	public void loadBroadFormat() {
		// 目標
		String path = root + "/broad_format.yml";
		File file = new File(GlobalVar.folder + path);	
	
		// 載入設定檔
		YamlConfiguration format_config = IOManager.getYamlConfig(file);

		// 迴圈讀取物品
		for (String key : format_config.getKeys(false)) {
			// 尋找廣播類型
			BroadType type = BroadType.valueOf(key);

			// 如果沒找到就跳過
			if (type == null) {continue;}
			
			// 找廣播類別
			BroadClass broad_class = GlobalVar.broad_map.get(type);
			
			// 如果沒找到就跳過
			if (broad_class == null) {continue;}
			
			// 設定格式
			broad_class.setFormat(format_config.getConfigurationSection(key));
		}
	}

	@Override
	public void saveBroadItem() {
	}

	@Override
	public void saveBroadFormat() {
	}
}
