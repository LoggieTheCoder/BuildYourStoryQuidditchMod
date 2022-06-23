package fr.loggie.bys.items.abstract_items;

import fr.loggie.bys.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BYSItem extends Item {
    public BYSItem(String name,CreativeTabs creativeTabs){
        setRegistryName(name).setUnlocalizedName(name);
        setCreativeTab(creativeTabs);
        ModItems.INSTANCE.getItems().add(this);

    }
    public BYSItem(String name, CreativeTabs creativeTabs,int maxStackSize){
        this(name,creativeTabs);
        this.setMaxStackSize(maxStackSize);
    }
}
