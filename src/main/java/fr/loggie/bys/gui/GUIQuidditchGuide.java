package fr.loggie.bys.gui;



import fr.loggie.bys.utils.References;

import net.minecraft.client.gui.GuiScreen;

import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;



public class GUIQuidditchGuide extends GuiScreen
{

    private static final ResourceLocation QUIDDITCH_GUIDE = new ResourceLocation(References.MODID + ":textures/gui/quidditch_guide.png");



    public GUIQuidditchGuide()
    {
        this.width = 255;
        this.height = 255;
    }



    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(QUIDDITCH_GUIDE);
        super.drawScreen(mouseX,mouseY,partialTicks);
    }

    @Override
    public void drawDefaultBackground() {
        super.drawDefaultBackground();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(QUIDDITCH_GUIDE);
        int w = 256;
        int h = 256;
        int i = (this.width - h) / 2;
        int j = (this.height - w) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, w, h);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}