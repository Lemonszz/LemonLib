package party.lemons.lemonlib.handler.cooldown;

import java.util.HashMap;
import java.util.Map;

public class CooldownContainer implements ICooldownHandler
{
	private HashMap<String, Integer> cooldowns;

	public CooldownContainer()
	{
		cooldowns = new HashMap<>();
	}

	@Override
	public void setCooldown(String key, int ticks)
	{
		cooldowns.put(key, ticks);
	}

	@Override
	public int getCooldown(String key)
	{
		if(cooldowns.containsKey(key))
			return cooldowns.get(key);

		return 0;
	}

	@Override
	public boolean isOnCooldown(String key)
	{
		return getCooldown(key) > 0;
	}

	@Override
	public void updateCooldown()
	{
		for(Map.Entry<String, Integer> entry : cooldowns.entrySet())
		{
			entry.setValue(entry.getValue() - 1);
		}
	}

	@Override
	public Map<String, Integer> getCooldowns()
	{
		return cooldowns;
	}
}
