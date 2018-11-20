package party.lemons.lemonlib.handler.cooldown;

import net.minecraft.entity.player.EntityPlayer;

public class CooldownUtil
{
	public static ICooldownHandler getCooldowns(EntityPlayer player)
	{
		return player.getCapability(CapabilityCooldownHandler.CAPABILITY, null);
	}

	public static void setCooldown(EntityPlayer player, String key, int ticks)
	{
		getCooldowns(player).setCooldown(key, ticks);
	}

	public static int getCooldown(EntityPlayer player, String key)
	{
		return 	getCooldowns(player).getCooldown(key);
	}

	public static boolean isOnCooldown(EntityPlayer player, String key)
	{
		return getCooldowns(player).isOnCooldown(key);
	}

	public static void updateCooldown(EntityPlayer player)
	{
		getCooldowns(player).updateCooldown();
	}
}
