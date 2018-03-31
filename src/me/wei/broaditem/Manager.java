package me.wei.broaditem;

import java.util.logging.Level;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import me.wei.broaditem.io.IOInterface;
import me.wei.broaditem.io.IOManager;
import me.wei.broaditem.io.SqlIO;
import me.wei.broaditem.io.YmlIO;
import me.wei.broaditem.lang.Lang;

public class Manager {
	
	// 控制台輸出者
	private static ConsoleCommandSender console_sender = PluginCore.instance.getServer().getConsoleSender();
	
	public static void colorLog(String strMes) {
		send(console_sender, strMes, false);
	}
	
	// 輸出
	public static void log(String strLog) {
		log(strLog, Level.INFO);
	}

	// 有輸出等級的輸出
	public static void log(String strLog, Level level) {
		PluginCore.instance.getLogger().log(level, strLog);
	}
	
	public static void reloadConfig() {
		// 清除
		GlobalVar.broaditem_map.clear();
		Lang.reset();
		
		// 載入
		loadConfig();
	}
	
	// 載入基本設定檔
	public static void loadConfig() {
		// 讀取
		String main_path = "/config.yml";
		YamlConfiguration main_config = IOManager.getYamlConfig(main_path);
		
		// 看是否有讀到
		if (main_config == null) {
			// 告知錯誤
			log("無法載入設定檔!(" + main_path + ")", Level.WARNING);
			return;	
		}
		
		// 載入資訊
		GlobalVar.info = main_config.getBoolean("LoadInfo", true);
		
		// 載入方式
		String load_type = main_config.getString("LoadType", "yml").toLowerCase();
		
		// 語言設定檔
		String lang_type = main_config.getString("LangType", "zh-tw").toLowerCase();
		
		// 讀取文字
		String lang_path = "/lang/" + lang_type + ".yml";
		YamlConfiguration lang_config = IOManager.getYamlConfig(lang_path);
		
		// 看是否有讀到
		if (lang_config == null) {
			// 告知錯誤
			log("無法載入設定檔!(" + lang_path + ")", Level.WARNING);	
		}
		else {
			// 迴圈賭取
			for (String lang_key : lang_config.getKeys(false)) {
				// 讀取文字
				Lang.setLang(lang_key, lang_config.getString(lang_key, ""));
			}
		}

		// 判斷使用何種方式載入
		IOInterface loader = null;
		switch(load_type) {
			case "yml" :
				loader = new YmlIO();
				break;
			case "sql" :
				// 讀取Sql設定
				ConfigurationSection sql_config = main_config.getConfigurationSection("SqlSetting");
				
				// 資料
				String sql_host = sql_config.getString("Host", "localhost");
				int sql_port = sql_config.getInt("Port", 3306);
				String sql_dataBase = sql_config.getString("DataBase", "test");
				String sql_user = sql_config.getString("User", "root");
				String sql_password = sql_config.getString("Password", "");
				
				// 建構
				loader = new SqlIO(sql_host, sql_port, sql_dataBase, sql_user, sql_password);
				break;
			default :
				loader = new YmlIO();
		}
		
		// 載入物品
		IOManager.load(loader);
	}
	
	// 訊息傳送
	public static void send(CommandSender sender, String strMessage) {
		send(sender, strMessage, true);
	}
	
	public static void send(CommandSender sender, String strMessage, boolean regx) {
		
		String str = (regx ? GlobalVar.regx : "") + strMessage;
		
		sender.sendMessage(str);
	}
	
	// 呼叫事件
	public static void callEvent(Event event) {
		getServer().getPluginManager().callEvent(event);
	}
	
	// 註冊事件
	public static void registerListener(Listener listener) {
		getServer().getPluginManager().registerEvents(listener, PluginCore.instance);
	}
	
	// 取得伺服
	public static Server getServer() {
		if (PluginCore.instance != null) { return PluginCore.instance.getServer(); }
		return null;
	}
}
