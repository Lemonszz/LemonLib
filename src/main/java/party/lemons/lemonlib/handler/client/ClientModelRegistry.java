package party.lemons.lemonlib.handler.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import party.lemons.lemonlib.LemonLib;
import party.lemons.lemonlib.item.IItemModel;
import party.lemons.lemonlib.item.ItemRegistry;

@Mod.EventBusSubscriber(modid = LemonLib.MODID, value = Side.CLIENT)
public class ClientModelRegistry
{
	@SubscribeEvent
	public static void onRegisterModel(ModelRegistryEvent event)
	{
		ItemRegistry.itemList.stream().filter(i->i instanceof IItemModel).forEach(i->registerModel((Item & IItemModel) i));
		ItemRegistry.itemList.stream().filter(i->i instanceof ItemBlock).forEach(ClientModelRegistry::registerSimpleModel);
	}

	public static <ModelItem extends Item & IItemModel> void registerModel(ModelItem item)
	{
		if(item == Items.AIR || !item.hasModel()) return;

		setModel(item, item.getModelLocation());
	}

	public static void registerSimpleModel(Item item)
	{
		if(item == Items.AIR) return;

		setModel(item, item.getRegistryName());
	}

	private static void setModel(Item item, ResourceLocation location)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(location, "inventory"));
	}

}
