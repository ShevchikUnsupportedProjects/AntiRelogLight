package com.github.r0306.AntiRelog.Util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Util
{

	public static final String newLine = System.getProperty( "line.separator" );

	public static String colorizeText(String string) {

		string = string.replaceAll("<black>", ChatColor.BLACK+"");
		string = string.replaceAll("<darkblue>", ChatColor.DARK_BLUE+"");
		string = string.replaceAll("<darkgreen>", ChatColor.DARK_GREEN+"");
		string = string.replaceAll("<darkaqua>", ChatColor.DARK_AQUA+"");
		string = string.replaceAll("<darkred>", ChatColor.DARK_RED+"");
		string = string.replaceAll("<darkpurple>", ChatColor.DARK_PURPLE+"");
		string = string.replaceAll("<gold>", ChatColor.GOLD+"");
		string = string.replaceAll("<gray>", ChatColor.GRAY+"");
		string = string.replaceAll("<darkgray>", ChatColor.DARK_GRAY+"");
		string = string.replaceAll("<blue>", ChatColor.BLUE+"");
		string = string.replaceAll("<green>", ChatColor.GREEN+"");
		string = string.replaceAll("<aqua>", ChatColor.AQUA+"");
		string = string.replaceAll("<red>", ChatColor.RED+"");
		string = string.replaceAll("<lightpurple>", ChatColor.LIGHT_PURPLE+"");
		string = string.replaceAll("<yellow>", ChatColor.YELLOW+"");
		string = string.replaceAll("<white>", ChatColor.WHITE+"");

		return string;

	}

	public static String replacePlayerName(String string, Player player)
	{

		return string.replaceAll("<player>", player.getName());

	}

	public static boolean canBypass(Player player)
	{

		return player.hasPermission("antirelog.pvpbypass");

	}

	public static boolean canUnban(CommandSender sender)
	{

		return sender.hasPermission("antirelog.unban");

	}

}
