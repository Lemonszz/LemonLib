package party.lemons.lemonlib.potion;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;

public class EffectCustom extends Effect
{
	private final ResourceLocation ICON_LOCATION;
	private int potionIndex;

	protected EffectCustom(EffectType type, int liquidColorIn, int iconX, int iconY, ResourceLocation iconSheet)
	{
		super(type, liquidColorIn);

		this.ICON_LOCATION = iconSheet;
		setIconIndex(iconX, iconY);
	}

	public Effect setIconIndex(int x, int y)
	{
		potionIndex = x + y * 8;
		return this;
	}

	@OnlyIn(Dist.CLIENT)
	public void renderInventoryEffect(EffectInstance effect, DisplayEffectsScreen<?> gui, int x, int y, float z)
	{
		drawIcon(x,y, 8, 8, effect, Minecraft.getInstance(), 1);
	}

	@OnlyIn(Dist.CLIENT)
	public void renderHUDEffect(EffectInstance effect, AbstractGui gui, int x, int y, float z, float alpha)
	{
		drawIcon(x,y, 4, 4, effect, Minecraft.getInstance(), alpha);
	}

	@OnlyIn(Dist.CLIENT)
	public void drawIcon(int x, int y, int offsetX, int offsetY, EffectInstance effect, Minecraft mc, float alpha)
	{
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(ICON_LOCATION);

		GlStateManager.color4f(1,1, 1, alpha);
		int uX = potionIndex % 8 * 18;
		int uV = potionIndex / 8 * 18;

		GuiUtils.drawTexturedModalRect(x + offsetX, y + offsetY, uX, uV, 18, 18, 0);
		GlStateManager.popMatrix();
	}
}
