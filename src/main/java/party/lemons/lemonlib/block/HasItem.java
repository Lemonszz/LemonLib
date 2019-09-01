package party.lemons.lemonlib.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import javax.annotation.Nullable;

public interface HasItem
{
	@Nullable
	default Item createItem(Item.Properties props)
	{
		if(this instanceof Block)
		{
			Block bl = (Block) this;
			BlockItem item = new BlockItem(bl, props);
			item.setRegistryName(bl.getRegistryName());

			return item;
		}

		return null;
	}

	default boolean hasItem()
	{
		return true;
	}
}
