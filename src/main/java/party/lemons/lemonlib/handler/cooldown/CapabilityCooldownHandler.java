package party.lemons.lemonlib.handler.cooldown;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import party.lemons.lemonlib.LemonLib;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = LemonLib.MODID)
public class CapabilityCooldownHandler
{
	@CapabilityInject(ICooldownHandler.class)
	public static final Capability<ICooldownHandler> CAPABILITY = null;

	@SubscribeEvent
	public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof PlayerEntity)
		{
			event.addCapability(new ResourceLocation(LemonLib.MODID, "cooldown"), new CooldownCapabilityProvider(new CooldownContainer()));
		}
	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		if(event.phase == TickEvent.Phase.END)
		{
			CooldownUtil.updateCooldown(event.player);
		}
	}

	public static class Storage implements Capability.IStorage<ICooldownHandler>
	{
		@Nullable
		@Override
		public INBT writeNBT(Capability<ICooldownHandler> capability, ICooldownHandler instance, Direction side)
		{
			return null;
		}

		@Override
		public void readNBT(Capability<ICooldownHandler> capability, ICooldownHandler instance, Direction side, INBT nbt)
		{

		}
	}
}
