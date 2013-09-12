package com.github.r0306.AntiRelog.Listeners;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import com.github.r0306.AntiRelog.AntiRelog;
import com.github.r0306.AntiRelog.Storage.DataBase;
import com.github.r0306.AntiRelog.Util.Clock;
import com.github.r0306.AntiRelog.Util.Colors;
import com.github.r0306.AntiRelog.Util.Configuration;

public class LogPrevention implements Listener, Colors
{

	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) throws IOException
	{
		
		Player player = event.getPlayer();

		if (DataBase.isInCombat(player))
		{
			
			long end = DataBase.getEndingTime(player);
			Set<Integer> items = new HashSet<Integer>();
			
			if (!Clock.isEnded(end))
			{


				if (!(player.getGameMode() == GameMode.CREATIVE))
				{
						
						if (Configuration.dropItemsEnabled())
						{
							
							dropItems(player);
							items.add(0);
							
						}
						
						if (Configuration.dropArmorEnabled())
						{
							
							dropArmor(player);
							items.add(1);
							
						}
						
						if (Configuration.dropExpEnabled())
						{
							
							dropExp(player);
							items.add(2);
							
						}
												
				}
				
					if (Configuration.broadcastEnabled())
					{
						
						Bukkit.broadcastMessage(name + green + player.getName() + " Вышел во время боя.");
						Bukkit.broadcastMessage(Configuration.getBroadcastMessage().replaceAll("<player>", player.getName()));
						
					}
					
					DataBase.addToLoginQueue(player.getName(), items);
					
					DataBase.banPlayer(player);
					
					AntiRelog.logger.log(player.getName(), null, 0);
					
					Clock.scheduleDelayedUnban(player);
										
				
				
			}
			
		}
			
	}
	
	public static void dropItems(HumanEntity player)
	{
		
			for (ItemStack i : player.getInventory().getContents())
			{
			 
				if (i != null)
				{
			    
					player.getWorld().dropItemNaturally(player.getLocation(), i);
					player.getInventory().remove(i);
			    
				}
			
			}

	}
	
	public static void dropArmor(HumanEntity player)
	{
		
        for (ItemStack armor : player.getInventory().getArmorContents())
        {
        	
        	if (armor.getAmount() != 0)
        	{
        	
        		player.getWorld().dropItemNaturally(player.getLocation(), armor);

        		player.getInventory().setArmorContents(new ItemStack[4]);
        	
        	}
        
        }
		
	}
	
	public static void dropExp(Player player)
	{
		
   		float Exp = player.getExp();
   		
   		int ExpOrbs = (int) Exp;
   		
   		int Level = player.getLevel();
   		
   		int ExpTotal = ((2 * Level * Level) + (5 * ExpOrbs)) / 5;
   		
   		World world = player.getWorld();
   		
   		for (int i = 0; i < 6; i ++)
   		{
   	    
   			((ExperienceOrb)world.spawn(player.getLocation(), ExperienceOrb.class)).setExperience( ExpTotal );
		
   		}
	    
   		player.setLevel( 0 );
   	    
   		player.setExp( 0 );
   		
	}
		
}
