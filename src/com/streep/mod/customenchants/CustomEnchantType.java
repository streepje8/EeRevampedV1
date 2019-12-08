package com.streep.mod.customenchants;

import org.bukkit.ChatColor;

public class CustomEnchantType {

	public static int Speed = 0;
	public static int strength = 1;
	public static int Haste = 2;
	public static int NightVision = 3;
	public static int DyLap = 4;
	public static int top = 4;
	
	
	public static String getName(int type) {
		if(type == Speed) {
			return ChatColor.GRAY + "Speed";
		}
		if(type == strength) {
			return ChatColor.GRAY + "Strength";
		}
		if(type == Haste) {
			return ChatColor.GRAY + "Haste";
		}
		if(type == NightVision) {
			return ChatColor.GRAY + "NightVision";
		}
		if(type == DyLap) {
			return ChatColor.GRAY + "DyLap";
		}
		return ChatColor.GRAY + "enchant.streep.type." + type;
	}


	public static Integer getType(String sr) {
		for(String ss : sr.split(" ")) {
			if(ss.equalsIgnoreCase(ChatColor.GRAY + "Speed")) {
				return Speed;
			}
			if(ss.equalsIgnoreCase(ChatColor.GRAY + "Strength")) {
				return strength;
			}
			if(ss.equalsIgnoreCase(ChatColor.GRAY + "Haste")) {
				return Haste;
			}
			if(ss.equalsIgnoreCase(ChatColor.GRAY + "NightVision")) {
				return NightVision;
			}
			if(ss.equalsIgnoreCase(ChatColor.GRAY + "DyLap")) {
				return DyLap;
			}
			if(ss.startsWith(ChatColor.GRAY + "enchant.streep.type.")) {
				String st = ss.replace(ChatColor.GRAY + "enchant.streep.type.", "");
				return Integer.parseInt(st);
			}
		}
		return null;
	}

	
	public static String toNumeral(int number) {
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

	public static int NumeralToInt(String num) {
		if(num.equalsIgnoreCase("I")) {
			return 1;
		}
		if(num.equalsIgnoreCase("II")) {
			return 2;
		}
		if(num.equalsIgnoreCase("III")) {
			return 3;
		}
		if(num.equalsIgnoreCase("IV")) {
			return 4;
		}
		if(num.equalsIgnoreCase("V")) {
			return 5;
		}
		if(num.equalsIgnoreCase("VI")) {
			return 6;
		}
		if(num.equalsIgnoreCase("VII")) {
			return 7;
		}
		if(num.equalsIgnoreCase("VIII")) {
			return 8;
		}
		if(num.equalsIgnoreCase("IX")) {
			return 9;
		}
		if(num.equalsIgnoreCase("X")) {
			return 10;
		}
		return Integer.parseInt(num);
	}


	public static int getMax(int type) {
		if(type == 0) {
			return 5;
		}
		if(type == 1) {
			return 5;
		}
		if(type == 2) {
			return 2;
		}
		if(type == 3) {
			return 1;
		}
		if(type == 4) {
			return 3;
		}
		return 1;
	}
	
	
}
