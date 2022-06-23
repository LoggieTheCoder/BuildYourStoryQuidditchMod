package fr.loggie.bys.items.public_items;

import fr.loggie.bys.BYS;
import fr.loggie.bys.items.abstract_items.BYSItem;
import fr.loggie.bys.utils.id.GuiID;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemQuidditchGuide extends BYSItem {

    public ItemQuidditchGuide(String name, CreativeTabs creativeTabs) {
        super(name, creativeTabs);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(worldIn.isRemote) {
            playerIn.openGui(BYS.instance, GuiID.GUI_QUIDDITCH_GUIDE, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
