package party.lemons.lemonlib.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionCustom extends Potion
{
	private final ResourceLocation ICON_LOCATION;
	private int potionIndex;

	protected PotionCustom(boolean isBadEffectIn, int liquidColorIn, int iconX, int iconY, ResourceLocation iconSheet)
	{
		super(isBadEffectIn, liquidColorIn);

		this.ICON_LOCATION = iconSheet;
		setIconIndex(iconX, iconY);
	}

	@Override
	public Potion setIconIndex(int x, int y)
	{
		potionIndex = x + y * 8;
		return this;
	}

	@SideOnly(Side.CLIENT)
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
	{
		drawIcon(x,y, 8, 8, effect, mc, 1);
	}

	@SideOnly(Side.CLIENT)
	public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha)
	{
		drawIcon(x,y, 4, 4, effect, mc, alpha);
	}

	@SideOnly(Side.CLIENT)
	public void drawIcon(int x, int y, int offsetX, int offsetY, PotionEffect effect, Minecraft mc, float alpha)
	{
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(ICON_LOCATION);

		GlStateManager.color(1,1, 1, alpha);
		int uX = potionIndex % 8 * 18;
		int uV = potionIndex / 8 * 18;

		GuiUtils.drawTexturedModalRect(x + offsetX, y + offsetY, uX, uV, 18, 18, 0);
		GlStateManager.popMatrix();
	}
}
