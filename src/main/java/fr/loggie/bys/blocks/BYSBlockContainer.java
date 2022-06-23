package fr.loggie.bys.blocks;

import fr.loggie.bys.BYS;
import fr.loggie.bys.init.ModBlocks;
import fr.loggie.bys.init.ModItems;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class BYSBlockContainer extends BlockContainer {
    public BYSBlockContainer(String name, Material materialIn,CreativeTabs creativeTabs){
        super(materialIn);
        this.setCreativeTab(creativeTabs);
        setRegistryName(name).setUnlocalizedName(name);
        ModBlocks.INSTANCE.getBlocks().add(this);
    }
    public BYSBlockContainer(String name, Material materialIn, float hardness, float resistance, CreativeTabs creativeTabs){

        this(name, materialIn,creativeTabs);
        setHardness(hardness);
        setResistance(resistance);
    }
    public BYSBlockContainer(String name,Material materialIn, float hardness,float resistance,CreativeTabs creativeTabs,int harvestlevel,String harvestType){
        this(name, materialIn, hardness, resistance,creativeTabs);
        setHarvestLevel(harvestType,harvestlevel);
    }

}
