package me.wei.broaditem.customEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.wei.broaditem.broad.items.WeiBroadItem;

public class BroadItemCastEvent extends Event implements Cancellable {

	private static final HandlerList HandlerList = new HandlerList();

	private boolean cancel;

	private Player player;

	private WeiBroadItem broad_item;
	
	private String message;

	// 事件建構子
	public BroadItemCastEvent(Player player, WeiBroadItem broad_item, String message) {
		this.player = player;
		this.broad_item = broad_item;
		this.message = message;
	}

	public Player getPlayer() {
		return this.player;
	}

	public WeiBroadItem getBroadItem() {
		return this.broad_item;
	}
	
	public String getMessage() {
		return this.message;
	}

	@Override
	public HandlerList getHandlers() {
		return HandlerList;
	}

	// 這個也要
	public static HandlerList getHandlerList() {
		return HandlerList;
	}

	@Override
	public boolean isCancelled() {
		return cancel;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}
}
