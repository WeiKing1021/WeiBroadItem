package me.wei.broaditem.broad.classes;

import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.wei.broaditem.broad.BroadProcess;

public abstract class BroadClass {
	
	public HashMap<Integer, String> formatMap = new HashMap<>();
	
	// �]�w�Ӽs�����O���榡((���a�ݨ쪺
	public void setFormat(ConfigurationSection section) {
		// �j���m�榡
		for (String s : section.getKeys(false)) {
			// �νs���Ӭd��
			int n = Integer.valueOf(s);
			this.formatMap.put(n, section.getString(s, "��f"));
		}
	}
	
	// ���m�榡
	public void resetFormat() {
		this.formatMap = new HashMap<>();
	}
	
	// �ഫ���̲צr��, �@��}�C���׬�1, �u��Title��2, �]����Subtitle
	public abstract String[] toFormatMessage(BroadProcess broad);
	
	public abstract void sendBroadCast(BroadProcess broad, Player... players);
}