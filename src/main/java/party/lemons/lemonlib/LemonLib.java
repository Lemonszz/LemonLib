package party.lemons.lemonlib;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import party.lemons.lemonlib.event.InitEvent;

@Mod(modid = LemonLib.MODID, name = LemonLib.NAME, version = LemonLib.VERSION)
public class LemonLib
{
	public static final String MODID = "lemonlib";
	public static final String NAME = "LemonLib";
	public static final String VERSION = "1.3.0";

	public static final Logger LOGGER = LogManager.getLogger(MODID);

	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
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
