package party.lemons.lemonlib.gen.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class FeatureChance extends Feature
{
	private final int chance;

	public FeatureChance(WorldGenerator feature, int chance)
	{
		super(feature);
		this.chance = chance;
	}

	@Override
	protected boolean doGenerate(World world, Random rand, BlockPos position)
	{
		return rand.nextInt(chance) == 0;
	}
}