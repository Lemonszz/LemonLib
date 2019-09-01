package party.lemons.lemonlib.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import party.lemons.lemonlib.LemonLib;
import party.lemons.lemonlib.item.ItemRegistry;
import party.lemons.lemonlib.util.Pair;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = LemonLib.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegistry
{
	private static List<Block> blockList = new ArrayList<>();

	private static String MODID = LemonLib.MODID;
	private static ItemGroup GROUP = null;
	private static IForgeRegistry<Block> REGISTRY;

	@SubscribeEvent
	public static void onRegisterItem(RegistryEvent.Register<Item> event)
	{
		blockList.stream().filter(b->(b instanceof HasItem) && ((HasItem) b).hasItem()).forEach(b->registerItemBlock(event.getRegistry(), (HasItem) b));
	}

	public static void registerItemBlock(IForgeRegistry<Item> registry, HasItem block)
	{
		Item it = block.createItem(new Item.Properties().group(GROUP));
		ItemRegistry.getItemList().add(it);
		registry.register(it);
	}

	public static Block registerBlock(Block block, String name)
	{
		return registerBlock(block, name, MODID);
	}

	public static Block registerBlock(Block block, String name, String domain)
	{
		block.setRegistryName(domain, name);

		blockList.add(block);
		REGISTRY.register(block);

		return block;
	}

	public static void setup(String modid, IForgeRegistry<Block> registry, ItemGroup group)
	{
		MODID = modid;
		GROUP = group;
		REGISTRY = registry;
	}

	public static List<Block> getBlockList()
	{
		return blockList;
	}
}
