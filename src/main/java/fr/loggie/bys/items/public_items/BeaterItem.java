package fr.loggie.bys.items.public_items;

import com.google.common.collect.Sets;
import fr.loggie.bys.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

import net.minecraft.item.ItemTool;

import java.util.Set;

public class BeaterItem extends ItemTool {
    public static final Set<Block> EFFECTIVE_ON= Sets.newHashSet();
    public BeaterItem(String name, CreativeTabs tab) {
        super(6,-3.5F,ToolMaterial.DIAMOND,EFFECTIVE_ON);
        setRegistryName(name).setUnlocalizedName(name);
        setCreativeTab(tab);
        ModItems.INSTANCE.getItems().add(this);
    }
}
