package me.wei.broaditem.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.configuration.file.YamlConfiguration;

import me.wei.broaditem.GlobalVar;
import me.wei.broaditem.Manager;
import me.wei.broaditem.PluginCore;

public class IOManager {
	
	private static int buf_size = 1024;
	
	public static void load(IOInterface io) {
		try {
			// 載入設定檔
			io.loadBroadItem();
			io.loadBroadFormat();
		}
		catch (IOException e) {
			Manager.log(e.toString(), Level.WARNING);
		}
		catch (SQLException e) {
			Manager.log(e.toString(), Level.WARNING);
		}		
	}
	
	public static void save(IOInterface io) {
		try {
			// 載入設定檔
			io.saveBroadItem();
			io.saveBroadFormat();
		}
		catch (IOException e) {
			Manager.log(e.toString(), Level.WARNING);
		}
		catch (SQLException e) {
			Manager.log(e.toString(), Level.WARNING);
		}	
	}
	
	public static void copyDefault(String path) throws IOException {
		// 處理左斜線右斜線問題
		path = path.replace("\\", "/");
		
		// 假設不存在就從jar中提出並儲存
		InputStream Input = PluginCore.class.getResourceAsStream("/defaultConfig" + path);
		File target = new File(GlobalVar.folder + path);
		
		// 建立目錄
		if (!target.exists()) {
			new File(target.getParent()).mkdirs();
			target.createNewFile();
		}
		
		// 輸出流
		OutputStream Output = new FileOutputStream(target);
		
		// 緩衝區長度
		byte[] buf = new byte[buf_size];
		int len;
		
		// 不斷讀取
		while ( (len = Input.read(buf)) > 0) {
			Output.write(buf, 0, len);
		}
		
		// 關閉IO流
		Input.close();
		Output.close();
		
		// 告知說已經提從jar中提取了檔案
		Manager.log("已提取檔案: " + path);
	};
	
	public static YamlConfiguration getYamlConfig(String path) {
		// 先取得目錄
		File file = new File(GlobalVar.folder + path);		
		
		// 回傳
		return getYamlConfig(file);
	}
	
	public static YamlConfiguration getYamlConfig(File file) {	
		// 假設不存在就從jar中提出並儲存
		if (!file.exists()) {
			
			String path = file.getAbsolutePath().replace(GlobalVar.folder, "");
			
			// 嘗試複製
			try { copyDefault(path); }
			catch (IOException e) {
				Manager.log(path + " 無法儲存, 原因為: " + e.toString(), Level.WARNING);
				return null;
			}
		}
		
		// 回傳
		return YamlConfiguration.loadConfiguration(file);
	}
}
