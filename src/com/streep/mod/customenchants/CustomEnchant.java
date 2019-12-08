package com.streep.mod.customenchants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomEnchant {

	private int type = 0;
	private int level = 1;
	
	public CustomEnchant(int EnchantmentType, int level) {
		this.type = EnchantmentType;
		this.level = level;
	}
	
	

	public void addToItemStack(ItemStack i) {
		ItemMeta meta = i.getItemMeta();
		if(meta.hasLore()) {
			List<String> lore = meta.getLore();
			boolean remove = false;
			int toremove = 0;
			int o = 0;
			for(String s : lore) {
				if(s.contains(CustomEnchantType.getName(this.type))) {
					remove = true;
					toremove = o;
				}
				o++;
			}
			if(remove) {
				lore.remove(toremove);
			}
			lore.add(CustomEnchantType.getName(this.type) + " " + toNumeral(this.level));
			meta.setLore(lore);
		} else {
			List<String> lore = new ArrayList<String>();
			lore.add(CustomEnchantType.getName(this.type) + " " + toNumeral(this.level));
			meta.setLore(lore);
		}
		i.setItemMeta(meta);
	}

	private String toNumeral(int number) {
		if(number > 10) {
			return number + "";
		}
		if(number < 0) {
			return number + "";
		}
		if(number == 1) {
			return "I";
		}
		if(number == 2) {
			return "II";
		}
		if(number == 3) {
			return "III";
		}
		if(number == 4) {
			return "IV";
		}
		if(number == 5) {
			return "V";
		}
		if(number == 6) {
			return "VI";
		}
		if(number == 7) {
			return "VII";
		}
		if(number == 8) {
			return "VIII";
		}
		if(number == 9) {
			return "IX";
		}
		if(number == 10) {
			return "X";
		}
		return number + "";
	}
	
}
