package fr.loggie.bys.items.abstract_items;

import fr.loggie.bys.entities.EntityBall;
import fr.loggie.bys.entities.EntityQuaffle;
import fr.loggie.bys.init.ModItems;
import fr.loggie.bys.items.abstract_items.SpawnEntityItem;
import fr.loggie.bys.utils.Messages;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public abstract class SpawnBall extends SpawnEntityItem {
    public static final String x1 = "x1";
    public static final String y1 = "y1";
    public static final String z1 = "z1";
    public static final String x2 = "x2";
    public static final String y2 = "y2";
    public static final String z2 = "z2";


    public SpawnBall(String name, CreativeTabs creativeTab) {
        super(name, creativeTab);
        this.setMaxStackSize(1);
    }


    @Override
    public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player) {
        return false;
    }

    @Override
    public boolean canItemEditBlocks() {
        return false;
    }
    public EntityBall getBrutEntity(World world){
        return null;
    }
    @Override
    public Entity getEntity(World world,ItemStack stack) {

        if (getNBT(stack, x1) != null && getNBT(stack, y1) != null && getNBT(stack, z1) != null && getNBT(stack, x2) != null && getNBT(stack, y2) != null && getNBT(stack, z2) != null){
            EntityBall entityBall =  getBrutEntity(world);
            entityBall.x1 = getNBT(stack,x1);
            entityBall.y1 = getNBT(stack,y1);
            entityBall.z1 = getNBT(stack,z1);
            entityBall.x2 = getNBT(stack,x2);
            entityBall.y2 = getNBT(stack,y2);
            entityBall.z2 = getNBT(stack,z2);
            return entityBall;
        }

        return null;
    }

    public Double getNBT(ItemStack itemStack,String key) {
        if(itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey(key)){
            return itemStack.getTagCompound().getDouble(key);
        }
        return null;
    }

    public static ItemStack addNBTPos(ItemStack itemstack,BlockPos pos,EntityPlayer player){
        NBTTagCompound nbt;
        if (itemstack.hasTagCompound())
        {
            nbt = itemstack.getTagCompound();
        }
        else
        {
            nbt = new NBTTagCompound();
        }
        assert nbt != null;
        if(!player.isSneaking()) {

            nbt.setDouble(x1, pos.getX());
            nbt.setDouble(y1, pos.getY());
            nbt.setDouble(z1, pos.getZ());
            if(player.world.isRemote) {
                player.sendMessage(new TextComponentString("\u00A72"+ Messages.FIRST_POS_SET.getTranslatedText()));
            }
        }else {
            nbt.setDouble(x2, pos.getX());
            nbt.setDouble(y2, pos.getY());
            nbt.setDouble(z2, pos.getZ());
            if (player.world.isRemote) {
                player.sendMessage(new TextComponentString("\u00A72"+Messages.SECOND_POS_SET.getTranslatedText()));
            }
        }
        itemstack.setTagCompound(nbt);

        return itemstack;
    }
    public static ItemStack addNBT2Pos(ItemStack itemstack,BlockPos pos,BlockPos otherPos){
        NBTTagCompound nbt;
        if (itemstack.hasTagCompound())
        {
            nbt = itemstack.getTagCompound();
        }
        else
        {
            nbt = new NBTTagCompound();
        }
        assert nbt != null;


        nbt.setDouble(x1, pos.getX());
        nbt.setDouble(y1, pos.getY());
        nbt.setDouble(z1, pos.getZ());


        nbt.setDouble(x2, otherPos.getX());
        nbt.setDouble(y2, otherPos.getY());
        nbt.setDouble(z2, otherPos.getZ());


        itemstack.setTagCompound(nbt);

        return itemstack;
    }




}
