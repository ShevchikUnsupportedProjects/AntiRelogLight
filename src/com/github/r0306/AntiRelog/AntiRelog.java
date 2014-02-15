package com.github.r0306.AntiRelog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.r0306.AntiRelog.Listeners.DamageListener;
import com.github.r0306.AntiRelog.Listeners.DeathListener;
import com.github.r0306.AntiRelog.Listeners.FreezeCommand;
import com.github.r0306.AntiRelog.Listeners.LogPrevention;
import com.github.r0306.AntiRelog.Loggers.ConsoleReader;
import com.github.r0306.AntiRelog.Storage.DataBase;
import com.github.r0306.AntiRelog.Util.Colors;
import com.github.r0306.AntiRelog.Util.Plugin;
import com.github.r0306.AntiRelog.Util.Util;

public class AntiRelog extends JavaPlugin implements Colors
{

	@Override
	public void onEnable()
	{

		new Plugin(this);

		loadConfiguration();

		registerExecutors();

		registerListeners();

		registerConsoleReader();

	}

	@Override
	public void onDisable()
	{
		try
		{

			shutdownSequence();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public void registerExecutors()
	{

		getCommand("antirelog").setExecutor(new Executor());

	}

	public void registerListeners()
	{
		getServer().getPluginManager().registerEvents(new DamageListener(), this);
		getServer().getPluginManager().registerEvents(new DeathListener(), this);
		getServer().getPluginManager().registerEvents(new LogPrevention(), this);
		getServer().getPluginManager().registerEvents(new FreezeCommand(), this);
	}

	public void registerConsoleReader()
	{
		((org.apache.logging.log4j.core.Logger) LogManager.getRootLogger()).addFilter(new ConsoleReader());
	}


	public void shutdownSequence() throws IOException
	{

		DataBase.clearAll();

		Plugin.shutDown();

	}

	public void loadConfiguration()
	{

		FileConfiguration cfg = this.getConfig();
		FileConfigurationOptions cfgOptions = cfg.options();

		cfg.addDefault("PvP.Tag-Message.Enabled", true);
		cfg.addDefault("PvP.Tag-Message.Tag", "<red>You have been tagged and are now in combat. Logging off will result in penalty.");
		cfg.addDefault("PvP.Tag-Message.Un-Tag", "<green>You are not in combat anymore.");

		cfg.addDefault("PvP.CombatLog.Drop.Items", true);
		cfg.addDefault("PvP.CombatLog.Drop.Armor", true);
		cfg.addDefault("PvP.CombatLog.Drop.Exp", true);

		cfg.addDefault("PvP.Command.Disallow-All", false);

		cfg.addDefault("PvP.Command.Freeze-Duration", 7);
		cfg.addDefault("PvP.Command.Freeze-Message", "<red>There's no running away from a battle!");

		cfg.addDefault("PvP.Command.Disallowed-List", Arrays.asList("tp", "warp", "home", "tpa", "creative"));

		cfg.addDefault("PvP.Command.WhiteList", new ArrayList<String>());

		cfg.addDefault("Exclusions.DisabledWorlds", new ArrayList<String>());

		cfgOptions.copyDefaults(true);

		cfgOptions.header(getHeader());

		cfgOptions.copyHeader(true);

		saveConfig();

	}

	private String getHeader()
	{

		String newLine = Util.newLine;

		return "This is the AntiRelogLight configuration file." + newLine +
				"Editing this file with Notepad++ is strongly recommended." + newLine +
				"Save the file and reload the server after you are done editing for changes to take place." + newLine +
				"Here are the explanations for each option:" + newLine +
				"Tag Message: This is the message sent when players enter combat." + newLine +
				"UnTag Message: This is the message sent when players are not in combat anymore." + newLine + newLine +
				"Drops: If these are set to true, the player will drop that equipment/item upon combat logging. If NPCs are enabled, the NPC will drop those items upon being killed." + newLine + newLine +
				"Disallow All: Set this to true to disallow all commands while in PVP. (Overrides command list)." + newLine +
				"Freeze Duration: This defines the amount of time in SECONDS that players must wait before using commands if they are hit. Set to 0 if you want to disable this feature." + newLine +
				"Freeze Message: This sets the message shown if players try to use a command during PVP." + newLine +
				"DisallowedCMDs: This is a list of the disallowed commands when Disallow All is off. Add to the list using the same format as shown. Set first line to 'null' to allow all commands." + newLine +
				"WhiteList: If Disallow All is on, commands in this list will be the only ones allowed. Set first line to 'null' to disallow all commands while using Disallow All." + newLine + newLine +
				"DisabledWorlds: The list of worlds at which plugin will be disabled";

	}

}
