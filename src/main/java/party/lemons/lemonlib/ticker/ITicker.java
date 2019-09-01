package party.lemons.lemonlib.ticker;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public interface ITicker
{
	/*
		ITickers MUST have a constructor that ONLY takes a world
	*/

	void update(World world);

	CompoundNBT writeToNBT();

	void readFromNBT(CompoundNBT tagCompound);

	boolean isTaskFinished(World world);

	int getDimension();

	boolean isUnique();
}