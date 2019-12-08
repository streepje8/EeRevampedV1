package com.streep.mod.customenchants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.streep.mod.Main;

public class EnchantListener implements Listener{

	public static Main plugin;

    public EnchantListener(Main instance) {
        plugin = instance;
    }
	
    @SuppressWarnings("deprecation")
    @EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(e.getPlayer() != null) {
			if(e.getPlayer().getItemInHand() != null) {
				if(e.getPlayer().getItemInHand().hasItemMeta()) {
					if(e.getPlayer().getItemInHand().getItemMeta().hasLore()) {
						BlockBreak(e);
					}
				}
			}
		}
    }
    
	@SuppressWarnings("deprecation")
	private void BlockBreak(BlockBreakEvent e) {
		ArrayList<Integer> etypes = getTypes(e.getPlayer().getItemInHand().getItemMeta().getLore());
		for(int type : etypes) {
			if(type == 4) {
				if(e.getBlock().getType().equals(Material.LAPIS_ORE)) {
					int level = getLevelFromLore(type, e.getPlayer().getItemInHand().getItemMeta().getLore());
					int max = 11;
					if(level == 1) {
						max = 6;
					}
					if(level == 2) {
						max = 4;
					}
					if(level == 3) {
						max = 2;
					}
					if(level >= 4) {
						max = 1;
					}
					Random rand = new Random();
					if(rand.nextInt(max) == 0) {
						e.setCancelled(true);
						e.getBlock().setType(Material.AIR);
						Location loc = e.getBlock().getLocation();
						ItemStack item = new ItemStack(Material.DIAMOND);
						item.setAmount(rand.nextInt(5));
						loc.getWorld().dropItem(loc, item);
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(e.getAction() != null) {
			if(e.getPlayer() != null) {
				if(e.getPlayer().getItemInHand() != null) {
					if(e.getPlayer().getItemInHand().hasItemMeta()) {
						if(e.getPlayer().getItemInHand().getItemMeta().hasLore()) {
							if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
								LeftClickEvent(e);
							}
							
							if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
								RightClickEvent(e);
							}
						}
					}
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	private void RightClickEvent(PlayerInteractEvent e) {
		ArrayList<Integer> etypes = getTypes(e.getPlayer().getItemInHand().getItemMeta().getLore());
		for(int type : etypes) {
			if(type == 0) {
				int level = getLevelFromLore(type, e.getPlayer().getItemInHand().getItemMeta().getLore());
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,84, level), true);
			}
			if(type == 3) {
				int level = getLevelFromLore(type, e.getPlayer().getItemInHand().getItemMeta().getLore());
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,84, level), true);
			}
		}
	}

	private int getLevelFromLore(int type, List<String> lore) {
		for(String s : lore) {
			if(s.contains(CustomEnchantType.getName(type))) {
				String o = s.replace(CustomEnchantType.getName(type) + " ", "");
				return CustomEnchantType.NumeralToInt(o);
			}
		}
		return 0;
	}
	 
	private ArrayList<Integer> getTypes(List<String> lore) {
		ArrayList<Integer> types = new ArrayList<Integer>();
		for(String s : lore) {
			if(CustomEnchantType.getType(s) != null) {
				types.add(CustomEnchantType.getType(s));
			}
		}
		return types;
	}

	@SuppressWarnings("deprecation")
	private void LeftClickEvent(PlayerInteractEvent e) {
		ArrayList<Integer> etypes = getTypes(e.getPlayer().getItemInHand().getItemMeta().getLore());
		for(int type : etypes) {
			if(type == 1) {
				int level = getLevelFromLore(type, e.getPlayer().getItemInHand().getItemMeta().getLore());
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,84, level), true);
			}
			if(type == 2) {
				int level = getLevelFromLore(type, e.getPlayer().getItemInHand().getItemMeta().getLore());
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,84, level), true);
			}
		}
	}

	@EventHandler
	 public void onBlock(BlockPlaceEvent e) {
		 
	 }
	
}
