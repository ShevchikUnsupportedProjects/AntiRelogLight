package com.github.r0306.AntiRelog;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.r0306.AntiRelog.Util.Clock;
import com.github.r0306.AntiRelog.Util.Colors;

public class Executor implements CommandExecutor, Colors {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("t") || args[0].equalsIgnoreCase("time")) {
				if (isPlayer(sender)) {
					displayRemainingTime(player);
					return true;
				}
			}
		}
		displayUnknownCommand(sender);
		return true;

	}

	public boolean isPlayer(CommandSender sender) {
		if (sender instanceof Player) {
			return true;
		}
		sender.sendMessage(name + ChatColor.RED + "You must be a player to use this command!");
		return false;
	}

	public void displayRemainingTime(Player player) {
		if (CombatTracker.isInCombat(player)) {
			long end = CombatTracker.getEndingTime(player);
			if (!Clock.isEnded(end)) {
				long remaining = Clock.getElapsed(Clock.getTime(), end);
				player.sendMessage(name + ChatColor.DARK_AQUA + "You have " + remaining + " seconds before combat ends.");
			}
		} else {
			player.sendMessage(name + ChatColor.GREEN + "You are not currently in combat.");
		}
	}

	public void displayNoPermissions(CommandSender sender) {
		sender.sendMessage(name + ChatColor.RED + "You do not have permission!");
	}

	public void displayUnknownCommand(CommandSender sender) {
		sender.sendMessage(name + ChatColor.RED + "Unknown command.");
	}

}
