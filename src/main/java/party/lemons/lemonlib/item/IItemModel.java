package party.lemons.lemonlib.item;

import net.minecraft.util.ResourceLocation;

public interface IItemModel
{
	default boolean hasModel()
	{
		return true;
	}

	ResourceLocation getModelLocation();
}
