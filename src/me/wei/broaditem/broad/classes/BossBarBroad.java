package me.wei.broaditem.broad.classes;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;

import me.wei.broaditem.broad.BroadProcess;
import me.wei.broaditem.compiler.FormatCompiler;

public class BossBarBroad extends BroadClass {

	@Override
	public String[] toFormatMessage(BroadProcess broad) {
		return new String[] {FormatCompiler.transformValues(this.formatMap, broad, 1)};
	}

	@Override
	public void sendBroadCast(BroadProcess broad, Player... players) {
		String[] result = toFormatMessage(broad);

		new BossBarRunnable(result[0], BarColor.RED, BarStyle.SEGMENTED_20, 60, players).start();
	}
}
