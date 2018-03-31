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
		
		// �P�_�O�_�n���}�s��
		boolean api_push_install = true;
		if (broad.getBroadType().contains(BroadType.ApiPush)) {
			// �ݦ��S���w��WeiBroadAPI
			if (GlobalVar.broad_api == null) {
				api_push_install = false;
				Manager.log("�Q�κ����s���\��Цw��WeiBroadApi����!", Level.WARNING);
			}
		}
		
		if (api_push_install) {
			// ��m���a�����ܺ���
			GlobalVar.broad_api.NormalBroad(broad.getPlayer().getName(), broad.getMessage());							
		}	
	}
}
