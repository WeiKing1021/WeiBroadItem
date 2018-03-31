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
		
		// �P�_����O�_���Ƹ��J
		if (this.instance != null) {
			Manager.log("���󭫽Ƹ��J!", Level.WARNING);
			return;
		}
		
		// ����إ�
		this.instance = this;

		// ���O�������O
		this.getCommand("weibroaditem").setExecutor(new AdminCommand());
		
		// ���J�]�w��
		Manager.loadConfig();
		
		// �M�䴡��
		Plugin plugin;
		
		// KyCraft�b����~~ �b�i�̤j�����峹��~~
		plugin = Manager.getServer().getPluginManager().getPlugin("Kycraft");
		if (plugin != null && plugin instanceof com.kunyihua.crafte.Main) {
			GlobalVar.kycraft_api = ((com.kunyihua.crafte.Main)plugin).getAPI();
		}
		
		// WeiBroadApi�b����~~ �S�H�Q�˳o�تF���! 
		plugin = Manager.getServer().getPluginManager().getPlugin("WeiBroadApi");
		if (plugin != null && plugin instanceof me.wei.broadapi.WeiBroadApi) {
			GlobalVar.broad_api = ((me.wei.broadapi.WeiBroadApi)plugin).getAPI();
		}
		
		// SkillApi�b����~~ �o��j�j�����󻰧ָ˰�!
		plugin = Manager.getServer().getPluginManager().getPlugin("SkillAPI");
		if (plugin != null && plugin instanceof com.sucy.skill.SkillAPI) {
			GlobalVar.skillapi = ((com.sucy.skill.SkillAPI)plugin);
		}
		
		// ���U��ť��
		ListenerManager.registerAll();
		
		// �Ұʩw�ɾ�
		GlobalVar.broad_process_runnable.start();
		
		// ���J����
		Manager.log("������J����!");
		
		// ��ܸ�T
		if (GlobalVar.info) {
			Manager.colorLog("��7=====================================================================");
			Manager.colorLog("��6�P�¨ϥΥ�����, �Y��a���Q��6��ܦ��ϰ�@�̸�T,");
			Manager.colorLog("��6�Цܳ]�w���N��aLoadInfo��6�]�w����afalse��6�Y�i");
			Manager.colorLog("��6�p���������󦹴��󪺰��D�Ϋ�ĳ, �i�Ρ�a�ګ��H�c��6�i��, �P��^^");
			Manager.colorLog("");
			Manager.colorLog("��6����@��: ��d�Q�B");
			Manager.colorLog("��6�ګ��p��: ��9https://home.gamer.com.tw/homeindex.php?owner=WeiKing1021");
			Manager.colorLog("��6RC�y��: zmalqp98");
			Manager.colorLog("��7=====================================================================");
		}
	}
	
	@Override
	public void onDisable() {
		// ���J����
		Manager.log("�����������!");
	}
}
