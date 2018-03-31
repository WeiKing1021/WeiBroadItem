package me.wei.broaditem;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

import com.kunyihua.crafte.api.KycraftAPI;
import com.sucy.skill.SkillAPI;

import me.wei.broadapi.BroadApi;
import me.wei.broaditem.broad.BroadProcess;
import me.wei.broaditem.broad.BroadProcessRunnable;
import me.wei.broaditem.broad.BroadType;
import me.wei.broaditem.broad.classes.*;
import me.wei.broaditem.broad.items.WeiBroadItem;

@SuppressWarnings( {"unchecked", "rawtypes", "serial"} )
public class GlobalVar {
	// 前墜
	public static final String regx = "§d[§eWeiBroadItem§d] §6";
	
	// 插件路徑
	public static String folder = PluginCore.instance.getDataFolder().getAbsolutePath();
	
	// 是否顯示作者親切小提醒
	public static boolean info = true;
	
	// 支援KyCraft
	public static KycraftAPI kycraft_api = null;
	
	// 支援BroadApi
	public static BroadApi broad_api = null;
	
	// SkillAPI
	public static SkillAPI skillapi = null;
	
	// BossBar
	public static BossBar bossbar = Bukkit.createBossBar("", BarColor.BLUE, BarStyle.SEGMENTED_20);
	
	// 廣播格式列表
	public static HashMap<BroadType, BroadClass> broad_map = new HashMap() {{
		this.put(BroadType.Message, new MessageBroad());
		this.put(BroadType.ActionBar, new ActionBarBroad());
		this.put(BroadType.Title, new TitleBroad());
		this.put(BroadType.BossBar, new BossBarBroad());
		this.put(BroadType.ApiPush, new ApiPushBroad());
	}};
	
	// 管理員的詞彙
	public static String admin_name = "管理員";
	
	// 物品列表
	public static HashMap<String, WeiBroadItem> broaditem_map = new HashMap<>();
	
	// 廣播處理程序
	public static BroadProcessRunnable broad_process_runnable = new BroadProcessRunnable();
	
	// 等待執行的處理列表
	public static List<BroadProcess> lstBroadProcess = new CopyOnWriteArrayList<BroadProcess>();
	
	// 玩家正要輸入訊息的列表
	public static HashMap<String, String> playerInputMap = new HashMap<>();
}
