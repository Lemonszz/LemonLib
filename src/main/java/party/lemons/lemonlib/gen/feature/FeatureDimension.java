package party.lemons.lemonlib.gen.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class FeatureDimension extends Feature
{
	private final int[] dims;

	public FeatureDimension(WorldGenerator feature, int... dims)
	{
		super(feature);
		this.dims = dims;
	}

	@Override
	protected boolean doGenerate(World world, Random rand, BlockPos position)
	{
		for(int i = 0; i < dims.length; i++)
		{
			if(dims[i] == world.provider.getDimension()) return true;
		}
		return false;
	}
}