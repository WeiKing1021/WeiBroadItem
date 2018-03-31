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
	
	// ���
	public String root = "/yml";

	@Override
	public void loadBroadItem() throws IOException, SQLException {
		// �ؼ�
		String path = root + "/broad_item.yml";
		File file = new File(GlobalVar.folder + path);	

		// ���J�]�w��
		YamlConfiguration item_config = IOManager.getYamlConfig(file);
;
		// �j��Ū�����~
		for (String key : item_config.getKeys(false)) {
			MyWeiBroadItem broad_item = new MyWeiBroadItem(key, item_config.getConfigurationSection(key));
			
			// ��m��map��
			GlobalVar.broaditem_map.put(key, broad_item);
		}

		// �ؼ�
		String kyc = root + "/broad_kycitem.yml";
		File kyc_file = new File(GlobalVar.folder + kyc);	

		// ���J�]�w��
		YamlConfiguration kycitem_config = IOManager.getYamlConfig(kyc_file);

		// �j��Ū�����~
		for (String key : kycitem_config.getKeys(false)) {
			KycWeiBroadItem broad_kycitem = new KycWeiBroadItem(key, kycitem_config.getConfigurationSection(key));
			
			// ��m��map��
			GlobalVar.broaditem_map.put(key, broad_kycitem);
		}
	}

	@Override
	public void loadBroadFormat() {
		// �ؼ�
		String path = root + "/broad_format.yml";
		File file = new File(GlobalVar.folder + path);	
	
		// ���J�]�w��
		YamlConfiguration format_config = IOManager.getYamlConfig(file);

		// �j��Ū�����~
		for (String key : format_config.getKeys(false)) {
			// �M��s������
			BroadType type = BroadType.valueOf(key);

			// �p�G�S���N���L
			if (type == null) {continue;}
			
			// ��s�����O
			BroadClass broad_class = GlobalVar.broad_map.get(type);
			
			// �p�G�S���N���L
			if (broad_class == null) {continue;}
			
			// �]�w�榡
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
