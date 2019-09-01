package party.lemons.lemonlib.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.registries.IForgeRegistry;
import party.lemons.lemonlib.LemonLib;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry
{
	public static List<Item> itemList = new ArrayList<>();

	private static String MODID = LemonLib.MODID;
	private static ItemGroup GROUP = null;
	private static IForgeRegistry<Item> REGISTRY;

	public static Item registerItem(Item item, String name, String... oredict)
	{
		item.setRegistryName(MODID, name);

		itemList.add(item);
		REGISTRY.register(item);

		return item;
	}

	public static void setup(String modid, IForgeRegistry<Item> registry, ItemGroup group)
	{
		MODID = modid;
		REGISTRY = registry;
		GROUP = group;
	}

	public static List<Item> getItemList()
	{
		return itemList;
	}
}
