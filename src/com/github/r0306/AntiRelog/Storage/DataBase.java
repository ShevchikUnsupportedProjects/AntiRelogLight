package com.github.r0306.AntiRelog.Storage;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.github.r0306.AntiRelog.Util.Clock;

public class DataBase
{

	private static HashMap<String, Entity> lastDamager = new HashMap<String, Entity>();
	private static HashMap<String, Long> inCombat = new HashMap<String, Long>();
	private static HashMap<String, Integer> ids = new HashMap<String, Integer>();

	public static void clearAll()
	{

		lastDamager = null;
		inCombat = null;
		ids = null;

	}

	public static Entity getLastTarget(Player player)
	{

		for (String s : lastDamager.keySet())
		{

			if (lastDamager.get(s) instanceof Player)
			{

				if (((Player) lastDamager.get(s)).getName().equalsIgnoreCase(player.getName()))
				{

					return Bukkit.getPlayer(s);

				}

			}

		}

		return null;

	}

	public static Entity getLastDamager(Player player)
	{

		if (containsLastDamager(player))
		{

			return lastDamager.get(player.getName());

		}

		return null;

	}

	public static Entity getLastOpponent(Player player)
	{

		if (getLastDamager(player) != null)
		{

			return getLastDamager(player);

		}

		return getLastTarget(player);

	}

	public static void setLastDamager(Player player, Entity entity)
	{

		lastDamager.put(player.getName(), entity);

		Clock.scheduleRemoveEntity(player, entity);

	}

	public static void removeLastDamager(Player player)
	{

		if (containsLastDamager(player))
		{

			lastDamager.remove(player.getName());

		}

	}

	public static boolean containsLastDamager(Player player)
	{

		return lastDamager.containsKey(player.getName());

	}


	public static void addInCombat(Player player, Long time)
	{

		inCombat.put(player.getName(), time);

	}

	public static void removeFromCombat(Player player)
	{

		if (isInCombat(player))
		{

			inCombat.remove(player.getName());

			if (isScheduled(player))
			{

				Bukkit.getScheduler().cancelTask(getTaskId(player));

			}

		}

	}

	public static boolean isInCombat(Player player)
	{

		return inCombat.containsKey(player.getName());

	}

	public static long getEndingTime(Player player)
	{

		return isInCombat(player) ? inCombat.get(player.getName()) : -1;

	}

	public static void registerTask(Player player, int id)
	{

		ids.put(player.getName(), id);

	}

	public static void removeTask(Player player)
	{

		if (isScheduled(player))
		{

			ids.remove(player.getName());

		}

	}

	public static boolean isScheduled(Player player)
	{

		return ids.containsKey(player.getName());

	}

	public static int getTaskId(Player player)
	{

		if (isScheduled(player))
		{

			return ids.get(player.getName());

		}

		return -1;

	}


}
