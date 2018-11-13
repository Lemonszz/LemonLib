package party.lemons.lemonlib.block;

import com.mojang.realmsclient.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import party.lemons.lemonlib.LemonLib;
import party.lemons.lemonlib.item.IItemModel;
import party.lemons.lemonlib.item.ItemRegistry;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = LemonLib.MODID)
public class BlockRegistry
{
	private static List<Block> blockList = new ArrayList<>();
	private static List<Pair<Block, String[]>> oreDict = new ArrayList<>();

	private static String MODID = LemonLib.MODID;
	private static CreativeTabs TAB = null;
	private static IForgeRegistry<Block> REGISTRY;

	@SubscribeEvent
	public static void onRegisterItem(RegistryEvent.Register<Item> event)
	{
		blockList.stream().filter(b->(b instanceof IItemModel) && ((IItemModel) b).hasModel()).forEach(b->registerItemBlock(event.getRegistry(), b));
	}

	public static void registerItemBlock(IForgeRegistry<Item> registry, Block block)
	{
		ItemBlock ib = new ItemBlock(block);
		ib.setRegistryName(block.getRegistryName());

		ItemRegistry.getItemList().add(ib);
		registry.register(ib);
	}

	public static Block setProperties(Block block, float hardness, float resistence, float light)
	{
		return block.setHardness(hardness).setResistance(resistence).setLightLevel(light);
	}

	public static Block registerBlock(Block block, String name, String... oreDict)
	{
		return registerBlock(block, name, MODID, true, oreDict);
	}

	public static Block registerBlock(Block block, String name, String domain, boolean addDomainToUnloc, String... ores)
	{
		String unloc = addDomainToUnloc ? (domain + ".") : "";

		block.setTranslationKey(unloc + name);
		block.setRegistryName(domain, name);

		if(TAB != null)
		{
			block.setCreativeTab(TAB);
		}
		if(ores.length > 0) oreDict.add(Pair.of(block, ores));

		blockList.add(block);
		REGISTRY.register(block);

		return block;
	}

	public static void setup(String modid, IForgeRegistry<Block> registry, CreativeTabs tab)
	{
		MODID = modid;
		TAB = tab;
		REGISTRY = registry;
	}

	public static List<Block> getBlockList()
	{
		return blockList;
	}

	public static List<Pair<Block, String[]>> getOreDictEntries()
	{
		return oreDict;
	}
}
