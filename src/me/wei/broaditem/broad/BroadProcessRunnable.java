package me.wei.broaditem.broad;

import org.bukkit.scheduler.BukkitRunnable;

import me.wei.broaditem.GlobalVar;
import me.wei.broaditem.PluginCore;

public class BroadProcessRunnable extends BukkitRunnable{

	@Override
	public void run() {
		// 如果廣播排程中沒有廣播
		if (GlobalVar.lstBroadProcess.size() == 0) {
			return;
		}

		// 取得第一個廣播排程
		BroadProcess broad_process = GlobalVar.lstBroadProcess.get(0);

		// 播放抓到的廣播排程
		ProcessManager.castBroadProcess(broad_process);

		// 刪除第一個排程
		GlobalVar.lstBroadProcess.remove(0);

	}
	
	// 非同步執行
	public void start() {
		this.runTaskTimerAsynchronously(PluginCore.instance, 0L, 5*20L);
	}

}
