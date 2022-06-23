package fr.loggie.bys.gui;


import fr.loggie.bys.BYS;
import fr.loggie.bys.containers.ContainerBriefcase;
import fr.loggie.bys.tilentities.TileEntityBriefcase;
import fr.loggie.bys.utils.References;

import fr.loggie.bys.utils.packets.SetBriefcaseFieldsValuePacket;
import fr.loggie.bys.utils.packets.SpawnBriefcaseContentPacket;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;

import java.io.IOException;

public class GUIBriefcase extends GuiContainer
{
    private static final ResourceLocation BRIEFCASE = new ResourceLocation(References.MODID + ":textures/gui/briefcase.png");
    private final InventoryPlayer playerInventory;
    private final TileEntityBriefcase te;
    private GuiTextField pos1X;
    private GuiTextField pos1Y;
    private GuiTextField pos1Z;
    private GuiTextField pos2X;
    private GuiTextField pos2Y;
    private GuiTextField pos2Z;



    public GUIBriefcase(InventoryPlayer playerInventory, TileEntityBriefcase chestInventory, EntityPlayer player)
    {

        super(new ContainerBriefcase(playerInventory, chestInventory, player));
        this.playerInventory = playerInventory;
        this.te = chestInventory;

        this.xSize = 179;
        this.ySize = 280;//256 AVANT
    }


    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(0,this.guiLeft+this.xSize/2-50,this.guiTop+145,100,20,"Spawn Balls"));
        this.pos1X = new GuiTextField(1, this.fontRenderer, this.width/2-this.xSize/2-75, this.guiTop+40, 50, 20);
        this.pos1Y = new GuiTextField(2, this.fontRenderer, this.width/2-this.xSize/2-75, this.guiTop+70, 50, 20);
        this.pos1Z = new GuiTextField(3, this.fontRenderer, this.width/2-this.xSize/2-75, this.guiTop+100, 50, 20);
        this.pos2X = new GuiTextField(4, this.fontRenderer, this.width/2+this.xSize/2+25, this.guiTop+40, 50, 20);
        this.pos2Y = new GuiTextField(5, this.fontRenderer, this.width/2+this.xSize/2+25, this.guiTop+70, 50, 20);
        this.pos2Z = new GuiTextField(6, this.fontRenderer, this.width/2+this.xSize/2+25, this.guiTop+100, 50, 20);

        this.pos1X.setMaxStringLength(12);
        this.pos1Y.setMaxStringLength(12);
        this.pos1Z.setMaxStringLength(12);
        this.pos2X.setMaxStringLength(12);
        this.pos2Y.setMaxStringLength(12);
        this.pos2Z.setMaxStringLength(12);

        this.pos1X.setFocused(false);
        this.pos1Y.setFocused(false);
        this.pos1Z.setFocused(false);
        this.pos2X.setFocused(false);
        this.pos2Y.setFocused(false);
        this.pos2Z.setFocused(false);

        this.pos1X.setText(te.pos1X);
        this.pos1Y.setText(te.pos1Y);
        this.pos1Z.setText(te.pos1Z);
        this.pos2X.setText(te.pos2X);
        this.pos2Y.setText(te.pos2Y);
        this.pos2Z.setText(te.pos2Z);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        this.pos1X.textboxKeyTyped(typedChar, keyCode);
        this.pos1Y.textboxKeyTyped(typedChar, keyCode);
        this.pos1Z.textboxKeyTyped(typedChar, keyCode);
        this.pos2X.textboxKeyTyped(typedChar, keyCode);
        this.pos2Y.textboxKeyTyped(typedChar, keyCode);
        this.pos2Z.textboxKeyTyped(typedChar, keyCode);
        if(this.mc.gameSettings.keyBindInventory.isActiveAndMatches(keyCode) && (pos1X.isFocused() || pos2X.isFocused())){
            return;
        }
        super.keyTyped(typedChar,keyCode);

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.pos1X.mouseClicked(mouseX, mouseY, mouseButton);
        this.pos1Y.mouseClicked(mouseX, mouseY, mouseButton);
        this.pos1Z.mouseClicked(mouseX, mouseY, mouseButton);
        this.pos2X.mouseClicked(mouseX, mouseY, mouseButton);
        this.pos2Y.mouseClicked(mouseX, mouseY, mouseButton);
        this.pos2Z.mouseClicked(mouseX, mouseY, mouseButton);

    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if(button.id==0){
            this.onGuiClosed();
            this.mc.displayGuiScreen(null);
            BYS.PACKETS_CHANNEL.sendToServer(new SpawnBriefcaseContentPacket(this.te.getPos().getX(),this.te.getPos().getY(),this.te.getPos().getZ()));
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 20; i++) {
                    this.te.getWorld().spawnParticle(EnumParticleTypes.FLAME,te.getPos().getX()+Math.random(),1+te.getPos().getY()+i*0.15,te.getPos().getZ()+Math.random(),0,0,0);
                }
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.pos1X.drawTextBox();
        this.pos1Y.drawTextBox();
        this.pos1Z.drawTextBox();
        this.pos2X.drawTextBox();
        this.pos2Y.drawTextBox();
        this.pos2Z.drawTextBox();
        this.drawString(this.fontRenderer,"X", this.width/2-this.xSize/2-85, this.guiTop+45, 16777215);
        this.drawString(this.fontRenderer,"Y", this.width/2-this.xSize/2-85,this.guiTop+75 , 16777215);
        this.drawString(this.fontRenderer,"Z", this.width/2-this.xSize/2-85,this.guiTop+105 , 16777215);
        this.drawString(this.fontRenderer,"X", this.width/2+this.xSize/2+80, this.guiTop+45, 16777215);
        this.drawString(this.fontRenderer,"Y", this.width/2+this.xSize/2+80, this.guiTop+75, 16777215);
        this.drawString(this.fontRenderer,"Z", this.width/2+this.xSize/2+80, this.guiTop+105, 16777215);
    }

    @Override
    public void onGuiClosed() {
        te.pos1X = this.pos1X.getText();
        te.pos1Y = this.pos1Y.getText();
        te.pos1Z = this.pos1Z.getText();
        te.pos2X = this.pos2X.getText();
        te.pos2Y = this.pos2Y.getText();
        te.pos2Z = this.pos2Z.getText();
        try {
            BYS.PACKETS_CHANNEL.sendToServer(new SetBriefcaseFieldsValuePacket(this.te.getPos(),Integer.parseInt(pos1X.getText()),Integer.parseInt(pos1Y.getText()),Integer.parseInt(pos1Z.getText()),Integer.parseInt(pos2X.getText()),Integer.parseInt(pos2Y.getText()),Integer.parseInt(pos2Z.getText())));
        }catch (Exception e){
            mc.player.sendMessage(new TextComponentString("Vous devez entrer les coordonees en nombres."));
        }
        te.markDirty();
        super.onGuiClosed();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        //this.fontRenderer.drawString(Objects.requireNonNull(this.te.getDisplayName()).getUnformattedText(), 8, 6, 16086784);
        //this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize-92, 16086784);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(BRIEFCASE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}