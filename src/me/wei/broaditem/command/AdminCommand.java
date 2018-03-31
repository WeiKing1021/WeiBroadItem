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
		
		// 判斷指令長度
		if (args != null && args.length > 0) {
			// 指令: /wbi reload
			if (args[0].toLowerCase().equals("reload")) {
				//
				Manager.reloadConfig();
				Manager.send(sender, "§a已重新讀取設定檔!");
				return true;
			}
			// 指令: /wbi get			
			else if (args[0].toLowerCase().equals("get")) {
				// 判斷是不是玩家輸入
				if ( !(sender instanceof Player) ) {
					Manager.send(sender, "§c該指令無法透過控制台執行");
					return true;
				}
				
				// 轉型成玩家
				Player player = (Player)sender;
				
				// 如果沒有輸入代碼
				if (args.length < 2) {
					Manager.send(sender, "§c請輸入物品代碼!");				
					return true;
				}
				
				// 取得數量
				int amounts = 1;
				if (args.length > 2) {
					// 防止跑錯
					try {
						amounts = Integer.valueOf(args[2]);
					}
					catch (NumberFormatException e){amounts = 1;}
				}
				
				// 取得廣播物品
				WeiBroadItem broad_item = BroadItemManager.getBroadItemByKey(args[1]);
				
				// 如果找不到該物品
				if (broad_item == null) {
					Manager.send(player, "§c找不到物品: §d" + args[1]);
					return true;
				}
				
				// 產生物品
				ItemStack item = broad_item.toItemStack();
				
				// 告知說無法產生該物品
				if (item == null) {
					Manager.send(player, "§c找不到物品: §d" + args[1]);
					return true;
				}
				
				// 設定數量
				item.setAmount(amounts);
				
				// 給予物品
				player.getInventory().addItem(item);
				
				// 告知已取得物品
				Manager.send(player, "§a已獲得廣播道具: §d" + args[1] + "§a(" + amounts + "§a個)");
				
				return true;
			}
			// 指令: /wbi broad
			else if (args[0].toLowerCase().equals("broad")) {
				Manager.send(sender, "§c這個功能我還沒寫. 麻煩自行進到遊戲中使用^^");
				return true;
			}
			// 指令: /wbi clear
			else if (args[0].toLowerCase().equals("clear")) {
				// 清除排程
				GlobalVar.lstBroadProcess.clear();
				
				Manager.send(sender, "§a已經撤銷所有排隊中的廣播!");
				return true;
			}
			else if (args[0].toLowerCase().equals("test")) {
				return true;
			}
			// 查看所有指令
			else if (args[0].toLowerCase().equals("help")) {
				Manager.send(sender, "§7======================§aWeiBroadItem§7======================", false);
				Manager.send(sender, "§f指令縮寫: [§6wbi§f, §6bi§f]", false);
				Manager.send(sender, "", false);
				Manager.send(sender, "§f/weibroaditem reload §7- §6重新讀取設定檔", false);
				Manager.send(sender, "§f/weibroaditem get <§a代碼§f> <§a數量(不輸入為1)§f> §7- §6取得特定廣播物品", false);
				Manager.send(sender, "§f/weibroaditem broad <§a訊息§f> §7- §6以管理員名義廣播", false);
				Manager.send(sender, "§f/weibroaditem clear <§a訊息§f> §7- §6撤銷所有廣播排程", false);
				Manager.send(sender, "§7========================================================", false);
				
				return true;
			}
		}
		
		return false;
	}

}
