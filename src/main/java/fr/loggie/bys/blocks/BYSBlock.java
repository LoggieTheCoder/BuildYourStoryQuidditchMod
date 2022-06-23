package fr.loggie.bys.blocks;


import fr.loggie.bys.init.ModBlocks;
import fr.loggie.bys.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;

public class BYSBlock extends Block {

    public BYSBlock(String name, Material materialIn, CreativeTabs creativeTabs){
        super(materialIn);
        if(creativeTabs!=null){
            setCreativeTab(creativeTabs);
        }
        setRegistryName(name).setUnlocalizedName(name);
        ModBlocks.INSTANCE.getBlocks().add(this);

    }
    public BYSBlock(String name,Material materialIn, float hardness,float resistance,CreativeTabs creativeTabs){
        this(name, materialIn,creativeTabs);
        setHardness(hardness);
        setResistance(resistance);
    }
    public BYSBlock(String name,Material materialIn, float hardness,float resistance,int harvestlevel,String harvestType,CreativeTabs creativeTabs){
        this(name, materialIn, hardness, resistance,creativeTabs);
        setHarvestLevel(harvestType,harvestlevel);
    }
}
