package fr.loggie.bys.items.spawn_items;

import fr.loggie.bys.entities.EntityFlyingBroom;

import fr.loggie.bys.init.ModItems;
import fr.loggie.bys.init.ModKeybinds;
import fr.loggie.bys.items.abstract_items.BYSItem;

import fr.loggie.bys.utils.Messages;
import fr.loggie.bys.utils.Sorts;
import net.minecraft.creativetab.CreativeTabs;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class MagicQuiddichWand extends BYSItem {
    public MagicQuiddichWand(String name, CreativeTabs tab) {
        super(name,tab);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!ModKeybinds.SORTS_KEY.isKeyDown()) {
            if (playerIn.isRiding() && playerIn.getRidingEntity() instanceof EntityFlyingBroom) {
                getActiveSort(playerIn, handIn).doAction(playerIn, playerIn.getRidingEntity());
                playerIn.sendMessage(new TextComponentString("\u00A7b"+Messages.SORT_APPLIED.getTranslatedText()+" \u00A7b"+getActiveSort(playerIn,handIn)));
            }
        }else {
            ItemStack stack= handIn==EnumHand.MAIN_HAND?playerIn.getHeldItemMainhand():playerIn.getHeldItemOffhand();
            playerIn.setHeldItem(handIn,setNBTSort(stack, getActiveSort(playerIn, handIn).next(), playerIn));
        }
        return new ActionResult<>(EnumActionResult.SUCCESS,playerIn.inventory.player.getHeldItemMainhand());
    }
    public Sorts getActiveSort(EntityPlayer playerIn,EnumHand handIn){
        switch (handIn) {
            case MAIN_HAND:
                if(playerIn.getHeldItemMainhand().getItem().toString().equals(ModItems.MAGIC_QUIDDITCH_WAND.toString())) {
                    NBTTagCompound nbt = playerIn.getHeldItemMainhand().getTagCompound();
                    if (nbt != null) {
                        int sortID = nbt.getInteger("sort");
                        return Sorts.getElementFromID(sortID);
                    }
                }
                break;
            case OFF_HAND:
                if(playerIn.getHeldItemOffhand().getItem().toString().equals(ModItems.MAGIC_QUIDDITCH_WAND.toString())) {
                    NBTTagCompound nbt2 = playerIn.getHeldItemOffhand().getTagCompound();
                    if (nbt2 != null) {
                        int sortID = nbt2.getInteger("sort");
                        return Sorts.getElementFromID(sortID);
                    }
                }
                break;
        }
        return Sorts.NO_SORT;
    }
    public static ItemStack setNBTSort(ItemStack itemstack,Sorts sort,EntityPlayer player){
        NBTTagCompound nbt;
        if (itemstack.hasTagCompound()) {nbt = itemstack.getTagCompound();}else{nbt = new NBTTagCompound();}
        assert nbt != null;
        nbt.setInteger("sort",sort.getID());
        if(player.world.isRemote) {
            player.sendMessage(new TextComponentString("\u00A72"+ Messages.SORT_SET.getTranslatedText()+" \u00A7b"+sort));
        }
        itemstack.setTagCompound(nbt);
        return itemstack;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if(target instanceof EntityFlyingBroom){
            if(!playerIn.world.isRemote){
                Sorts sort = getActiveSort(playerIn,hand);
                sort.doAction(playerIn,target);
                playerIn.sendMessage(new TextComponentString("\u00A7b"+Messages.SORT_APPLIED.getTranslatedText()+" \u00A7b"+sort));
            }
            return true;
        }
        return false;
    }
}
