package party.lemons.lemonlib.util;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

public class InventoryUtil
{
	public static void dropInventoryItems(World worldIn, BlockPos pos, IItemHandler inventory)
	{
		dropInventoryItems(worldIn, (double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), inventory);
	}

	public static void dropInventoryItems(World worldIn, Entity entityAt, IItemHandler inventory)
	{
		dropInventoryItems(worldIn, entityAt.posX, entityAt.posY, entityAt.posZ, inventory);
	}

	private static void dropInventoryItems(World worldIn, double x, double y, double z, IItemHandler inventory)
	{
		for(int i = 0; i < inventory.getSlots(); ++i)
		{
			ItemStack itemstack = inventory.getStackInSlot(i);

			if(!itemstack.isEmpty())
			{
				InventoryHelper.spawnItemStack(worldIn, x, y, z, itemstack);
			}
		}
	}

	public static boolean ingredientMatch(Ingredient ingredient, ItemStack stack)
	{
		if(stack == null)
		{
			return false;
		}else
		{
			for(ItemStack itemstack : ingredient.getMatchingStacks())
			{
				if(itemstack.getItem() == stack.getItem())
				{
					if(itemstack.getCount() <= stack.getCount())
					{
						int i = itemstack.getMetadata();
						if(i == Short.MAX_VALUE || i == stack.getMetadata())
						{
							return true;
						}
					}
				}
			}
			return false;
		}
	}
}
