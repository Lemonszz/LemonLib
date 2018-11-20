package party.lemons.lemonlib.handler.cooldown;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class CooldownCapabilityProvider implements INBTSerializable<NBTTagCompound>, ICapabilityProvider
{
	private final CooldownContainer container;

	public CooldownCapabilityProvider(CooldownContainer cooldownContainer)
	{
		this.container = cooldownContainer;
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
	{
		return capability == CapabilityCooldownHandler.CAPABILITY;
	}

	@Nullable
	@Override
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
	{
		if(capability == CapabilityCooldownHandler.CAPABILITY)
			return (T)this.container;

		return null;
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound tagCompound = new NBTTagCompound();

		NBTTagList cooldowns = new NBTTagList();
		Map<String, Integer> entries = container.getCooldowns();
		for(Map.Entry<String, Integer> entry : entries.entrySet())
		{
			if(entry.getValue() > 0)
			{
				NBTTagCompound cd = new NBTTagCompound();

				cd.setString("key", entry.getKey());
				cd.setInteger("value", entry.getValue());

				cooldowns.appendTag(cd);
			}
		}

		if(cooldowns.tagCount() > 0)
			tagCompound.setTag("cooldowns", cooldowns);

		return tagCompound;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		if(nbt.hasKey("cooldowns"))
		{
			NBTTagList cooldowns = nbt.getTagList("cooldowns", Constants.NBT.TAG_COMPOUND);
			for(int i = 0; i < cooldowns.tagCount(); i++)
			{
				NBTTagCompound entry = cooldowns.getCompoundTagAt(i);

				container.setCooldown(entry.getString("key"), entry.getInteger("value"));
			}
		}
	}
}
