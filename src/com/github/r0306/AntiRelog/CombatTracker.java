package com.github.r0306.AntiRelog;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public class CombatTracker {

	private static HashMap<UUID, Long> inCombat = new HashMap<UUID, Long>();

	public static void clearAll() {
		inCombat.clear();
	}

	public static void addInCombat(Player player, Long time) {
		inCombat.put(player.getUniqueId(), time);
	}

	public static void removeFromCombat(Player player) {
		inCombat.remove(player.getUniqueId());
	}

	public static boolean isInCombat(Player player) {
		return inCombat.containsKey(player.getName());
	}

	public static long getEndingTime(Player player) {
		return isInCombat(player) ? inCombat.get(player.getName()) : -1;
	}

}
