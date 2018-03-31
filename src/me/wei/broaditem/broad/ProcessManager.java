package me.wei.broaditem.broad;

import org.bukkit.entity.Player;

import me.wei.broaditem.GlobalVar;
import me.wei.broaditem.Manager;
import me.wei.broaditem.broad.classes.*;

public class ProcessManager {
	
	// 增加廣播佇列
	public static void addBroadProcess(BroadProcess broad) {
		GlobalVar.lstBroadProcess.add(broad);
	}
	
	public static void castBroadProcess(BroadProcess broad) {
		// 玩家陣列	
		Player[] players = Manager.getServer().getOnlinePlayers().toArray(new Player[0]);
		
		for (BroadType type : broad.getBroadType()) {

			// 取得廣播類別
			BroadClass broad_class = GlobalVar.broad_map.get(type);

			// 如果有抓到廣播類別, 就執行這個廣播的廣播方法
			if (broad_class != null) {
				broad_class.sendBroadCast(broad, players);
			}
		}					
	}
}
