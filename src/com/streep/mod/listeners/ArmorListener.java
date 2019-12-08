package com.streep.mod.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.streep.mod.Main;

public class ArmorListener implements Listener{

	public static Main plugin;
	
	public ArmorListener(Main main) {
		plugin = main;
	}
	
	@EventHandler
	public void onUpdate(PlayerMoveEvent e) {
		if(e.getPlayer() != null) {
			Player p = e.getPlayer();
			if(p.getInventory() != null) {
				if(p.getInventory().getChestplate() != null) {
					ItemStack chestplate = p.getInventory().getChestplate();
					if(chestplate.hasItemMeta()) {
						if(chestplate.getItemMeta().getDisplayName() != null) {
							String name = chestplate.getItemMeta().getDisplayName();
							if(name.equalsIgnoreCase(ChatColor.AQUA + "Aqua Armor")) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,82,1));
							}
						}
					}
				}
			}
		}
	}
	
}
