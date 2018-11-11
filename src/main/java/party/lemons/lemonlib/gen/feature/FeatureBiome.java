package party.lemons.lemonlib.gen.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class FeatureBiome extends Feature
{
	private final Biome[] biomes;

	public FeatureBiome(WorldGenerator worldGenerator, Biome... biomes)
	{
		super(worldGenerator);

		this.biomes = biomes;
	}

	@Override
	protected boolean doGenerate(World world, Random rand, BlockPos position)
	{
		for(Biome biome : biomes)
		{
			if(world.getBiome(position) == biome) return true;
		}

		return false;
	}
}
