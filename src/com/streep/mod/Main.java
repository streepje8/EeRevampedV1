package com.streep.mod;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.streep.mod.craftchecker.Craft;
import com.streep.mod.craftguide.GuideListener;
import com.streep.mod.craftguide.GuideManager;
import com.streep.mod.customenchants.EnchantCommand;
import com.streep.mod.customenchants.EnchantListener;
import com.streep.mod.customenchants.table.EnchantGui;
import com.streep.mod.dimensions.MarsGEN;
import com.streep.mod.listeners.ArmorListener;
import com.streep.mod.mobs.mobs.Darco;

import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityTypes;
import net.minecraft.server.v1_12_R1.EntityZombie;
import net.minecraft.server.v1_12_R1.MinecraftKey;

public class Main extends JavaPlugin { 

	public void onEnable(){
		getLogger().info(getName() + " by streepje8 and Larsigames");
		ManaManager.init(this);
		MarsCode.init(this);
		if(!(getDataFolder().exists())) {
			getDataFolder().mkdirs();
		}
		getServer().getPluginManager().registerEvents(new ClickListener(this), this);
		getServer().getPluginManager().registerEvents(new GuideListener(this), this);
		getServer().getPluginManager().registerEvents(new EnchantListener(this), this);
		getServer().getPluginManager().registerEvents(new EnchantGui(this), this);
		getServer().getPluginManager().registerEvents(new ArmorListener(this), this);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for(Player p : getServer().getOnlinePlayers()) {
					ManaManager.displayMana(p);
					MarsCode.update(p);
				}
			}
		}, 0L, 10L);
		ManaManager.loadMana();
		getLogger().info("Creating mars demension");
		for(World w : getServer().getWorlds()) {
			if(w.getName().equalsIgnoreCase("world_mars")) {
				getServer().getWorlds().remove(w);
			}
		}
		World marsworld = getServer().createWorld(new WorldCreator("world_mars").generator(new MarsGEN()));
		marsworld.setTime(7000000);
		marsworld.setGameRuleValue("doMobSpawning", "false");
		marsworld.setGameRuleValue("doDaylightCycle", "false");
		getLogger().info("Success");
		Craft.setLogger(getLogger());
		Craft.setMain(this);
		ItemStack Result = new ItemStack(Material.HOPPER,1);
		ItemMeta meta = Result.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "Rocket");
		meta.setUnbreakable(true);
		meta.addEnchant(Enchantment.PROTECTION_FIRE, 1, true);
		Result.setItemMeta(meta);
		Craft.setRocket(Result);
		setCustomItems();
		CustomEntities.registerEntities();
	}
	
	
	private void setCustomItems() {
		ItemStack CustomItem = new ItemStack(Material.STICK,1);
		ItemMeta meta = CustomItem.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "Speed Wand");
		meta.setUnbreakable(true);
		CustomItem.setItemMeta(meta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe = new ShapedRecipe(CustomItem).shape("isi", "sws", "isi").setIngredient('i', Material.IRON_INGOT).
				setIngredient('s', Material.SUGAR).setIngredient('w', Material.STICK);
				getServer().addRecipe(Recipe);
		
		
		GuideManager.addRecipe(CustomItem, Recipe.getShape(), Recipe.getIngredientMap(),this);
				
				
		CustomItem = new ItemStack(Material.STICK,1);
		meta = CustomItem.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "Quarry Wand");
		meta.setUnbreakable(true);
		CustomItem.setItemMeta(meta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe2 = new ShapedRecipe(CustomItem).shape("isi", "sws", "isi").setIngredient('i', Material.IRON_PICKAXE).
				setIngredient('s', Material.COAL).setIngredient('w', Material.OBSIDIAN);
				getServer().addRecipe(Recipe2);
		GuideManager.addRecipe(CustomItem, Recipe2.getShape(), Recipe2.getIngredientMap(),this);
				
		CustomItem = new ItemStack(Material.FURNACE,1);
		meta = CustomItem.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "SuperSmelter");
		meta.setUnbreakable(true);
		CustomItem.setItemMeta(meta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe3 = new ShapedRecipe(CustomItem).shape("ccc", "csc", "ccc").setIngredient('s', Material.LAVA_BUCKET).setIngredient('c', Material.COAL_BLOCK);
				getServer().addRecipe(Recipe3);
		GuideManager.addRecipe(CustomItem, Recipe3.getShape(), Recipe3.getIngredientMap(),this);
	
		CustomItem = new ItemStack(Material.EYE_OF_ENDER,1);
		meta = CustomItem.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_PURPLE + "ManaAmulet");
		meta.setUnbreakable(true);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		CustomItem.setItemMeta(meta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe4 = new ShapedRecipe(CustomItem).shape("iii", "iei", "iii").setIngredient('i', Material.IRON_INGOT).setIngredient('e', Material.ENDER_PEARL);
				getServer().addRecipe(Recipe4);
		GuideManager.addRecipe(CustomItem, Recipe4.getShape(), Recipe4.getIngredientMap(),this);
				
		CustomItem = new ItemStack(Material.IRON_PICKAXE,1);
		meta = CustomItem.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GRAY + "DRILL PICKAXE");
		CustomItem.setItemMeta(meta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe5 = new ShapedRecipe(CustomItem).shape("grg", "rdr", "grg").setIngredient('d', Material.DIAMOND_PICKAXE).setIngredient('g', Material.GOLD_INGOT).setIngredient('r', Material.REDSTONE_BLOCK);
				getServer().addRecipe(Recipe5);
		GuideManager.addRecipe(CustomItem, Recipe5.getShape(), Recipe5.getIngredientMap(),this);
		
		CustomItem = new ItemStack(Material.STAINED_GLASS,1);
		meta = CustomItem.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "Space helmet");
		meta.setUnbreakable(true);
		CustomItem.setItemMeta(meta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe6 = new ShapedRecipe(CustomItem).shape("qrq", "rgr", "qrq").setIngredient('g', Material.GLASS).setIngredient('q', Material.QUARTZ_BLOCK).setIngredient('r', Material.REDSTONE);
				getServer().addRecipe(Recipe6);
		GuideManager.addRecipe(CustomItem, Recipe6.getShape(), Recipe6.getIngredientMap(),this);
				
		CustomItem = new ItemStack(Material.LEATHER_CHESTPLATE,1);
		LeatherArmorMeta lmeta = (LeatherArmorMeta) CustomItem.getItemMeta();
		lmeta.setDisplayName(ChatColor.AQUA + "Space suit");
		lmeta.setUnbreakable(true);
		lmeta.setColor(Color.fromRGB(255, 255, 255));
		CustomItem.setItemMeta(lmeta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe7 = new ShapedRecipe(CustomItem).shape("qrq", "rgr", "qrq").setIngredient('g', Material.IRON_CHESTPLATE).setIngredient('q', Material.IRON_INGOT).setIngredient('r', Material.REDSTONE);
				getServer().addRecipe(Recipe7);
		GuideManager.addRecipe(CustomItem, Recipe7.getShape(), Recipe7.getIngredientMap(),this);
				
		CustomItem = new ItemStack(Material.LEATHER_LEGGINGS,1);
		lmeta = (LeatherArmorMeta) CustomItem.getItemMeta();
		lmeta.setDisplayName(ChatColor.AQUA + "Space suit");
		lmeta.setUnbreakable(true);
		lmeta.setColor(Color.fromRGB(255, 255, 255));
		CustomItem.setItemMeta(lmeta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe8 = new ShapedRecipe(CustomItem).shape("qrq", "rgr", "qrq").setIngredient('g', Material.IRON_LEGGINGS).setIngredient('q', Material.IRON_INGOT).setIngredient('r', Material.REDSTONE);
				getServer().addRecipe(Recipe8);
		GuideManager.addRecipe(CustomItem, Recipe8.getShape(), Recipe8.getIngredientMap(),this);
				
		CustomItem = new ItemStack(Material.LEATHER_BOOTS,1);
		lmeta = (LeatherArmorMeta) CustomItem.getItemMeta();
		lmeta.setDisplayName(ChatColor.AQUA + "Space suit");
		lmeta.setUnbreakable(true);
		lmeta.setColor(Color.fromRGB(255, 255, 255));
		CustomItem.setItemMeta(lmeta);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe9 = new ShapedRecipe(CustomItem).shape("qrq", "rgr", "qrq").setIngredient('g', Material.IRON_BOOTS).setIngredient('q', Material.IRON_INGOT).setIngredient('r', Material.REDSTONE);
				getServer().addRecipe(Recipe9);
		GuideManager.addRecipe(CustomItem, Recipe9.getShape(), Recipe9.getIngredientMap(),this);
				
				
		ItemStack CustomItems = new ItemStack(Material.COAL_BLOCK,1);
		meta = CustomItems.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "Fuel Cell");
		meta.setUnbreakable(true);
		meta.addEnchant(Enchantment.PROTECTION_FIRE, 1, true);
		CustomItems.setItemMeta(meta);
		Craft.setFuel(CustomItems);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe10 = new ShapedRecipe(CustomItems).shape("rcr", "cdc", "rlr").setIngredient('d', Material.REDSTONE).setIngredient('l', Material.LAVA_BUCKET).setIngredient('c', Material.COAL_BLOCK).setIngredient('r', Material.COAL);
				getServer().addRecipe(Recipe10);
		GuideManager.addRecipe(CustomItems, Recipe10.getShape(), Recipe10.getIngredientMap(),this);
				
		ItemStack CustomItemss = new ItemStack(Material.CLAY_BALL,1);
		meta = CustomItemss.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "Fusion Amulet");
		meta.setUnbreakable(true);
		meta.addEnchant(Enchantment.PROTECTION_FIRE, 1, true);
		CustomItemss.setItemMeta(meta);
		Craft.setFusion(CustomItemss);
		@SuppressWarnings("deprecation")
		ShapedRecipe Recipe11 = new ShapedRecipe(CustomItemss).shape("ioi", "oro", "ioi").setIngredient('o', Material.OBSIDIAN).setIngredient('i', Material.IRON_INGOT).setIngredient('r', Material.REDSTONE_BLOCK);
				getServer().addRecipe(Recipe11);
		GuideManager.addRecipe(CustomItemss, Recipe11.getShape(), Recipe11.getIngredientMap(),this);
				
		Craft.setup(this);
					
		
	}


	public void onDisable(){
		ManaManager.saveMana();
		CustomEntities.unregisterEntities();
		getLogger().info("HardCoreEE succesfully shut down");
	}
	
	
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("mana")) {
			sender.sendMessage(ChatColor.AQUA + "Mana: " + ChatColor.BLUE + ManaManager.getMana((Player) sender));
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("crafting")) {
			GuideManager.openGuide(this, sender, cmd, label, args);
		}
		if(sender.isOp()) {
			if(cmd.getName().equalsIgnoreCase("customenchant")) {
				EnchantCommand.runCommand(sender, cmd, label, args);
			}
			if(cmd.getName().equalsIgnoreCase("givemana")) {
				if(args.length == 2) {
					String playername = args[0];
					int amount = 0;
					try {
						amount = Integer.parseInt(args[1]);
						ManaManager.giveMana(getServer().getPlayer(playername), amount);
						return true;
					} catch(Exception e) {
						sender.sendMessage(ChatColor.RED + "amount must be a number or player is not found");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "/givemana <player> <amount>");
				}
			}
			if(cmd.getName().equalsIgnoreCase("customsummon")) {
				if(args.length > 0) {
					if(args[0].equalsIgnoreCase("0")) {
						if(sender instanceof Player) {
							Player p = (Player) sender;
							Location loc = p.getLocation();
							Darco superZombie = new Darco(loc);
							((CraftWorld)loc.getWorld()).getHandle().addEntity(superZombie, SpawnReason.CUSTOM);
							sender.sendMessage(ChatColor.GREEN + "SUCESS!");
						}
					}
				} else {
					sender.sendMessage(ChatColor.RED + "/customsummon <id>");
				}
			}
		}
		return false;
	}
	
	public enum CustomEntities {

	    Darco("Darco", 54, EntityType.ZOMBIE, EntityZombie.class, Darco.class);

	    private String name;
	    private int id;
	    private EntityType entityType;
	    private Class<? extends Entity> nmsClass;
	    private Class<? extends Entity> customClass;
	    private MinecraftKey key;
	    private MinecraftKey oldKey;

	    private CustomEntities(String name, int id, EntityType entityType, Class<? extends Entity> nmsClass, Class<? extends Entity> customClass) {
	        this.name = name;
	        this.id = id;
	        this.entityType = entityType;
	        this.nmsClass = nmsClass;
	        this.customClass = customClass;
	        this.key = new MinecraftKey(name);
	        this.oldKey = EntityTypes.b.b(nmsClass);
	    }

	    public static void registerEntities() { for (CustomEntities ce : CustomEntities.values()) ce.register(); }
	    public static void unregisterEntities() { for (CustomEntities ce : CustomEntities.values()) ce.unregister(); }

	    private void register() {
	        EntityTypes.d.add(key);
	        EntityTypes.b.a(id, key, customClass);
	    }

	    private void unregister() {
	        EntityTypes.d.remove(key);
	        EntityTypes.b.a(id, oldKey, nmsClass);
	    }

	    public String getName() {
	        return name;
	    }

	    public int getID() {
	        return id;
	    }

	    public EntityType getEntityType() {
	        return entityType;
	    }

	    public Class<?> getCustomClass() {
	        return customClass;
	    }
	}
	
}
