package fr.loggie.bys.entities;

import fr.loggie.bys.init.ModItems;
import fr.loggie.bys.utils.Functions;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EntityBall extends EntityFlying {

    public double x1;
    public double y1;
    public double z1;
    public double x2;
    public double y2;
    public double z2;
    public double move_x=0;
    public double move_y=0;
    public double move_z=0;
    public double speed=0.25;



    public EntityBall(World worldIn) {

        super(worldIn);
        this.setNoGravity(true);
    }


    public void setClientLimits(BlockPos b1,BlockPos b2){
        this.x1 = b1.getX();this.y1 = b1.getY();this.z1 = b1.getZ();
        this.x2 = b2.getX();this.y2 = b2.getY();this.z2 = b2.getZ();

    }

    public void addPosition(double x, double y, double z){
        this.setPosition(this.posX+x,this.posY+y,this.posZ+z);
    }
    public void goToCenter(){
        this.setPosition(getCenter().getX(),getCenter().getY(),getCenter().getZ());
    }
    public BlockPos getFirstLimit(){
        return new BlockPos(x1,y1,z1);
    }
    public BlockPos getSecondLimit(){
        return new BlockPos(x2,y2,z2);
    }
    public BlockPos getCenter(){
        return new BlockPos((x1+x2)/2,(y1+y2)/2,(z1+z2)/2);
    }

    public ItemStack getDropItemStack() {
        return null;
    }
    public void dropItemStack(ItemStack itemStack) {
        if(itemStack !=null && !world.isRemote) {
            EntityItem itemToSpawn = new EntityItem(world, this.posX, this.posY, this.posZ, itemStack);
            world.spawnEntity(itemToSpawn);
        }
    }
    public void dropLoot(){
        dropItemStack(getDropItemStack());
    }

    @Override
    public void setDead() {
        this.dropLoot();
        super.setDead();
    }
    public void setPointingZone(){

        if(x1>=x2){
            if(posX>x1){
                move_x=x1-posX;
                speed = 1;
            }else if(posX<x2){
                move_x=x2-posX;
                speed = 1;
            }
        }else { //x1<x2
            if(posX<x1){
                move_x=x1-posX;
                speed = 1;
            }else if(posX>x2){
                move_x=x2-posX;
                speed = 1;
            }
        }

        if(y1>=y2){
            if(posY>y1){
                move_y=y1-posY;
                speed = 1;
            }else if(posY<y2){
                move_y=y2-posY;
                speed = 1;
            }
        }else { //y1<y2
            if(posY<y1){
                move_y=y1-posY;
                speed = 1;
            }else if(posY>y2){
                move_y=y2-posY;
                speed = 1;
            }
        }
        if(z1>=z2){
            if(posZ>z1){
                move_z=z1-posZ;
                speed = 1;
            }else if(posZ<z2){
                move_z=z2-posZ;
                speed = 1;
            }
        }else { //z1<z2
            if(posZ<z1){
                move_z=z1-posZ;
                speed = 1;
            }else if(posZ>z2){
                move_z=z2-posZ;
                speed = 1;
            }
        }
        this.setPosition(this.move_x*speed+posX,this.move_y*speed+posY,this.move_z*speed+posZ);

    }
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        compound.setIntArray("positions",new int[]{(int)this.x1,(int)this.y1,(int)this.z1,(int)this.x2,(int)this.y2,(int)this.z2});
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        int[] ints = compound.getIntArray("positions");
        if(ints.length ==6) {
            this.setClientLimits(new BlockPos(ints[0], ints[1], ints[2]), new BlockPos(ints[3], ints[4], ints[5]));
        }
    }

}
