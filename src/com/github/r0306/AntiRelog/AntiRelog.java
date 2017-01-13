package com.github.r0306.AntiRelog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.r0306.AntiRelog.Listeners.DamageListener;
import com.github.r0306.AntiRelog.Listeners.DeathListener;
import com.github.r0306.AntiRelog.Listeners.FreezeCommand;
import com.github.r0306.AntiRelog.Listeners.LogPrevention;
import com.github.r0306.AntiRelog.Util.Colors;
import com.github.r0306.AntiRelog.Util.Plugin;
import com.github.r0306.AntiRelog.Util.Util;

public class AntiRelog extends JavaPlugin implements Colors {

	@Override
	public void onEnable() {
		new Plugin(this);
		loadConfiguration();
		registerExecutors();
		registerListeners();
	}

	@Override
	public void onDisable() {
		try {
			shutdownSequence();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void registerExecutors() {
		getCommand("antirelog").setExecutor(new Executor());
	}

	public void registerListeners() {
		getServer().getPluginManager().registerEvents(new DamageListener(), this);
		getServer().getPluginManager().registerEvents(new DeathListener(), this);
		getServer().getPluginManager().registerEvents(new LogPrevention(), this);
		getServer().getPluginManager().registerEvents(new FreezeCommand(), this);
	}

	public void shutdownSequence() throws IOException {
		CombatTracker.clearAll();
		Plugin.shutDown();
	}

	public void loadConfiguration() {
		FileConfiguration cfg = this.getConfig();
		FileConfigurationOptions cfgOptions = cfg.options();

		cfg.addDefault("PvP.Freeze-Duration", 7);
		cfg.addDefault("PvP.LeaveReasons", Arrays.asList(new String[] { "Disconnected", "disconnect.spam" }));

		cfg.addDefault("PvP.Tag-Message.Enabled", true);
		cfg.addDefault("PvP.Tag-Message.Tag", "<red>You have been tagged and are now in combat. Logging off will result in penalty.");
		cfg.addDefault("PvP.Tag-Message.Un-Tag", "<green>You are not in combat anymore.");

		cfg.addDefault("PvP.CombatLog.Drop.Items", true);
		cfg.addDefault("PvP.CombatLog.Drop.Armor", true);
		cfg.addDefault("PvP.CombatLog.Drop.Exp", true);

		cfg.addDefault("PvP.Command.Freeze-Message", "<red>There's no running away from a battle!");

		cfg.addDefault("PvP.Command.WhiteList", new ArrayList<String>());

		cfg.addDefault("Exclusions.DisabledWorlds", new ArrayList<String>());

		cfgOptions.copyDefaults(true);

		cfgOptions.header(getHeader());

		cfgOptions.copyHeader(true);

		saveConfig();
	}

	private String getHeader() {
		String newLine = Util.newLine;
		return "This is the AntiRelogLight configuration file." + newLine + "Editing this file with Notepad++ is strongly recommended." + newLine + "Save the file and reload the server after you are done editing for changes to take place." + newLine + "Here are the explanations for each option:" + newLine + newLine + "Freeze Duration: This defines the amount of time in SECONDS that players must wait before he can use commands or leave safely" + newLine + "LeaveReasons: If player leaves from combat with this messages in console than it is assumbed that the leave was intentional (not a sudden server error disconnect)" + newLine + newLine + "Tag Message: This is the message sent when players enter combat." + newLine + "UnTag Message: This is the message sent when players are not in combat anymore." + newLine + newLine + "Drops: If these are set to true, the player will drop that equipment/item upon combat logging." + newLine + newLine
				+ "Freeze Message: This sets the message shown if players try to use a command during PVP." + newLine + "WhiteList: List of allowed commands in combat" + newLine + newLine + "DisabledWorlds: The list of worlds at which plugin will be disabled";
	}

}
