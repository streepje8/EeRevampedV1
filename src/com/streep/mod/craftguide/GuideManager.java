package com.streep.mod.craftguide;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.streep.mod.Main;
import com.streep.mod.craftchecker.Craft;

public class GuideManager {

	public static Main mymain;
	public static ArrayList<SavedRecipe> savedRecipes = new ArrayList<SavedRecipe>();
	
	public static void addRocket(Main m) {
		Inventory RecipeInv = m.getServer().createInventory(null, 27,ChatColor.AQUA + "Crafting");
		ItemStack item = new ItemStack(Material.ARROW, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "Next");
		item.setItemMeta(meta);
		RecipeInv.setItem(26, item);
		item = new ItemStack(Material.ARROW, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "Back");
		item.setItemMeta(meta);
		RecipeInv.setItem(18, item);
		
		//set Recipe
		RecipeInv.setItem(1, Craft.Fuel);
		RecipeInv.setItem(2, new ItemStack(Material.GLASS));
		RecipeInv.setItem(3, Craft.Fuel);
		RecipeInv.setItem(10, new ItemStack(Material.QUARTZ_BLOCK));
		RecipeInv.setItem(11, Craft.Core);
		RecipeInv.setItem(12, new ItemStack(Material.QUARTZ_BLOCK));
		RecipeInv.setItem(19, Craft.Fuel);
		RecipeInv.setItem(20, new ItemStack(Material.QUARTZ_BLOCK));
		RecipeInv.setItem(21, Craft.Fuel);
		
		RecipeInv.setItem(15, Craft.Rocket);
		recipeInv.add(RecipeInv);
	}
	
	public static void openGuide(Main m, CommandSender sender, Command cmd, String label, String[] args) {
		mymain = m;
		if(!recipeIsSet) {
			for(SavedRecipe sr : savedRecipes) {
				Inventory RecipeInv = m.getServer().createInventory(null, 27,ChatColor.AQUA + "Crafting");
				ItemStack item = new ItemStack(Material.ARROW, 1);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.AQUA + "Next");
				item.setItemMeta(meta);
				RecipeInv.setItem(26, item);
				item = new ItemStack(Material.ARROW, 1);
				meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.AQUA + "Back");
				item.setItemMeta(meta);
				RecipeInv.setItem(18, item);
				
				//set Recipe
				RecipeInv.setItem(1, sr.getIngredients().get(sr.getShape()[0].charAt(0)));
				RecipeInv.setItem(2, sr.getIngredients().get(sr.getShape()[0].charAt(1)));
				RecipeInv.setItem(3, sr.getIngredients().get(sr.getShape()[0].charAt(2)));
				RecipeInv.setItem(10, sr.getIngredients().get(sr.getShape()[1].charAt(0)));
				RecipeInv.setItem(11, sr.getIngredients().get(sr.getShape()[1].charAt(1)));
				RecipeInv.setItem(12, sr.getIngredients().get(sr.getShape()[1].charAt(2)));
				RecipeInv.setItem(19, sr.getIngredients().get(sr.getShape()[2].charAt(0)));
				RecipeInv.setItem(20, sr.getIngredients().get(sr.getShape()[2].charAt(1)));
				RecipeInv.setItem(21, sr.getIngredients().get(sr.getShape()[2].charAt(2)));
				
				RecipeInv.setItem(15, sr.getResult());
				recipeInv.add(RecipeInv);
			}
			GuideManager.addRocket(m);
		}
		if(sender instanceof Player) {
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
			Player p = (Player) sender;
			p.openInventory(myInventory);
			mymain = m;
		} else {
			sender.sendMessage("No console access");
		}
		
	}

	public static ArrayList<Inventory> recipeInv = new ArrayList<Inventory>();
	public static boolean recipeIsSet = false;
	
	public static void addRecipe(ItemStack result, String[] shape, Map<Character, ItemStack> ingredients, Main main) {
		SavedRecipe sr = new SavedRecipe(result, shape, ingredients, main);
		savedRecipes.add(sr);
	}
	
	public static void openRecipe(Player p, int i) {
		if(recipeInv.size() > i) {
			p.openInventory(recipeInv.get(i));
		}
	}

	public static Integer getMax() {
		return recipeInv.size();
	}
	
}
