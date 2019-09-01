package party.lemons.lemonlib.handler.cooldown;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class CooldownCapabilityProvider implements INBTSerializable<CompoundNBT>, ICapabilityProvider
{
	private final LazyOptional<ICooldownHandler> container;

	public CooldownCapabilityProvider(CooldownContainer cooldownContainer)
	{
		container = LazyOptional.of(()->cooldownContainer);
	}

	@Override
	public CompoundNBT serializeNBT()
	{
		CompoundNBT tagCompound = new CompoundNBT();

		ListNBT cooldowns = new ListNBT();
		ICooldownHandler cdh = container.orElse(null);

		Map<String, Integer> entries = cdh.getCooldowns();
		for(Map.Entry<String, Integer> entry : entries.entrySet())
		{
			if(entry.getValue() > 0)
			{
				CompoundNBT cd = new CompoundNBT();

				cd.putString("key", entry.getKey());
				cd.putInt("value", entry.getValue());

				cooldowns.add(cd);
			}
		}

		if(cooldowns.size() > 0)
			tagCompound.put("cooldowns", cooldowns);

		return tagCompound;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt)
	{
		if(nbt.contains("cooldowns"))
		{
			ListNBT cooldowns = nbt.getList("cooldowns", Constants.NBT.TAG_COMPOUND);
			for(int i = 0; i < cooldowns.size(); i++)
			{
				CompoundNBT entry = cooldowns.getCompound(i);
				ICooldownHandler cd = container.orElse(null);

				cd.setCooldown(entry.getString("key"), entry.getInt("value"));
			}
		}
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
	{
		return container.cast();
	}
}
