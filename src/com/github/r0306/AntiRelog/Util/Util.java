package com.github.r0306.AntiRelog.Util;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;


public class Util
{

	public static String newLine = System.getProperty( "line.separator" );
	
	private static final String name = Plugin.getPlugin().getDescription().getName();
	
	private static final String version = Plugin.getPlugin().getDescription().getVersion();
	
	private static final String website = Plugin.getPlugin().getDescription().getWebsite();
	
	private static String newVersion;
	
	public static String getName()
	{
		
		return name;
		
	}
	
	public static String getVersion()
	{
		
		return version;
		
	}
	
	public static String getWebsite()
	{
		
		return website;
		
	}
	
	public static String getDownload()
	{
		
		return website.replaceAll("files.rss", "");
		
	}
	
	public static String getNewVersion()
	{
		
		return newVersion;
		
	}
	
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
	
	public static boolean aggroNPC(Player player)
	{
		
		return player.hasPermission("antirelog.aggressivenpc");
		
	}
	
	public static boolean citizensEnabled()
	{
		
		return Bukkit.getPluginManager().getPlugin("Citizens") != null && Bukkit.getPluginManager().getPlugin("Citizens").getDescription().getVersion().startsWith("1");
		
	}
	
	public static List<EntityType> getHostileMobs()
	{
		
		return Arrays.asList(EntityType.BLAZE, EntityType.CAVE_SPIDER, EntityType.CREEPER, EntityType.ENDER_DRAGON, EntityType.ENDERMAN,
							 EntityType.GHAST, EntityType.GIANT, EntityType.IRON_GOLEM, EntityType.MAGMA_CUBE, EntityType.PIG_ZOMBIE,
							 EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SLIME, EntityType.SPIDER, EntityType.WOLF, EntityType.ZOMBIE,
							 EntityType.WITCH, EntityType.WITHER, EntityType.WITHER_SKULL);
		
	}
	
	public static List<EntityType> getPassiveMobs()
	{
		
		return Arrays.asList(EntityType.BAT, EntityType.CHICKEN, EntityType.COW, EntityType.MUSHROOM_COW, EntityType.OCELOT, EntityType.PIG,
							 EntityType.SHEEP, EntityType.SNOWMAN, EntityType.SQUID, EntityType.VILLAGER);
		
	}

    
    public static String versionCorrect(String version)
    {

    	if (version.length() < 3)
    	{
    		
    		version += "0";
    		
    	}
    	
    	return version;
    	 	
    }
    
}
