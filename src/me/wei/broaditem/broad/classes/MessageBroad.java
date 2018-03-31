package me.wei.broaditem.broad.classes;

import org.bukkit.entity.Player;

import me.wei.broaditem.broad.BroadProcess;
import me.wei.broaditem.compiler.FormatCompiler;

public class MessageBroad extends BroadClass {
	@Override
	public String[] toFormatMessage(BroadProcess broad) {
		return new String[] {
				FormatCompiler.transformValues(this.formatMap, broad, 1)
				};
	}

	@Override
	public void sendBroadCast(BroadProcess broad, Player... players) {
		String[] result = toFormatMessage(broad);

		for (Player player : players) {
			player.sendMessage(result[0]);
		}
	}
}
