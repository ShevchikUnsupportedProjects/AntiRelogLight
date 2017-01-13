package com.github.r0306.AntiRelog.Listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.github.r0306.AntiRelog.CombatTracker;
import com.github.r0306.AntiRelog.Util.Clock;
import com.github.r0306.AntiRelog.Util.Colors;
import com.github.r0306.AntiRelog.Util.Configuration;
import com.github.r0306.AntiRelog.Util.Util;

public class DamageListener implements Listener, Colors {

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onDamage(EntityDamageByEntityEvent event) {
		Entity victim = event.getEntity();
		Entity attacker = event.getDamager();

		if (Configuration.getExcludedWorlds().contains(victim.getWorld().getName()) || Configuration.getExcludedWorlds().contains(attacker.getWorld().getName())) {
			return;
		}

		if (event.getCause() != DamageCause.CUSTOM && event.getDamage() >= 0) {
			Player victimPlayer = victim instanceof Player ? (Player) victim : null;
			Player attackerPlayer = attacker instanceof Player ? (Player) attacker : null;
			if (victimPlayer != null && attackerPlayer != null) {
				tagPlayer(victimPlayer);
				tagPlayer(attackerPlayer);
			} else if (victimPlayer != null && attacker instanceof Projectile) {
				tagPlayerProjectile(victimPlayer, (Projectile) attacker);
			}
		}
	}

	public void tagPlayerProjectile(Player player, Projectile projectile) {
		if (projectile.getShooter() instanceof Player) {
			Player attacker = (Player) projectile.getShooter();
			tagPlayer(player);
			tagPlayer(attacker);
		}
	}

	public void tagPlayer(Player player) {
		if (!Util.canBypass(player)) {
			if (Configuration.messagesEnabled() && !CombatTracker.isInCombat(player)) {
				player.sendMessage(name + Configuration.getTagMessage());
			}
			CombatTracker.addInCombat(player, Clock.getEnd());
		}
	}

}
