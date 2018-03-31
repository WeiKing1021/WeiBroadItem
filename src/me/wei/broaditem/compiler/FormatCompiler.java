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
		// 取得對應數字的格式
		String format = formatMap.get(formatNum);
		
		// 嘗試取取得
		WeiBroadItem broad_item = broad.getBroadItem();
		if (broad_item != null) {
			String override_format = broad_item.getFormatOverrideMap().get(formatNum);
			
			// 如果存在改寫條目, 就用改寫的
			if (override_format != null) { format = override_format; }
		}
		
		// 取得基本資料
		Player player = broad.getPlayer();
		
		format = format.replace("{PLAYER_NAME}", player.getName());
		format = format.replace("{PLAYER_DISPLAY}", player.getDisplayName());
		format = format.replace("{PLAYER_LEVEL}", String.valueOf(player.getLevel()));
		format = format.replace("{PLAYER_WORLD}", player.getWorld().getName());		
		
		// 看有沒有SkillAPI
		if (GlobalVar.skillapi != null) {
			PlayerData api_data = GlobalVar.skillapi.getPlayerData(player);
			
			// 查看該玩家的SkillAPI資料, 判斷是否擁有職業
			if (api_data != null && api_data.hasClass()) {
				format = format.replace("{SKILLAPI_LEVEL}", String.valueOf(api_data.getMainClass().getLevel()));
				format = format.replace("{SKILLAPI_CLASS}", api_data.getMainClass().getData().getName());
				format = format.replace("{SKILLAPI_CLASS_PREFIX}", api_data.getMainClass().getData().getPrefix());
			}
		}
		
		// 訊息最後傳, 因為包含玩家自己輸入的文字, 如果輸入其他參數就糟糕了!
		return format.replace("{MESSAGE}", broad.getMessage());
	}
}
