package com.streep.mod;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.streep.mod.craftchecker.Craft;
import com.streep.mod.customenchants.table.EnchantGui;

public class ClickListener implements Listener{

	public static Main plugin;

    public ClickListener(Main instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onPlayerCraftItem​(PrepareItemCraftEvent e) {
    	if(e.getInventory().getMatrix().length < 9) {
    		return;
    	}
    	if(Craft.Check(e)) {
			e.getInventory().setResult(Craft.Rocket);
    	}
    }
    
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if(event.getEntity() instanceof Monster)
        {     
            if(event.getEntity().getKiller() != null) {
            	if(event.getEntity().getKiller() instanceof Player) {
            		Player p = event.getEntity().getKiller();
            		if(ManaManager.getMana(p) < ManaManager.getMax(p) - 10) {
            			ManaManager.giveMana(p, 10);
            			p.sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "i" + ChatColor.WHITE + "] " + ChatColor.BLUE + "10 Mana collected");
            		} else {
            			ManaManager.setMana(p, ManaManager.getMax(p));
            			p.sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "i" + ChatColor.WHITE + "] " + ChatColor.BLUE + "Reached mana cap, craft a mana amulet!");
            		}
            	}
            }
        }
    }
    
    
    @EventHandler
    public void FurnaceBurnEvent(FurnaceBurnEvent e) {
    	if(e.getBlock() != null && e.getBlock().getState() != null) {
	    	Block furnaceBlock = e.getBlock();
			Furnace state = (Furnace) furnaceBlock.getState();
			if(state.getCustomName().equalsIgnoreCase(ChatColor.AQUA + "supersmelter")) {
				state.setBurnTime((short) 10000);
				state.setCookTime((short) 50);
				state.update();
	    	}
    	}
    }
    
    @EventHandler
    public void onBlock(BlockPlaceEvent event){
    	if(event.getItemInHand().getItemMeta().getDisplayName() != null) {
	    	if(event.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "supersmelter")) {
	    		event.getPlayer().sendMessage(ChatColor.GREEN + "Super Smelter Permanently Placed");
	    		event.getPlayer().sendMessage(ChatColor.YELLOW + "Warning: it never drops itself");
	    	}
    	}
    }
    
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction() != null) {
			Action eAction = event.getAction();
			MarsCode.PlayerInteract(event, eAction);
			if(eAction == Action.LEFT_CLICK_BLOCK) {
				if(event.getPlayer().getItemInHand().getItemMeta() != null) {
					if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null) {
						if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_GRAY + "DRILL PICKAXE")) {
							Block b = event.getClickedBlock();
							Location middle = b.getLocation();
							World w = event.getPlayer().getWorld();
							for(int x = middle.getBlockX() - 1; x <= middle.getBlockX() + 1; x++) {
								for(int y = middle.getBlockY() - 1; y <= middle.getBlockY() + 1; y++) {
									for(int z = middle.getBlockZ() - 1; z <= middle.getBlockZ() + 1; z++) {
										if(!(w.getBlockAt(x, y, z).getType().equals(Material.BEDROCK)) && !(w.getBlockAt(x, y, z).getType().equals(Material.OBSIDIAN))) {
											w.getBlockAt(x, y, z).breakNaturally();
										}
									}
								}
							}
							event.getPlayer().getItemInHand().setDurability((short)(event.getPlayer().getItemInHand().getDurability() + 2));
							if(event.getPlayer().getItemInHand().getDurability() >= 250) {
								event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() - 1);
							}
						}
					}
				}
			}
			if (eAction == Action.RIGHT_CLICK_AIR || eAction == Action.RIGHT_CLICK_BLOCK ) {
				if(event.getPlayer() != null && event.getPlayer().getItemInHand() != null) {
					if(event.getPlayer().getItemInHand().getItemMeta() != null) {
						if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null) {
							if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Speed Wand")) {
								if(ManaManager.getMana(event.getPlayer()) >= 2) {
									event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 90, 2));
									ManaManager.takeMana(event.getPlayer(), 2);
								} else {
									event.getPlayer().sendMessage(ChatColor.RED + "2 Mana required.");
								}
							}
							
							if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "ManaAmulet")) {
								event.getPlayer().sendMessage(ChatColor.GREEN + "Mana cap increased");
								ManaManager.addCap(event.getPlayer(), 30);
								ItemStack CustomItem = event.getPlayer().getItemInHand();
								CustomItem.setAmount(CustomItem.getAmount() - 1);
								event.setCancelled(true);
							}
							
							if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Custom Enchanter")) {
								EnchantGui.openGui(event.getPlayer());
								event.setCancelled(true);
							}
							
							if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Rocket")) {
								if(!event.getPlayer().getWorld().getName().equalsIgnoreCase("world_mars")) {
									event.getPlayer().teleport(new Location(Craft.m.getServer().getWorld("world_mars"),Craft.m.getServer().getWorld("world_mars").getSpawnLocation().getX(),Craft.m.getServer().getWorld("world_mars").getSpawnLocation().getY(),Craft.m.getServer().getWorld("world_mars").getSpawnLocation().getZ()));
									event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 10));
								} else {
									event.getPlayer().teleport(new Location(Craft.m.getServer().getWorld("world"),Craft.m.getServer().getWorld("world").getSpawnLocation().getX(),Craft.m.getServer().getWorld("world").getSpawnLocation().getY(),Craft.m.getServer().getWorld("world").getSpawnLocation().getZ()));
								}
								event.setCancelled(true);
							}
							
							if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Quarry Wand")) {
								if(ManaManager.getMana(event.getPlayer()) >= 10) {
									Location loc = event.getPlayer().getLocation();
									int y = loc.getBlockY() - 1;
									for(int x = loc.getBlockX() - 2; x < loc.getBlockX() + 2; x++) {
											for(int z = loc.getBlockZ() - 2; z < loc.getBlockZ() + 2; z++) {
												if(event.getPlayer().getLocation().getWorld().getBlockAt(x, y, z).getType().equals(Material.LAVA)) {
													event.getPlayer().getLocation().getWorld().getBlockAt(x, y, z).setType(Material.COBBLESTONE);
												} else {
													Material type = event.getPlayer().getLocation().getWorld().getBlockAt(x, y, z).getType();
													if(!(type.equals(Material.BEDROCK)) && !(type.equals(Material.OBSIDIAN))) {
														event.getPlayer().getLocation().getWorld().getBlockAt(x, y, z).breakNaturally();
													} else {
														event.getPlayer().spawnParticle(Particle.SMOKE_LARGE, new Location(event.getPlayer().getWorld(),x,y,z), 5);
													}
												}
											}
										}
									y -= 1;
									for(int x = loc.getBlockX() - 2; x < loc.getBlockX() + 2; x++) {
										for(int z = loc.getBlockZ() - 2; z < loc.getBlockZ() + 2; z++) {
											if(event.getPlayer().getLocation().getWorld().getBlockAt(x, y, z).getType().equals(Material.LAVA)) {
												event.getPlayer().getLocation().getWorld().getBlockAt(x, y, z).setType(Material.COBBLESTONE);
											}
										}
									}
									ManaManager.takeMana(event.getPlayer(), 10);
								} else {
									event.getPlayer().sendMessage(ChatColor.RED + "10 Mana required.");
								}
							}	
						}
					}
				}
			}
		}
	}
}
