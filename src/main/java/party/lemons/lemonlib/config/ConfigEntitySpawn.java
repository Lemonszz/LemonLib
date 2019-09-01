package party.lemons.lemonlib.config;

public class ConfigEntitySpawn
{
	public int rarity;

	public int min;

	public int max;

	public boolean enabled;

	public ConfigEntitySpawn(int rarity, int min, int max, boolean enabled)
	{

		this.rarity = rarity;
		this.min = min;
		this.max = max;
		this.enabled = enabled;
	}
}
