package com.github.r0306.AntiRelog.Util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Util {

	public static final String newLine = System.getProperty("line.separator");

	public static String colorizeText(String string) {
		string = string.replace("<black>", ChatColor.BLACK + "");
		string = string.replace("<darkblue>", ChatColor.DARK_BLUE + "");
		string = string.replace("<darkgreen>", ChatColor.DARK_GREEN + "");
		string = string.replace("<darkaqua>", ChatColor.DARK_AQUA + "");
		string = string.replace("<darkred>", ChatColor.DARK_RED + "");
		string = string.replace("<darkpurple>", ChatColor.DARK_PURPLE + "");
		string = string.replace("<gold>", ChatColor.GOLD + "");
		string = string.replace("<gray>", ChatColor.GRAY + "");
		string = string.replace("<darkgray>", ChatColor.DARK_GRAY + "");
		string = string.replace("<blue>", ChatColor.BLUE + "");
		string = string.replace("<green>", ChatColor.GREEN + "");
		string = string.replace("<aqua>", ChatColor.AQUA + "");
		string = string.replace("<red>", ChatColor.RED + "");
		string = string.replace("<lightpurple>", ChatColor.LIGHT_PURPLE + "");
		string = string.replace("<yellow>", ChatColor.YELLOW + "");
		string = string.replace("<white>", ChatColor.WHITE + "");
		return string;
	}

	public static boolean canBypass(Player player) {
		return player.hasPermission("antirelog.pvpbypass");
	}

}
