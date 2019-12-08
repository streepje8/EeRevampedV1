package com.streep.mod.customenchants;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnchantCommand {

	@SuppressWarnings("deprecation")
	public static void runCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			if(args.length == 2) {
				try {
					int type = Integer.parseInt(args[0]);
					int level = Integer.parseInt(args[1]);
					Player p = (Player) sender;
					if(p.getItemInHand() != null) {
						CustomEnchant e = new CustomEnchant(type, level);
						e.addToItemStack(p.getItemInHand());
					}
				} catch(Exception e) {
					sender.sendMessage("Please give valid numbers");
				}
			}
		}
	}
	
}
