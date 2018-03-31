package me.wei.broaditem;

import java.util.logging.Level;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.wei.broaditem.command.AdminCommand;
import me.wei.broaditem.listeners.ListenerManager;

@SuppressWarnings( {"static-access"} )
public class PluginCore extends JavaPlugin{
	
	public static PluginCore instance;
	
	@Override
	public void onEnable() {
		
		// 判斷插件是否重複載入
		if (this.instance != null) {
			Manager.log("插件重複載入!", Level.WARNING);
			return;
		}
		
		// 實體建立
		this.instance = this;

		// 指令執行類別
		this.getCommand("weibroaditem").setExecutor(new AdminCommand());
		
		// 載入設定檔
		Manager.loadConfig();
		
		// 尋找插件
		Plugin plugin;
		
		// KyCraft在哪裡~~ 在勇者大哥的文章裡~~
		plugin = Manager.getServer().getPluginManager().getPlugin("Kycraft");
		if (plugin != null && plugin instanceof com.kunyihua.crafte.Main) {
			GlobalVar.kycraft_api = ((com.kunyihua.crafte.Main)plugin).getAPI();
		}
		
		// WeiBroadApi在哪裡~~ 沒人想裝這種東西啦! 
		plugin = Manager.getServer().getPluginManager().getPlugin("WeiBroadApi");
		if (plugin != null && plugin instanceof me.wei.broadapi.WeiBroadApi) {
			GlobalVar.broad_api = ((me.wei.broadapi.WeiBroadApi)plugin).getAPI();
		}
		
		// SkillApi在哪裡~~ 這麼強大的插件趕快裝啦!
		plugin = Manager.getServer().getPluginManager().getPlugin("SkillAPI");
		if (plugin != null && plugin instanceof com.sucy.skill.SkillAPI) {
			GlobalVar.skillapi = ((com.sucy.skill.SkillAPI)plugin);
		}
		
		// 註冊監聽者
		ListenerManager.registerAll();
		
		// 啟動定時器
		GlobalVar.broad_process_runnable.start();
		
		// 載入結束
		Manager.log("插件載入完成!");
		
		// 顯示資訊
		if (GlobalVar.info) {
			Manager.colorLog("§7=====================================================================");
			Manager.colorLog("§6感謝使用本插件, 若§a不想§6顯示此區域作者資訊,");
			Manager.colorLog("§6請至設定中將§aLoadInfo§6設定為§afalse§6即可");
			Manager.colorLog("§6如有任何關於此插件的問題或建議, 可用§a巴哈信箱§6告知, 感謝^^");
			Manager.colorLog("");
			Manager.colorLog("§6插件作者: §d魏丁");
			Manager.colorLog("§6巴哈小屋: §9https://home.gamer.com.tw/homeindex.php?owner=WeiKing1021");
			Manager.colorLog("§6RC語音: zmalqp98");
			Manager.colorLog("§7=====================================================================");
		}
	}
	
	@Override
	public void onDisable() {
		// 載入結束
		Manager.log("插件卸載完成!");
	}
}
