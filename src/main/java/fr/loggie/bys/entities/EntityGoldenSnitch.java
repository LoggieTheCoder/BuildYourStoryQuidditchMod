package fr.loggie.bys.entities;

import fr.loggie.bys.init.ModItems;
import fr.loggie.bys.items.abstract_items.SpawnBall;
import fr.loggie.bys.utils.Functions;
import fr.loggie.bys.utils.Messages;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityGoldenSnitch extends EntityBall{

    public double speed;
    public double prevGSX;
    public double prevGSY;
    public double prevGSZ;
    public final double MAX_SPEED= 15.0D;
    public final double MIN_SPEED= 3.0D;


    public EntityGoldenSnitch(World worldIn){
        super(worldIn);
        this.setRandom();
    }
    @Nullable
    @Override
    public ItemStack getDropItemStack() {
        ItemStack is = new ItemStack(ModItems.GOLDEN_SNITCH_ITEM,1);
        is = SpawnBall.addNBT2Pos(is,new BlockPos(x1,y1,z1),new BlockPos(x2,y2,z2));
        return is;
    }
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(source.getTrueSource() instanceof EntityPlayer) {
            if(!world.isRemote) {
                source.getTrueSource().sendMessage(new TextComponentString(source.getTrueSource().getName() + Messages.CAUGHT_GOLDEN_SNITCH.getTranslatedText()));
                //ItemStack is = new ItemStack(ModItems.golden_snitch_item,1);
                //is = SpawnBall.addNBT2Pos(is,new BlockPos(x1,y1,z1),new BlockPos(x2,y2,z2));
                //world.spawnEntity(new EntityItem(world,this.posX,this.posY,this.posZ,is));
                this.setDead();
            }

        }else if(source == DamageSource.OUT_OF_WORLD){
            if(!world.isRemote) {
                //ItemStack is = new ItemStack(ModItems.golden_snitch_item,1);
                //is = SpawnBall.addNBT2Pos(is,new BlockPos(x1,y1,z1),new BlockPos(x2,y2,z2));
                //world.spawnEntity(new EntityItem(world,this.posX,this.posY,this.posZ,is));
                this.setDead();
            }
        }
        return true;
    }

    public void setRandom(){
        move_x = Math.random()-0.5;
        move_y = Math.random()-0.5;
        move_z = Math.random()-0.5;
        speed = Math.random()*(MAX_SPEED-MIN_SPEED)+MIN_SPEED;
    }
    public void moveRandom(){

        this.setPosition(move_x*speed+this.posX,move_y*speed+this.posY,move_z*speed+this.posZ);
    }

    @Override
    public void onEntityUpdate() {
        this.setRotation(0,0);
        if(!world.isRemote) {
            prevGSX = posX;
            prevGSY = posY;
            prevGSZ = posZ;
            if ((int) (Math.random() * 100) == 1) {
                if((int)(Math.random()*2) ==1){

                    this.setRandom();
                }else {

                    this.standStill();

                }
            }
            this.moveRandom();
            if ((!Functions.isAirBlock(world,new BlockPos(posX,posY,posZ))) || !Functions.isInsideBlocks(new BlockPos(posX,posY,posZ),new BlockPos(x1,y1,z1),new BlockPos(x2,y2,z2))) {
                this.setRandom();
                setPosition(prevGSX,prevGSY,prevGSZ);

            }
            if (!Functions.isInsideBlocks(this.getPosition(), new BlockPos(x1, y1, z1), new BlockPos(x2, y2, z2))) {
                setPointingZone();
            }
        }
        this.setRotation(0,0);


    }
    public void standStill(){
        this.move_x=0;
        this.move_y=0;
        this.move_z=0;
        this.speed = 0;
    }


}
