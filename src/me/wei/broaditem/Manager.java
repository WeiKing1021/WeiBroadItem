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
	
	// ����x��X��
	private static ConsoleCommandSender console_sender = PluginCore.instance.getServer().getConsoleSender();
	
	public static void colorLog(String strMes) {
		send(console_sender, strMes, false);
	}
	
	// ��X
	public static void log(String strLog) {
		log(strLog, Level.INFO);
	}

	// ����X���Ū���X
	public static void log(String strLog, Level level) {
		PluginCore.instance.getLogger().log(level, strLog);
	}
	
	public static void reloadConfig() {
		// �M��
		GlobalVar.broaditem_map.clear();
		Lang.reset();
		
		// ���J
		loadConfig();
	}
	
	// ���J�򥻳]�w��
	public static void loadConfig() {
		// Ū��
		String main_path = "/config.yml";
		YamlConfiguration main_config = IOManager.getYamlConfig(main_path);
		
		// �ݬO�_��Ū��
		if (main_config == null) {
			// �i�����~
			log("�L�k���J�]�w��!(" + main_path + ")", Level.WARNING);
			return;	
		}
		
		// ���J��T
		GlobalVar.info = main_config.getBoolean("LoadInfo", true);
		
		// ���J�覡
		String load_type = main_config.getString("LoadType", "yml").toLowerCase();
		
		// �y���]�w��
		String lang_type = main_config.getString("LangType", "zh-tw").toLowerCase();
		
		// Ū����r
		String lang_path = "/lang/" + lang_type + ".yml";
		YamlConfiguration lang_config = IOManager.getYamlConfig(lang_path);
		
		// �ݬO�_��Ū��
		if (lang_config == null) {
			// �i�����~
			log("�L�k���J�]�w��!(" + lang_path + ")", Level.WARNING);	
		}
		else {
			// �j����
			for (String lang_key : lang_config.getKeys(false)) {
				// Ū����r
				Lang.setLang(lang_key, lang_config.getString(lang_key, ""));
			}
		}

		// �P�_�ϥΦ�ؤ覡���J
		IOInterface loader = null;
		switch(load_type) {
			case "yml" :
				loader = new YmlIO();
				break;
			case "sql" :
				// Ū��Sql�]�w
				ConfigurationSection sql_config = main_config.getConfigurationSection("SqlSetting");
				
				// ���
				String sql_host = sql_config.getString("Host", "localhost");
				int sql_port = sql_config.getInt("Port", 3306);
				String sql_dataBase = sql_config.getString("DataBase", "test");
				String sql_user = sql_config.getString("User", "root");
				String sql_password = sql_config.getString("Password", "");
				
				// �غc
				loader = new SqlIO(sql_host, sql_port, sql_dataBase, sql_user, sql_password);
				break;
			default :
				loader = new YmlIO();
		}
		
		// ���J���~
		IOManager.load(loader);
	}
	
	// �T���ǰe
	public static void send(CommandSender sender, String strMessage) {
		send(sender, strMessage, true);
	}
	
	public static void send(CommandSender sender, String strMessage, boolean regx) {
		
		String str = (regx ? GlobalVar.regx : "") + strMessage;
		
		sender.sendMessage(str);
	}
	
	// �I�s�ƥ�
	public static void callEvent(Event event) {
		getServer().getPluginManager().callEvent(event);
	}
	
	// ���U�ƥ�
	public static void registerListener(Listener listener) {
		getServer().getPluginManager().registerEvents(listener, PluginCore.instance);
	}
	
	// ���o���A
	public static Server getServer() {
		if (PluginCore.instance != null) { return PluginCore.instance.getServer(); }
		return null;
	}
}
