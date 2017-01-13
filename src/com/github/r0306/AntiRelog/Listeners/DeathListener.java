package com.github.r0306.AntiRelog.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.github.r0306.AntiRelog.CombatTracker;

public class DeathListener implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		CombatTracker.removeFromCombat(event.getEntity());
	}

}
