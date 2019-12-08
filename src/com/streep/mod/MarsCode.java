package com.streep.mod;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MarsCode {

	
	
	@SuppressWarnings("deprecation")
	public static void PlayerInteract(PlayerInteractEvent event, Action eAction) {
		if(eAction == Action.RIGHT_CLICK_AIR || eAction == Action.RIGHT_CLICK_BLOCK) {
			if(event.getPlayer().getItemInHand() != null) {
				if(event.getPlayer().getItemInHand().getItemMeta() != null) {
					if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null) {
						if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Space helmet")) {
							if(event.getPlayer().getInventory().getHelmet() == null) {
								event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() - 1);
								m.getLogger().info("ERROR IN SETTING HELMET");
								ItemStack CustomItem = new ItemStack(Material.STAINED_GLASS,1);
								ItemMeta meta = CustomItem.getItemMeta();
								meta.setDisplayName(ChatColor.AQUA + "Space helmet");
								meta.setUnbreakable(true);
								CustomItem.setItemMeta(meta);
								event.getPlayer().getInventory().setHelmet(CustomItem);
								event.setCancelled(true);
							} else {
								//m.getLogger().info("HELMET IS NOT NULL");
							}
						} else {
							//m.getLogger().info("HAND ITEM IS NULL");
						}
					}
				}
			}
		}
		
	}

	public static void update(Player p) {
		if(p.getWorld().getName() != null) {
			if(p.getWorld().getName().equalsIgnoreCase("world_mars")) {
				if(p.getInventory().getHelmet() != null) {
					if(p.getInventory().getHelmet().getItemMeta() != null) {
						if(p.getInventory().getHelmet().getItemMeta().getDisplayName() != null) {
							if(p.getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Space helmet")) {
								p.setRemainingAir(10);
								p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 50, 3));
							} else {
								p.setHealth(p.getHealth() - 1);
								p.sendMessage(ChatColor.RED + "Get access to air!");
								if(p.getHealth() <= 2) {
									p.setHealth(0.0D);
								}
							}
						} else {
							p.setHealth(p.getHealth() - 1);
							if(p.getHealth() <= 2) {
								p.setHealth(0.0D);
							}
							p.sendMessage(ChatColor.RED + "Get access to air!");
						}
					}
				} else {
					p.setHealth(p.getHealth() - 1);
					p.sendMessage(ChatColor.RED + "Get access to air!");
					if(p.getHealth() <= 2) {
						p.setHealth(0.0D);
					}
				}
			}
		}
		
	}

	public static Main m;
	
	public static void init(Main main) {
		m = main;
	}

}
