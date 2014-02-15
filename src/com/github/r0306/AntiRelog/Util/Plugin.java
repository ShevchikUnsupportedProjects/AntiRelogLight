package com.github.r0306.AntiRelog.Util;

import com.github.r0306.AntiRelog.AntiRelog;

public class Plugin
{

	private static AntiRelog plugin;

	public Plugin(AntiRelog plugin)
	{

		Plugin.plugin = plugin;

	}

	public static AntiRelog getPlugin()
	{

		return plugin;

	}

	public static void shutDown()
	{

		plugin = null;

	}

}
