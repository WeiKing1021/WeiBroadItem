package me.wei.broaditem.compiler;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.sucy.skill.api.player.PlayerData;

import me.wei.broaditem.GlobalVar;
import me.wei.broaditem.broad.BroadProcess;
import me.wei.broaditem.broad.items.WeiBroadItem;

@SuppressWarnings( {"static-access"} )
public class FormatCompiler {
	public static String transformValues(HashMap<Integer, String> formatMap, BroadProcess broad, int formatNum) {
		// ���o�����Ʀr���榡
		String format = formatMap.get(formatNum);
		
		// ���ը����o
		WeiBroadItem broad_item = broad.getBroadItem();
		if (broad_item != null) {
			String override_format = broad_item.getFormatOverrideMap().get(formatNum);
			
			// �p�G�s�b��g����, �N�Χ�g��
			if (override_format != null) { format = override_format; }
		}
		
		// ���o�򥻸��
		Player player = broad.getPlayer();
		
		format = format.replace("{PLAYER_NAME}", player.getName());
		format = format.replace("{PLAYER_DISPLAY}", player.getDisplayName());
		format = format.replace("{PLAYER_LEVEL}", String.valueOf(player.getLevel()));
		format = format.replace("{PLAYER_WORLD}", player.getWorld().getName());		
		
		// �ݦ��S��SkillAPI
		if (GlobalVar.skillapi != null) {
			PlayerData api_data = GlobalVar.skillapi.getPlayerData(player);
			
			// �d�ݸӪ��a��SkillAPI���, �P�_�O�_�֦�¾�~
			if (api_data != null && api_data.hasClass()) {
				format = format.replace("{SKILLAPI_LEVEL}", String.valueOf(api_data.getMainClass().getLevel()));
				format = format.replace("{SKILLAPI_CLASS}", api_data.getMainClass().getData().getName());
				format = format.replace("{SKILLAPI_CLASS_PREFIX}", api_data.getMainClass().getData().getPrefix());
			}
		}
		
		// �T���̫��, �]���]�t���a�ۤv��J����r, �p�G��J��L�ѼƴN�V�|�F!
		return format.replace("{MESSAGE}", broad.getMessage());
	}
}
