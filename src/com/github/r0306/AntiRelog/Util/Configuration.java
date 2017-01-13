package com.github.r0306.AntiRelog.Util;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class Configuration implements Colors {

	private static FileConfiguration config = Plugin.getPlugin().getConfig();

	private static boolean tagMessageEnabled = config.getBoolean("PvP.Tag-Message.Enabled");

	private static String tagMessage = Util.colorizeText(config.getString("PvP.Tag-Message.Tag"));

	private static int freezeDuration = config.getInt("PvP.Freeze-Duration");

	private static boolean dropItems = config.getBoolean("PvP.CombatLog.Drop.Items");

	private static boolean dropExp = config.getBoolean("PvP.CombatLog.Drop.Exp");

	private static String freezeMessage = Util.colorizeText(config.getString("PvP.Command.Freeze-Message"));

	private static List<String> whiteList = config.getStringList("PvP.Command.WhiteList");

	private static List<String> excludedWorlds = config.getStringList("Exclusions.DisabledWorlds");

	public static boolean dropItemsEnabled() {
		return dropItems;
	}

	public static boolean dropExpEnabled() {
		return dropExp;
	}

	public static boolean messagesEnabled() {
		return tagMessageEnabled;
	}

	public static String getTagMessage() {
		return tagMessage;
	}

	public static int getFreezeDuration() {
		return freezeDuration * 20;
	}

	public static String getFreezeMessage() {
		return freezeMessage;
	}

	public static List<String> getWhiteList() {
		return whiteList;
	}

	public static List<String> getExcludedWorlds() {
		return excludedWorlds;
	}

}
