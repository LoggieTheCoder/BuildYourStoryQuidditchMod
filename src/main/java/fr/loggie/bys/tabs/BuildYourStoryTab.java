package fr.loggie.bys.tabs;

import fr.loggie.bys.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;


public class BuildYourStoryTab extends CreativeTabs {


    public BuildYourStoryTab(String label,String backgroundImageName) {
        super(label);
        this.setBackgroundImageName(backgroundImageName);

    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.GOLDEN_SNITCH_ITEM);
    }
}
