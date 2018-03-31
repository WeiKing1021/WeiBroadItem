package me.wei.broaditem.listeners;

import me.wei.broaditem.Manager;

public class ListenerManager {
	public static void registerAll() {
		Manager.registerListener( new PlayerInteractListener() );
		Manager.registerListener( new PlayerChatListener() );
	}
}
