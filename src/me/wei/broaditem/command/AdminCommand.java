package me.wei.broaditem.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.wei.broaditem.BroadItemManager;
import me.wei.broaditem.GlobalVar;
import me.wei.broaditem.Manager;
import me.wei.broaditem.broad.items.WeiBroadItem;

public class AdminCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		// �P�_���O����
		if (args != null && args.length > 0) {
			// ���O: /wbi reload
			if (args[0].toLowerCase().equals("reload")) {
				//
				Manager.reloadConfig();
				Manager.send(sender, "��a�w���sŪ���]�w��!");
				return true;
			}
			// ���O: /wbi get			
			else if (args[0].toLowerCase().equals("get")) {
				// �P�_�O���O���a��J
				if ( !(sender instanceof Player) ) {
					Manager.send(sender, "��c�ӫ��O�L�k�z�L����x����");
					return true;
				}
				
				// �૬�����a
				Player player = (Player)sender;
				
				// �p�G�S����J�N�X
				if (args.length < 2) {
					Manager.send(sender, "��c�п�J���~�N�X!");				
					return true;
				}
				
				// ���o�ƶq
				int amounts = 1;
				if (args.length > 2) {
					// ����]��
					try {
						amounts = Integer.valueOf(args[2]);
					}
					catch (NumberFormatException e){amounts = 1;}
				}
				
				// ���o�s�����~
				WeiBroadItem broad_item = BroadItemManager.getBroadItemByKey(args[1]);
				
				// �p�G�䤣��Ӫ��~
				if (broad_item == null) {
					Manager.send(player, "��c�䤣�쪫�~: ��d" + args[1]);
					return true;
				}
				
				// ���ͪ��~
				ItemStack item = broad_item.toItemStack();
				
				// �i�����L�k���͸Ӫ��~
				if (item == null) {
					Manager.send(player, "��c�䤣�쪫�~: ��d" + args[1]);
					return true;
				}
				
				// �]�w�ƶq
				item.setAmount(amounts);
				
				// �������~
				player.getInventory().addItem(item);
				
				// �i���w���o���~
				Manager.send(player, "��a�w��o�s���D��: ��d" + args[1] + "��a(" + amounts + "��a��)");
				
				return true;
			}
			// ���O: /wbi broad
			else if (args[0].toLowerCase().equals("broad")) {
				Manager.send(sender, "��c�o�ӥ\����٨S�g. �·Цۦ�i��C�����ϥ�^^");
				return true;
			}
			// ���O: /wbi clear
			else if (args[0].toLowerCase().equals("clear")) {
				// �M���Ƶ{
				GlobalVar.lstBroadProcess.clear();
				
				Manager.send(sender, "��a�w�g�M�P�Ҧ��ƶ������s��!");
				return true;
			}
			else if (args[0].toLowerCase().equals("test")) {
				return true;
			}
			// �d�ݩҦ����O
			else if (args[0].toLowerCase().equals("help")) {
				Manager.send(sender, "��7======================��aWeiBroadItem��7======================", false);
				Manager.send(sender, "��f���O�Y�g: [��6wbi��f, ��6bi��f]", false);
				Manager.send(sender, "", false);
				Manager.send(sender, "��f/weibroaditem reload ��7- ��6���sŪ���]�w��", false);
				Manager.send(sender, "��f/weibroaditem get <��a�N�X��f> <��a�ƶq(����J��1)��f> ��7- ��6���o�S�w�s�����~", false);
				Manager.send(sender, "��f/weibroaditem broad <��a�T����f> ��7- ��6�H�޲z���W�q�s��", false);
				Manager.send(sender, "��f/weibroaditem clear <��a�T����f> ��7- ��6�M�P�Ҧ��s���Ƶ{", false);
				Manager.send(sender, "��7========================================================", false);
				
				return true;
			}
		}
		
		return false;
	}

}
