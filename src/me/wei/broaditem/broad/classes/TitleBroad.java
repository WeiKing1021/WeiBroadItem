package me.wei.broaditem.broad.classes;

import org.bukkit.entity.Player;

import me.wei.broaditem.broad.BroadProcess;
import me.wei.broaditem.compiler.FormatCompiler;

public class TitleBroad extends BroadClass {
	@Override
	public String[] toFormatMessage(BroadProcess broad) {
		return new String[] {
				FormatCompiler.transformValues(this.formatMap, broad, 1),
				FormatCompiler.transformValues(this.formatMap, broad, 2)
				};
	}

	@Override
	public void sendBroadCast(BroadProcess broad, Player... players) {
		String[] result = toFormatMessage(broad);

		for (Player player : players) {
			player.sendTitle(result[0], result[1], 10, 60, 10);
		}
	}
}
