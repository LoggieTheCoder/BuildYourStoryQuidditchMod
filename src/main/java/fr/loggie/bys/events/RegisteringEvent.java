package fr.loggie.bys.events;

import fr.loggie.bys.init.ModBlocks;
import fr.loggie.bys.init.ModItems;
import fr.loggie.bys.utils.handlers.TileEntitiesHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegisteringEvent {
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> e){
        ModItems.INSTANCE.init();
        e.getRegistry().registerAll(ModItems.INSTANCE.getItems().toArray(new Item[0]));
    }
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> e){
        ModBlocks.INSTANCE.init();
        e.getRegistry().registerAll(ModBlocks.INSTANCE.getBlocks().toArray(new Block[0]));
        TileEntitiesHandler.registerTileEntities();
    }
}
