package party.lemons.lemonlib.item;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IItemModel
{
	default boolean hasModel()
	{
		return true;
	}

	default ResourceLocation getModelLocation()
	{
		if(this instanceof IForgeRegistryEntry)
		{
			return ((IForgeRegistryEntry)this).getRegistryName();
		}
		return null;
	}
}
