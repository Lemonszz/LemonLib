package party.lemons.lemonlib.test;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import party.lemons.lemonlib.event.InitEvent;

public class EventTests
{
	@SubscribeEvent
	public static void onPreInit(InitEvent.Pre event)
	{
		System.out.println("Pre Init Event Fired");
	}

	@SubscribeEvent
	public static void onPostInit(InitEvent.Init event)
	{
		System.out.println("Init Event Fired");
	}

	@SubscribeEvent
	public static void onPostInit(InitEvent.Post event)
	{
		System.out.println("Post Init Event Fired");
	}
}
