package com.streep.mod.craftguide;

import java.util.HashMap;

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

public class GuideListener implements Listener{

	public static Main m;
	
	public GuideListener(Main main) {
		m = main;
	}

	private HashMap<String,Integer> playerPage = new HashMap<String, Integer>(); //name, page
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked(); // The player that clicked the item
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		Inventory inventory = event.getInventory(); // The inventory that was clicked in
		if (inventory.getName().equals(ChatColor.AQUA + "Crafting Guide")) {
			if(clicked.hasItemMeta()) {
				if(clicked.getItemMeta().hasDisplayName()) {
					if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Next")) {
						GuideManager.openRecipe(player,0);
						if(this.playerPage.containsKey(player.getName())) {
							this.playerPage.remove(player.getName());
						}
						this.playerPage.put(player.getName(), 0);
					}
				}
			}
			event.setCancelled(true);
		}
		if(inventory.getName().equals(ChatColor.AQUA + "Crafting")) {
			if(clicked.hasItemMeta()) {
				if(clicked.getItemMeta().hasDisplayName()) {
					if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Next")) {
						if(GuideManager.getMax() > this.playerPage.get(player.getName())) {
							GuideManager.openRecipe(player,this.playerPage.get(player.getName()) + 1);
							int nowpage = this.playerPage.get(player.getName());
							this.playerPage.remove(player.getName());
							this.playerPage.put(player.getName(), nowpage + 1);
						} else {
							Inventory myInventory = m.getServer().createInventory(null, 27,ChatColor.AQUA + "Crafting Guide");
							ItemStack item = new ItemStack(Material.ARROW, 1);
							ItemMeta meta = item.getItemMeta();
							meta.setDisplayName(ChatColor.AQUA + "Next");
							item.setItemMeta(meta);
							myInventory.setItem(26, item);
							item = new ItemStack(Material.BOOK, 1);
							meta = item.getItemMeta();
							meta.setDisplayName(ChatColor.WHITE + "Welcome To The Crafting Guide");
							item.setItemMeta(meta);
							myInventory.setItem(13, item);
							player.openInventory(myInventory);
						}
					}
				}
			}
			if(clicked.hasItemMeta()) {
				if(clicked.getItemMeta().hasDisplayName()) {
					if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Back")) {
						if(this.playerPage.get(player.getName()) > 0) {
							GuideManager.openRecipe(player,this.playerPage.get(player.getName()) - 1);
							int nowpage = this.playerPage.get(player.getName());
							this.playerPage.remove(player.getName());
							this.playerPage.put(player.getName(), nowpage - 1);
						} else {
							Inventory myInventory = m.getServer().createInventory(null, 27,ChatColor.AQUA + "Crafting Guide");
							ItemStack item = new ItemStack(Material.ARROW, 1);
							ItemMeta meta = item.getItemMeta();
							meta.setDisplayName(ChatColor.AQUA + "Next");
							item.setItemMeta(meta);
							myInventory.setItem(26, item);
							item = new ItemStack(Material.BOOK, 1);
							meta = item.getItemMeta();
							meta.setDisplayName(ChatColor.WHITE + "Welcome To The Crafting Guide");
							item.setItemMeta(meta);
							myInventory.setItem(13, item);
							player.openInventory(myInventory);
						}
					}
				}
			}
			if(event.isLeftClick() || event.isRightClick() || event.isShiftClick()) {
				if(!clicked.getType().equals(Material.AIR)) {
					event.setCancelled(true);
				}
			}
		}
		
	}
	
}
