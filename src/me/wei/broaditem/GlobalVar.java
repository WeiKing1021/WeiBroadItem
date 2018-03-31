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
	// �e�Y
	public static final String regx = "��d[��eWeiBroadItem��d] ��6";
	
	// ������|
	public static String folder = PluginCore.instance.getDataFolder().getAbsolutePath();
	
	// �O�_��ܧ@�̿ˤ��p����
	public static boolean info = true;
	
	// �䴩KyCraft
	public static KycraftAPI kycraft_api = null;
	
	// �䴩BroadApi
	public static BroadApi broad_api = null;
	
	// SkillAPI
	public static SkillAPI skillapi = null;
	
	// BossBar
	public static BossBar bossbar = Bukkit.createBossBar("", BarColor.BLUE, BarStyle.SEGMENTED_20);
	
	// �s���榡�C��
	public static HashMap<BroadType, BroadClass> broad_map = new HashMap() {{
		this.put(BroadType.Message, new MessageBroad());
		this.put(BroadType.ActionBar, new ActionBarBroad());
		this.put(BroadType.Title, new TitleBroad());
		this.put(BroadType.BossBar, new BossBarBroad());
		this.put(BroadType.ApiPush, new ApiPushBroad());
	}};
	
	// �޲z�������J
	public static String admin_name = "�޲z��";
	
	// ���~�C��
	public static HashMap<String, WeiBroadItem> broaditem_map = new HashMap<>();
	
	// �s���B�z�{��
	public static BroadProcessRunnable broad_process_runnable = new BroadProcessRunnable();
	
	// ���ݰ��檺�B�z�C��
	public static List<BroadProcess> lstBroadProcess = new CopyOnWriteArrayList<BroadProcess>();
	
	// ���a���n��J�T�����C��
	public static HashMap<String, String> playerInputMap = new HashMap<>();
}
