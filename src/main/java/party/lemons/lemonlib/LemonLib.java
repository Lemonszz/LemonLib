package party.lemons.lemonlib;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import party.lemons.lemonlib.config.LemonLibConfig;
import party.lemons.lemonlib.event.InitEvent;
import party.lemons.lemonlib.test.EventTests;

@Mod(modid = LemonLib.MODID, name = LemonLib.NAME, version = LemonLib.VERSION)
public class LemonLib
{
	public static final String MODID = "lemonlib";
	public static final String NAME = "LemonLib";
	public static final String VERSION = "1.0.0";

	public static final Logger LOGGER = LogManager.getLogger(MODID);

	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		if(LemonLibConfig.DEBUG)
		{
			MinecraftForge.EVENT_BUS.register(EventTests.class);
		}

		MinecraftForge.EVENT_BUS.post(new InitEvent.Pre(event));
	}

	@Mod.EventHandler
	public void onInit(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.post(new InitEvent.Init(event));
	}

	@Mod.EventHandler
	public void onPostInit(FMLPostInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.post(new InitEvent.Post(event));
	}
}
