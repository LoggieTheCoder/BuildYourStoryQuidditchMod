package fr.loggie.bys.tabs;

import fr.loggie.bys.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;


public class QuidditchTab extends CreativeTabs {


    public QuidditchTab(String label, String backgroundImageName) {
        super(label);
        this.setBackgroundImageName(backgroundImageName);

    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.BROSSDUR_1_ITEM);
    }
}
