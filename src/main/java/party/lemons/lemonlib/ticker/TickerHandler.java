package party.lemons.lemonlib.ticker;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import party.lemons.lemonlib.LemonLib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mod.EventBusSubscriber(modid = LemonLib.MODID)
public class TickerHandler
{
	private static HashMap<Integer, List<ITicker>> worldTickers = new HashMap<>();
	private static List<Class<ITicker>> registeredTickers = new ArrayList<>();

	public static void addTicker(ITicker ticker, int dim)
	{
		if(!worldTickers.containsKey(dim)) worldTickers.put(dim, new ArrayList<>());

		//Don't have more than 1 instance of unique tickers
		if(ticker.isUnique() && hasTickerOfType(dim, ticker.getClass())) return;

		worldTickers.get(dim).add(ticker);
	}

	private static boolean hasTickerOfType(int dim, Class<? extends ITicker> clazz)
	{
		if(!worldTickers.containsKey(dim)) return false;

		return worldTickers.get(dim).stream().anyMatch(t->t.getClass() == clazz);
	}

	private static void removeFinishedTickers(World world)
	{
		for(List<ITicker> tickerList : worldTickers.values())
		{
			tickerList.removeIf(t->t.isTaskFinished(world));
		}
	}

	private static void tick(World world)
	{
		if(!worldTickers.containsKey(world.getDimension())) return;

		List<ITicker> tickerList = worldTickers.get(world.getDimension());
		tickerList.stream().forEach(t->t.update(world));
	}

	public static <T extends ITicker> void registerTicker(Class<T> clazz)
	{
		registeredTickers.add((Class<ITicker>) clazz);
	}

	public static ListNBT writeToNBT()
	{
		ListNBT list = new ListNBT();
		for(List<ITicker> tickerList : worldTickers.values())
		{
			for(ITicker ticker : tickerList)
			{
				CompoundNBT tickerTags = ticker.writeToNBT();
				tickerTags.putInt("key", registeredTickers.indexOf(ticker.getClass()));
				tickerTags.putInt("dim", ticker.getDimension());

				list.add(tickerTags);
			}
		}
		return list;
	}

	public static void readFromNBT(ListNBT tickerList)
	{
		worldTickers.clear();

		World world = ServerLifecycleHooks.getCurrentServer().getWorld(DimensionType.OVERWORLD);
		for(int i = 0; i < tickerList.size(); i++)
		{
			CompoundNBT tag = tickerList.getCompound(i);
			Class<ITicker> clazz = registeredTickers.get(tag.getInt("key"));
			int dimID = tag.getInt("dim");
			try
			{
				ITicker ticker = clazz.getConstructor(World.class).newInstance(world);
				ticker.readFromNBT(tag);
				addTicker(ticker, dimID);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	@SubscribeEvent
	public static void onWorldTick(TickEvent.WorldTickEvent event)
	{
		if(event.phase == TickEvent.Phase.START) return;

		tick(event.world);
		removeFinishedTickers(event.world);
	}

	@SubscribeEvent
	public static void onWorldLoad(WorldEvent.Load event)
	{
		if(event.getWorld() instanceof World)
		{
		//	TickerSavedData.get((World) event.getWorld());
		}
	}
}