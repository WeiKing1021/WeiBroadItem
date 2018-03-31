package me.wei.broaditem.broad.classes;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.wei.broaditem.GlobalVar;
import me.wei.broaditem.PluginCore;

public class BossBarRunnable extends BukkitRunnable{
	
	private int delay;
	
	// 建構子(廣播訊息, BossBar顏色, BossBar樣式, 持續時間, 玩家列表)
	public BossBarRunnable(String title, BarColor color, BarStyle style, int delay, Player... players) {
		this.delay = delay;		
		
		GlobalVar.bossbar.setTitle(title);
		GlobalVar.bossbar.setColor(color);;
		GlobalVar.bossbar.setStyle(style);
		
		if (players == null || players.length == 0) { return; }
		
		for (Player player : players) { GlobalVar.bossbar.addPlayer(player); }
	}
	
	@Override
	public void run() {	
		GlobalVar.bossbar.removeAll();
	}
	
	public void start() {
		this.runTaskLaterAsynchronously(PluginCore.instance, delay);
	}
}
