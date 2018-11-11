package party.lemons.lemonlib.event;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLStateEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public abstract class InitEvent<T extends FMLStateEvent> extends Event
{
	private final T event;

	public InitEvent(T event)
	{
		this.event = event;
	}

	public T getInitEvent()
	{
		return event;
	}

	public static class Pre extends InitEvent<FMLPreInitializationEvent>
	{
		public Pre(FMLPreInitializationEvent event)
		{
			super(event);
		}
	}

	public static class Init extends InitEvent<FMLInitializationEvent>
	{
		public Init(FMLInitializationEvent event)
		{
			super(event);
		}
	}

	public static class Post extends InitEvent<FMLPostInitializationEvent>
	{
		public Post(FMLPostInitializationEvent event)
		{
			super(event);
		}
	}
}
