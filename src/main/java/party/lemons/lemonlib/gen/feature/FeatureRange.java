package party.lemons.lemonlib.gen.feature;


import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public final class FeatureRange extends Feature
{
	private final int count;
	private final int minHeight;
	private final int maxHeight;

	public FeatureRange(WorldGenerator feature, int count, int minHeight, int maxHeight)
	{
		super(feature);
		this.count = count;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
	}

	@Override
	protected boolean doGenerate(World world, Random rand, BlockPos position)
	{
		boolean result = false;
		for(int n = count; n-- > 0; )
		{
			result |= generator.generate(world, rand, position.add(rand.nextInt(16), rand.nextInt(maxHeight - minHeight) + minHeight, rand.nextInt(16)));
		}
		return result;
	}
}