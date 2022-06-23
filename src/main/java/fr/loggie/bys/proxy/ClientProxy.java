package fr.loggie.bys.proxy;

import fr.loggie.bys.BYS;
import fr.loggie.bys.init.*;
import fr.loggie.bys.utils.References;
import fr.loggie.bys.utils.handlers.TileEntitiesHandler;
import fr.loggie.bys.utils.handlers.GUIHandler;
import fr.loggie.bys.utils.handlers.RenderHandler;
import fr.loggie.bys.utils.packets.ModPackets;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy{
    @Override
    public void preInit() {
        super.preInit();

        ModPackets.registerPackets();
        OBJLoader.INSTANCE.addDomain(References.MODID);
        MinecraftForge.EVENT_BUS.register(ModItems.INSTANCE);
        MinecraftForge.EVENT_BUS.register(ModBlocks.INSTANCE);
        ModEntities.registerEntities();
        RenderHandler.registerEntityRenders();
        NetworkRegistry.INSTANCE.registerGuiHandler(BYS.instance,new GUIHandler());
        ModPotions.registerPotions();

    }

    @Override
    public void postInit() {
        super.postInit();

    }

    @Override
    public void init() {
        super.init();
    }

}
