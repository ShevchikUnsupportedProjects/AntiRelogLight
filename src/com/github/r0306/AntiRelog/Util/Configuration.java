package com.github.r0306.AntiRelog.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;


public class Configuration implements Colors
{

	private static FileConfiguration config = Plugin.getPlugin().getConfig();

	private static boolean tagMessageEnabled = config.getBoolean("PvP.Tag-Message.Enabled");

	private static String tagMessage = Util.colorizeText(config.getString("PvP.Tag-Message.Tag"));

	private static String unTagMessage = Util.colorizeText(config.getString("PvP.Tag-Message.Un-Tag"));
	
	private static Set<String> invalidLeaveReasons = new HashSet<String>(config.getStringList("PvP.LeaveReasons"));
	
	private static int freezeDuration = config.getInt("PvP.Freeze-Duration");

	private static boolean dropItems = config.getBoolean("PvP.CombatLog.Drop.Items");

	private static boolean dropArmor = config.getBoolean("PvP.CombatLog.Drop.Armor");

	private static boolean dropExp = config.getBoolean("PvP.CombatLog.Drop.Exp");

	private static String freezeMessage = Util.colorizeText(config.getString("PvP.Command.Freeze-Message"));

	private static List<String> whiteList = config.getStringList("PvP.Command.WhiteList");

	private static List<String> excludedWorlds = config.getStringList("Exclusions.DisabledWorlds");

	public static boolean tagMessageEnabled()
	{

		return tagMessageEnabled;

	}

	public static String getTagMessage()
	{

		return tagMessage;

	}
	
	public static Set<String> getInvalidLeaveReasons()
	{
		
		return invalidLeaveReasons;
		
	}

	public static String getUnTagMessage()
	{

		return unTagMessage;

	}

	public static boolean dropItemsEnabled()
	{

		return dropItems;

	}

	public static boolean dropArmorEnabled()
	{

		return dropArmor;

	}

	public static boolean dropExpEnabled()
	{

		return dropExp;

	}

	public static int getFreezeDuration()
	{

		return freezeDuration * 20;

	}

	public static String getFreezeMessage()
	{

		return freezeMessage;

	}

	public static List<String> getWhiteList()
	{

		return whiteList;

	}

	public static List<String> getExcludedWorlds()
	{

		return excludedWorlds;

	}

}
