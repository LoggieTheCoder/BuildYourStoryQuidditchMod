package fr.loggie.bys.utils.handlers;

import fr.loggie.bys.containers.ContainerBriefcase;
import fr.loggie.bys.gui.GUIBriefcase;
import fr.loggie.bys.gui.GUIQuidditchGuide;
import fr.loggie.bys.tilentities.TileEntityBriefcase;
import fr.loggie.bys.utils.id.GuiID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GUIHandler implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID== GuiID.GUI_BRIEFCASE)return new ContainerBriefcase(player.inventory,(TileEntityBriefcase) world.getTileEntity(new BlockPos(x,y,z)),player);
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if(ID== GuiID.GUI_BRIEFCASE)return new GUIBriefcase(player.inventory,(TileEntityBriefcase) world.getTileEntity(new BlockPos(x,y,z)),player);
        if(ID==GuiID.GUI_QUIDDITCH_GUIDE)return new GUIQuidditchGuide();
        return null;
    }
}
