package com.streep.mod.mobs.mobs;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.minecraft.server.v1_12_R1.EntityZombie;
import net.minecraft.server.v1_12_R1.EnumItemSlot;
import net.minecraft.server.v1_12_R1.MobEffect;
import net.minecraft.server.v1_12_R1.MobEffects;

public class Darco extends EntityZombie{
	
	@SuppressWarnings("deprecation")
	public Darco(Location loc) {
        super(((CraftWorld)loc.getWorld()).getHandle());
        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setCustomName("Darco");
        this.setCustomNameVisible(true);
        org.bukkit.inventory.ItemStack i = new org.bukkit.inventory.ItemStack(Material.LEATHER_CHESTPLATE,1);
        LeatherArmorMeta l = (LeatherArmorMeta) i.getItemMeta();
        l.setColor(Color.BLACK);
        l.setDisplayName(ChatColor.BLACK + "Darco Armor");
        i.setItemMeta(l);
        i.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        this.setSlot(EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(i));
        i = new org.bukkit.inventory.ItemStack(Material.LEATHER_LEGGINGS,1);
        l = (LeatherArmorMeta) i.getItemMeta();
        l.setColor(Color.BLACK);
        l.setDisplayName(ChatColor.BLACK + "Darco Armor");
        i.setItemMeta(l);
        i.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        this.setSlot(EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(i));
        i = new org.bukkit.inventory.ItemStack(Material.LEATHER_BOOTS,1);
        l = (LeatherArmorMeta) i.getItemMeta();
        l.setColor(Color.BLACK);
        l.setDisplayName(ChatColor.BLACK + "Darco Armor");
        i.setItemMeta(l);
        i.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        this.setSlot(EnumItemSlot.FEET, CraftItemStack.asNMSCopy(i));
        org.bukkit.inventory.ItemStack playerhead = new org.bukkit.inventory.ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        
        SkullMeta playerheadmeta = (SkullMeta) playerhead.getItemMeta();
        playerheadmeta.setOwner("Ender_Glitcher");
        playerheadmeta.setDisplayName(ChatColor.WHITE + "Darco head");
        playerhead.setItemMeta(playerheadmeta);
        this.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(playerhead));
        i = new org.bukkit.inventory.ItemStack(Material.STONE_SWORD,1);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(ChatColor.BLACK + "Darco Sword");
        i.setItemMeta(m);
        i.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
        this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(i));
        this.addEffect(new MobEffect(MobEffects.FASTER_MOVEMENT,10000,2,true ,false));
        this.addEffect(new MobEffect(MobEffects.INVISIBILITY,10000,2,true ,false));
    }
	  
}
