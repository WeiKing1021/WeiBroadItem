package me.wei.broaditem.broad;

import org.bukkit.scheduler.BukkitRunnable;

import me.wei.broaditem.GlobalVar;
import me.wei.broaditem.PluginCore;

public class BroadProcessRunnable extends BukkitRunnable{

	@Override
	public void run() {
		// �p�G�s���Ƶ{���S���s��
		if (GlobalVar.lstBroadProcess.size() == 0) {
			return;
		}

		// ���o�Ĥ@�Ӽs���Ƶ{
		BroadProcess broad_process = GlobalVar.lstBroadProcess.get(0);

		// �����쪺�s���Ƶ{
		ProcessManager.castBroadProcess(broad_process);

		// �R���Ĥ@�ӱƵ{
		GlobalVar.lstBroadProcess.remove(0);

	}
	
	// �D�P�B����
	public void start() {
		this.runTaskTimerAsynchronously(PluginCore.instance, 0L, 5*20L);
	}

}
