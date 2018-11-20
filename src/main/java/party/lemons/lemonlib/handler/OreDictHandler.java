package party.lemons.lemonlib.handler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import party.lemons.lemonlib.LemonLib;
import party.lemons.lemonlib.block.BlockRegistry;
import party.lemons.lemonlib.event.InitEvent;
import party.lemons.lemonlib.item.ItemRegistry;
import party.lemons.lemonlib.util.Pair;

@Mod.EventBusSubscriber(modid = LemonLib.MODID)
public class OreDictHandler
{
	@SubscribeEvent
	public static void onInit(InitEvent.Init event)
	{
		for(Pair<Item, String[]> pair : ItemRegistry.getOreDictEntries())
		{
			for(String entry : pair.getSecond())
			{
				OreDictionary.registerOre(entry, pair.getFirst());
			}
		}

		for(Pair<Block, String[]> pair : BlockRegistry.getOreDictEntries())
		{
			for(String entry : pair.getSecond())
			{
				OreDictionary.registerOre(entry, pair.getFirst());
			}
		}
	}
}
