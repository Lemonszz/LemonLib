package party.lemons.lemonlib.handler.cooldown;

import java.util.Map;

public interface ICooldownHandler
{
	void setCooldown(String key, int ticks);
	int getCooldown(String key);
	boolean isOnCooldown(String key);
	void updateCooldown();
	Map<String, Integer> getCooldowns();
}
