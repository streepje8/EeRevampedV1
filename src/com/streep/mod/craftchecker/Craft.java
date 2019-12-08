package com.streep.mod.craftchecker;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import com.streep.mod.Main;
import com.streep.mod.craftguide.GuideManager;

public class Craft {

	public static ItemStack Rocket;
	public static ItemStack Fuel;
	public static ItemStack Core;

	public static void setFuel(ItemStack customItem) {
		Fuel = customItem;
		
	}

	public static void setFusion(ItemStack customItem) {
		Core = customItem;
		
	}
	
	public static Main m;
	
	public static void setMain(Main main) {
		m = main;
	}

	public static void setRocket(ItemStack rocket) {
		Rocket = rocket;
	}
	
	@SuppressWarnings("serial")
	public static boolean Check(PrepareItemCraftEvent e) {
		if(CheckRecipe(e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, Fuel);
            put(1, new ItemStack(Material.GLASS));
            put(2, Fuel);
            put(3, new ItemStack(Material.QUARTZ_BLOCK));
            put(4, Core);
            put(5, new ItemStack(Material.QUARTZ_BLOCK));
            put(6, Fuel);
            put(7, new ItemStack(Material.QUARTZ_BLOCK));
            put(8, Fuel);
        }})) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean CheckRecipe(CraftingInventory inv, HashMap<Integer, ItemStack> ingredients) {
		ItemStack[] matrix = inv.getMatrix();
        for(int i = 0; i < 9; i++){
            if(ingredients.containsKey(i)){
                if(matrix[i] == null || !matrix[i].equals(ingredients.get(i))){
                    return false;
                }
            } else {
                if(matrix[i] != null){
                    return false;
                }
            }
        }
        return true;
	}

	
	public static Logger Logger;
	public static void setLogger(Logger logger) {
		Logger = logger;
	}

	public static void setup(Main main) {
		
		ItemStack CustomItems = new ItemStack(Material.LEATHER_HELMET,1);
		LeatherArmorMeta lmeta = (LeatherArmorMeta) CustomItems.getItemMeta();
		lmeta.setDisplayName(ChatColor.AQUA + "Redstone Armor");
		lmeta.setUnbreakable(true);
		lmeta.setColor(Color.fromRGB(252, 40, 3));
		lmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
		lmeta.setUnbreakable(true);
		CustomItems.setItemMeta(lmeta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe11 = new ShapedRecipe(CustomItems).shape("rrr", "rar", "aaa").setIngredient('r', Material.REDSTONE).setIngredient('a', Material.AIR);
				main.getServer().addRecipe(Recipe11);
		GuideManager.addRecipe(CustomItems, Recipe11.getShape(), Recipe11.getIngredientMap(),main);
				
		CustomItems = new ItemStack(Material.LEATHER_CHESTPLATE,1);
		lmeta = (LeatherArmorMeta) CustomItems.getItemMeta();
		lmeta.setDisplayName(ChatColor.AQUA + "Redstone Armor");
		lmeta.setUnbreakable(true);
		lmeta.setColor(Color.fromRGB(252, 40, 3));
		lmeta.setUnbreakable(true);
		lmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
		CustomItems.setItemMeta(lmeta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe12 = new ShapedRecipe(CustomItems).shape("rar", "rrr", "rrr").setIngredient('r', Material.REDSTONE).setIngredient('a', Material.AIR);
				main.getServer().addRecipe(Recipe12);
		GuideManager.addRecipe(CustomItems, Recipe12.getShape(), Recipe12.getIngredientMap(),main);
			
		CustomItems = new ItemStack(Material.LEATHER_LEGGINGS,1);
		lmeta = (LeatherArmorMeta) CustomItems.getItemMeta();
		lmeta.setDisplayName(ChatColor.AQUA + "Redstone Armor");
		lmeta.setUnbreakable(true);
		lmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
		lmeta.setColor(Color.fromRGB(252, 40, 3));
		CustomItems.setItemMeta(lmeta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe13 = new ShapedRecipe(CustomItems).shape("rrr", "rar", "rar").setIngredient('r', Material.REDSTONE).setIngredient('a', Material.AIR);
				main.getServer().addRecipe(Recipe13);
		GuideManager.addRecipe(CustomItems, Recipe13.getShape(), Recipe13.getIngredientMap(),main);
								
		CustomItems = new ItemStack(Material.LEATHER_BOOTS,1);
		lmeta = (LeatherArmorMeta) CustomItems.getItemMeta();
		lmeta.setDisplayName(ChatColor.AQUA + "Redstone Armor");
		lmeta.setUnbreakable(true);
		lmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
		lmeta.setColor(Color.fromRGB(252, 40, 3));
		CustomItems.setItemMeta(lmeta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe14 = new ShapedRecipe(CustomItems).shape("aaa", "rar", "rar").setIngredient('r', Material.REDSTONE).setIngredient('a', Material.AIR);
				main.getServer().addRecipe(Recipe14);
		GuideManager.addRecipe(CustomItems, Recipe14.getShape(), Recipe14.getIngredientMap(),main);
		

		CustomItems = new ItemStack(Material.LEATHER_HELMET,1);
		lmeta = (LeatherArmorMeta) CustomItems.getItemMeta();
		lmeta.setDisplayName(ChatColor.AQUA + "Emerald Armor");
		lmeta.setUnbreakable(true);
		lmeta.setColor(Color.fromRGB(40, 253, 3));
		lmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
		lmeta.setUnbreakable(true);
		CustomItems.setItemMeta(lmeta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe15 = new ShapedRecipe(CustomItems).shape("rrr", "rar", "aaa").setIngredient('r', Material.EMERALD).setIngredient('a', Material.AIR);
				main.getServer().addRecipe(Recipe15);
		GuideManager.addRecipe(CustomItems, Recipe15.getShape(), Recipe15.getIngredientMap(),main);
				
		CustomItems = new ItemStack(Material.LEATHER_CHESTPLATE,1);
		lmeta = (LeatherArmorMeta) CustomItems.getItemMeta();
		lmeta.setDisplayName(ChatColor.AQUA + "Emerald Armor");
		lmeta.setUnbreakable(true);
		lmeta.setColor(Color.fromRGB(40, 253, 3));
		lmeta.setUnbreakable(true);
		lmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
		CustomItems.setItemMeta(lmeta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe16 = new ShapedRecipe(CustomItems).shape("rar", "rrr", "rrr").setIngredient('r', Material.EMERALD).setIngredient('a', Material.AIR);
				main.getServer().addRecipe(Recipe16);
		GuideManager.addRecipe(CustomItems, Recipe16.getShape(), Recipe16.getIngredientMap(),main);
			
		CustomItems = new ItemStack(Material.LEATHER_LEGGINGS,1);
		lmeta = (LeatherArmorMeta) CustomItems.getItemMeta();
		lmeta.setDisplayName(ChatColor.AQUA + "Emerald Armor");
		lmeta.setUnbreakable(true);
		lmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
		lmeta.setColor(Color.fromRGB(40, 253, 3));
		CustomItems.setItemMeta(lmeta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe17 = new ShapedRecipe(CustomItems).shape("rrr", "rar", "rar").setIngredient('r', Material.EMERALD).setIngredient('a', Material.AIR);
				main.getServer().addRecipe(Recipe17);
		GuideManager.addRecipe(CustomItems, Recipe17.getShape(), Recipe17.getIngredientMap(),main);
								
		CustomItems = new ItemStack(Material.LEATHER_BOOTS,1);
		lmeta = (LeatherArmorMeta) CustomItems.getItemMeta();
		lmeta.setDisplayName(ChatColor.AQUA + "Emerald Armor");
		lmeta.setUnbreakable(true);
		lmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
		lmeta.setColor(Color.fromRGB(40, 253, 3));
		CustomItems.setItemMeta(lmeta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe18 = new ShapedRecipe(CustomItems).shape("aaa", "rar", "rar").setIngredient('r', Material.EMERALD).setIngredient('a', Material.AIR);
				main.getServer().addRecipe(Recipe18);
		GuideManager.addRecipe(CustomItems, Recipe18.getShape(), Recipe18.getIngredientMap(),main);
		
		ItemStack CustomItema = new ItemStack(Material.BOOK,1);
		ItemMeta imeta = CustomItema.getItemMeta();
		imeta.setDisplayName(ChatColor.AQUA + "Custom Enchanter");
		imeta.setUnbreakable(true);
		imeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
		CustomItema.setItemMeta(imeta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe19 = new ShapedRecipe(CustomItema).shape("ara", "rbr", "ara").setIngredient('r', Material.DIAMOND_BLOCK).setIngredient('a', Material.AIR).setIngredient('b', Material.BOOK);
				main.getServer().addRecipe(Recipe19);
		GuideManager.addRecipe(CustomItema, Recipe19.getShape(), Recipe19.getIngredientMap(),main);
		
		
		CustomItems = new ItemStack(Material.LEATHER_CHESTPLATE,1);
		lmeta = (LeatherArmorMeta) CustomItems.getItemMeta();
		lmeta.setDisplayName(ChatColor.AQUA + "Aqua Armor");
		lmeta.setUnbreakable(true);
		lmeta.setColor(Color.fromRGB(3, 40, 252));
		lmeta.setUnbreakable(true);
		lmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
		CustomItems.setItemMeta(lmeta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe20 = new ShapedRecipe(CustomItems).shape("rar", "rrr", "rrr").setIngredient('r', Material.PRISMARINE_CRYSTALS).setIngredient('a', Material.AIR);
				main.getServer().addRecipe(Recipe20);
		GuideManager.addRecipe(CustomItems, Recipe20.getShape(), Recipe20.getIngredientMap(),main);
				
	}
	
}
