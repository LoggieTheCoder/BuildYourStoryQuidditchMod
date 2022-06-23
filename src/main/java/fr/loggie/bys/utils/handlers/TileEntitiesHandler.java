package fr.loggie.bys.utils.handlers;

import fr.loggie.bys.tilentities.TileEntityBriefcase;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntitiesHandler {
    public static void registerTileEntities(){
        GameRegistry.registerTileEntity(TileEntityBriefcase.class,"briefcase");
    }
}
