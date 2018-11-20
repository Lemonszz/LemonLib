package party.lemons.lemonlib.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import party.lemons.lemonlib.LemonLib;
import party.lemons.lemonlib.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry
{
	public static List<Item> itemList = new ArrayList<>();
	private static List<Pair<Item, String[]>> oreDict = new ArrayList<>();

	private static String MODID = LemonLib.MODID;
	private static CreativeTabs TAB = null;
	private static IForgeRegistry<Item> REGISTRY;

	public static Item registerItem(Item item, String name, String... oredict)
	{
		item.setTranslationKey(MODID + "." + name);
		item.setRegistryName(MODID, name);

		if(TAB != null)
		{
			item.setCreativeTab(TAB);
		}

		if(oredict.length > 0)
		{
			oreDict.add(Pair.of(item, oredict));
		}

		itemList.add(item);
		REGISTRY.register(item);

		return item;
	}

	public static void setup(String modid, IForgeRegistry<Item> registry, CreativeTabs tab)
	{
		MODID = modid;
		TAB = tab;
		REGISTRY = registry;
	}

	public static List<Item> getItemList()
	{
		return itemList;
	}

	public static List<Pair<Item, String[]>> getOreDictEntries()
	{
		return oreDict;
	}

}
