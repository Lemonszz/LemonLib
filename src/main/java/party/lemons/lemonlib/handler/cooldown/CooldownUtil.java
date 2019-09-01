package party.lemons.lemonlib.handler.cooldown;

import net.minecraft.entity.player.PlayerEntity;

public class CooldownUtil
{
	public static ICooldownHandler getCooldowns(PlayerEntity player)
	{
		return player.getCapability(CapabilityCooldownHandler.CAPABILITY, null).orElse(null);
	}

	public static void setCooldown(PlayerEntity player, String key, int ticks)
	{
		getCooldowns(player).setCooldown(key, ticks);
	}

	public static int getCooldown(PlayerEntity player, String key)
	{
		return 	getCooldowns(player).getCooldown(key);
	}

	public static boolean isOnCooldown(PlayerEntity player, String key)
	{
		return getCooldowns(player).isOnCooldown(key);
	}

	public static void updateCooldown(PlayerEntity player)
	{
		getCooldowns(player).updateCooldown();
	}
}
