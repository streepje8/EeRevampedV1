package com.streep.mod;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ManaManager {

	public static HashMap<String, Integer> mana = new HashMap<String, Integer>();
	public static HashMap<String, Integer> manacap = new HashMap<String, Integer>();
	public static Main plugin;
	
	public static int getMana(Player p) {
		if(mana.containsKey(p.getDisplayName())) {
			return mana.get(p.getDisplayName());
		} else {
			mana.put(p.getDisplayName(), 0);
			return 0;
		}
	}
	
	public static void init(Main m) {
		plugin = m;
	}
	
	public static void addCap(Player p, int amount) {
		int currentcap = manacap.get(p.getDisplayName());
		manacap.put(p.getDisplayName(), currentcap + amount);
	}
	public static void remCap(Player p, int amount) {
		int currentcap = manacap.get(p.getDisplayName());
		manacap.put(p.getDisplayName(), currentcap - amount);
	}
	public static void setCap(Player p, int amount) {
		manacap.put(p.getDisplayName(), amount);
	}
	
	public static void giveMana(Player p, int amount) {
		int mana = getMana(p);
		mana += amount;
		setMana(p, mana);
	}
	
	public static void setMana(Player p, int amount) {
		mana.remove(p.getDisplayName());
		mana.put(p.getDisplayName(), amount);
		saveMana();
	}
	
	public static void displayMana(Player p) { 
		ActionBar.sendActionBarMessage(p, ChatColor.AQUA + "Mana: " + ChatColor.BLUE + getMana(p) + ChatColor.AQUA + "/" + getMax(p));
	}

	public static int getMax(Player p) {
		if(manacap.containsKey(p.getDisplayName())) {
			return manacap.get(p.getDisplayName());
		} else {
			manacap.put(p.getDisplayName(), 90);
			return 90;
		}
	}

	public static void takeMana(Player player, int i) {
		int mana = getMana(player);
		mana -= i;
		setMana(player, mana);
		
	}
	
	public static void saveMana() {
		File customYml = new File(plugin.getDataFolder()+"/mana.yml");
		FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customYml);
		String text = "";
		for(String playerName : mana.keySet()) {
			text += playerName + "%" + mana.get(playerName) + "&";
		}
		String maxtext = "";
		for(String playerName : manacap.keySet()) {
			maxtext += playerName + "%" + manacap.get(playerName) + "&";
		}
		customConfig.set("manadata", text);
		customConfig.set("manamax", maxtext);
		saveCustomYml(customConfig, customYml);
	}
	
	public static void loadMana() {
		try {
			File customYml = new File(plugin.getDataFolder()+"/mana.yml");
			FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customYml);
			String manading = customConfig.getString("manadata");
			String manacapI = customConfig.getString("manamax");
			for(String playermana : manading.split("&")) {
				String[] plma = playermana.split("%");
				if(plma.length == 2) {
					String playername = plma[0];
					String mana = plma[1];
					ManaManager.mana.put(playername, Integer.parseInt(mana));
				} else {
					plugin.getLogger().info("ERROR FAILED TO LOAD MANA");
				}
			}
			for(String playermanacap : manacapI.split("&")) {
				String[] plmac = playermanacap.split("%");
				if(plmac.length == 2) {
					String playernames = plmac[0];
					String manacaps = plmac[1];
					ManaManager.manacap.put(playernames, Integer.parseInt(manacaps));
				} else {
					plugin.getLogger().info("ERROR FAILED TO LOAD MANA CAPS");
				}
			}
		} catch(Exception e) {
			plugin.getLogger().info("No mana file found creating a new one when mana changes");
		}
	}
	
	public static void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
		e.printStackTrace();
		}
	}
}
