package me.wei.broaditem.broad;

import org.bukkit.entity.Player;

import me.wei.broaditem.GlobalVar;
import me.wei.broaditem.Manager;
import me.wei.broaditem.broad.classes.*;

public class ProcessManager {
	
	// �W�[�s����C
	public static void addBroadProcess(BroadProcess broad) {
		GlobalVar.lstBroadProcess.add(broad);
	}
	
	public static void castBroadProcess(BroadProcess broad) {
		// ���a�}�C	
		Player[] players = Manager.getServer().getOnlinePlayers().toArray(new Player[0]);
		
		for (BroadType type : broad.getBroadType()) {

			// ���o�s�����O
			BroadClass broad_class = GlobalVar.broad_map.get(type);

			// �p�G�����s�����O, �N����o�Ӽs�����s����k
			if (broad_class != null) {
				broad_class.sendBroadCast(broad, players);
			}
		}					
	}
}
