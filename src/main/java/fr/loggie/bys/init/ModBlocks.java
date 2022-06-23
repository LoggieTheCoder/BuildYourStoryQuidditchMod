package fr.loggie.bys.init;

import com.google.common.collect.Lists;
import fr.loggie.bys.BYS;
import fr.loggie.bys.blocks.BriefcaseBlock;
import fr.loggie.bys.utils.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

public class ModBlocks {
    public static final ModBlocks INSTANCE = new ModBlocks();
    public static Block BRIEFCASE;


    private List<Block> blocks;
    public void init(){
        blocks = Lists.newArrayList();
        BRIEFCASE= new BriefcaseBlock("briefcase", Material.WOOD,1,5,BYS.QUIDDITCH_TAB);
    }
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent e){
        for (Block block : blocks) {
            registerModel(block);
        }

    }
    private void registerModel(Block block){
        ItemBlock ib = new ItemBlock(block);
        if(block.getRegistryName() != null) {
            ib.setRegistryName(block.getRegistryName());
        }


        GameRegistry.findRegistry(Item.class).register(ib);

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),0,new ModelResourceLocation(new ResourceLocation(References.MODID,block.getUnlocalizedName().substring(5)),"inventory"));
    }

    public List<Block> getBlocks() {
        return blocks;
    }
}
