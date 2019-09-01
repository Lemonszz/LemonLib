package party.lemons.lemonlib.ticker;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;
import party.lemons.lemonlib.LemonLib;

public class TickerSavedData extends WorldSavedData
{
	public static final String DATA_NAME = LemonLib.MODID + "_TickerData";

	public TickerSavedData(String name)
	{
		super(name);
	}

	@Override
	public void read(CompoundNBT nbt)
	{
		ListNBT tickers = nbt.getList("tickers", Constants.NBT.TAG_COMPOUND);
		TickerHandler.readFromNBT(tickers);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound)
	{
		compound.put("tickers", TickerHandler.writeToNBT());

		return compound;
	}

	public TickerSavedData()
	{
		super(DATA_NAME);
	}

	public static TickerSavedData get(World world)
	{
		ServerWorld overworld = world.getServer().getWorld(DimensionType.OVERWORLD);

		DimensionSavedDataManager storage = overworld.getSavedData();
		return storage.getOrCreate(TickerSavedData::new, DATA_NAME);
	}
}