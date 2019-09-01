package party.lemons.lemonlib.config;


public class ConfigOreGeneration
{
	public int min_height;

	public int max_height;

	public int vein_size;

	public int vein_count;

	public ConfigOreGeneration(int min_height, int max_height, int vein_size, int vein_count)
	{
		this.min_height = min_height;
		this.max_height = max_height;
		this.vein_size = vein_size;
		this.vein_count = vein_count;
	}
}
