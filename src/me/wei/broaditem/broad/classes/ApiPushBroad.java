package me.wei.broaditem.broad.classes;

import java.util.logging.Level;

import org.bukkit.entity.Player;

import me.wei.broaditem.GlobalVar;
import me.wei.broaditem.Manager;
import me.wei.broaditem.broad.BroadProcess;
import me.wei.broaditem.broad.BroadType;

public class ApiPushBroad extends BroadClass{

	@Override
	public String[] toFormatMessage(BroadProcess broad) {return null;}

	@Override
	public void sendBroadCast(BroadProcess broad, Player... players) {
		
		// 判斷是否要公開廣播
		boolean api_push_install = true;
		if (broad.getBroadType().contains(BroadType.ApiPush)) {
			// 看有沒有安裝WeiBroadAPI
			if (GlobalVar.broad_api == null) {
				api_push_install = false;
				Manager.log("想用網站廣播功能請安裝WeiBroadApi插件!", Level.WARNING);
			}
		}
		
		if (api_push_install) {
			// 放置玩家推播至網站
			GlobalVar.broad_api.NormalBroad(broad.getPlayer().getName(), broad.getMessage());							
		}	
	}
}
