package party.lemons.lemonlib.gen.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;


public abstract class Feature extends WorldGenerator
{
	protected final WorldGenerator generator;

	public Feature(WorldGenerator worldGenerator)
	{
		this.generator = worldGenerator;
	}

	public boolean generate(World world, Random rand, BlockPos position)
	{
		if(doGenerate(world, rand, position)) return generator.generate(world, rand, position);

		return false;
	}

	protected abstract boolean doGenerate(World world, Random rand, BlockPos position);
}
