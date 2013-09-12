package com.github.r0306.AntiRelog.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.github.r0306.AntiRelog.Storage.DataBase;

public class AntiTeleportListener implements Listener {
	
	
	@EventHandler (priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPVPTeleport(PlayerTeleportEvent event)
	{
		Player player = event.getPlayer();	
		
		if (DataBase.isInCombat(player))
		{
			event.setCancelled(true);
		}
	}

}
