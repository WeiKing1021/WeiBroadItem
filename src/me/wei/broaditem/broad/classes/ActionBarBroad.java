package me.wei.broaditem.broad.classes;

import org.bukkit.entity.Player;

import me.wei.broaditem.broad.BroadProcess;
import me.wei.broaditem.compiler.FormatCompiler;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class ActionBarBroad extends BroadClass{

	@Override
	public String[] toFormatMessage(BroadProcess broad) {
		return new String[] {FormatCompiler.transformValues(this.formatMap, broad, 1)};
	}

	@Override
	public void sendBroadCast(BroadProcess broad, Player... players) {
		String[] result = toFormatMessage(broad);

		for (Player player : players) {
			player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(result[0]));
		}	
	}
}
