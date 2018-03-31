package me.wei.broaditem.broad.classes;

import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.wei.broaditem.broad.BroadProcess;

public abstract class BroadClass {
	
	public HashMap<Integer, String> formatMap = new HashMap<>();
	
	// 設定該廣播類別的格式((玩家看到的
	public void setFormat(ConfigurationSection section) {
		// 迴圈放置格式
		for (String s : section.getKeys(false)) {
			// 用編號來查找
			int n = Integer.valueOf(s);
			this.formatMap.put(n, section.getString(s, "§f"));
		}
	}
	
	// 重置格式
	public void resetFormat() {
		this.formatMap = new HashMap<>();
	}
	
	// 轉換成最終字串, 一般陣列長度為1, 只有Title為2, 因為有Subtitle
	public abstract String[] toFormatMessage(BroadProcess broad);
	
	public abstract void sendBroadCast(BroadProcess broad, Player... players);
}