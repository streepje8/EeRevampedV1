package com.streep.mod.customenchants.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.streep.mod.Main;
import com.streep.mod.customenchants.CustomEnchant;
import com.streep.mod.customenchants.CustomEnchantType;

public class EnchantGui implements Listener{

	public static Main plugin;
	
	public EnchantGui(Main m) {
		plugin = m;
	}
	
	public static void openGui(Player p) {
		Inventory inv = plugin.getServer().createInventory(null, 27,ChatColor.DARK_PURPLE + "Custom Enchant");
		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "");
		i.setItemMeta(meta);
		for(int z = 0; z <= 11; z++) {
			inv.setItem(z, i);
		}
		for(int z = 15; z <= 26; z++) {
			inv.setItem(z, i);
		}
		ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE,1, (short)3);
		ItemMeta metas = is.getItemMeta();
		metas.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Confirm");
		List<String> list = new ArrayList<String>();
		if(metas.hasLore()) {
			list = metas.getLore();
		}
		list.add(ChatColor.GRAY + "Put an item to the left");
		list.add(ChatColor.GRAY + "Put an emerald to the right");
		list.add(ChatColor.GRAY + "Click to confirm");
		metas.setLore(list);
		is.setItemMeta(metas);
		inv.setItem(13, is);
		p.openInventory(inv);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked(); // The player that clicked the item
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		Inventory inventory = event.getInventory(); // The inventory that was clicked in
		if (inventory.getName().equals(ChatColor.DARK_PURPLE + "Custom Enchant")) {
			if(clicked.hasItemMeta()) {
				if(clicked.getItemMeta().getDisplayName() != null) {
					if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "")) {
						event.setCancelled(true);
					}
				}
			}
			if(clicked.hasItemMeta()) {
				if(clicked.getItemMeta().getDisplayName() != null) {
					if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "" + ChatColor.BOLD + "Confirm")) {
						event.setCancelled(true);
						if(inventory.getItem(14).getType().equals(Material.EMERALD)) {
							int amount = inventory.getItem(14).getAmount();
							inventory.getItem(14).setAmount(amount - 1);
							Random rand = new Random();
							int ectype = rand.nextInt(CustomEnchantType.top + 1);
							int level = rand.nextInt(CustomEnchantType.getMax(ectype));
							if(level == 0) {
								level++;
							}
							CustomEnchant ec = new CustomEnchant(ectype ,level);
							ec.addToItemStack(inventory.getItem(12));
							player.sendMessage(ChatColor.GREEN + "Enchant success!");
						} else {
							player.sendMessage(ChatColor.RED + "Put in an emerald!");
						}
					}
				}
			}
		}
	}
	
}
