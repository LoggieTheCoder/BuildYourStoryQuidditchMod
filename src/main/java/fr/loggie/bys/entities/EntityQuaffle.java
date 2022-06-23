package fr.loggie.bys.entities;

import fr.loggie.bys.init.ModItems;
import fr.loggie.bys.items.abstract_items.SpawnBall;
import fr.loggie.bys.utils.Functions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityQuaffle extends EntityBall{
    public static final double DISTANCE_TO_HOLDING_PLAYER = 1;
    public static final double GRAVITY_FORCE_PER_TICK = 0.2;
    public static final double MULTIPLIER_DISTANCE = 6;
    public static final double FREIN = 1.03;
    public double gravity;
    public double prevX;
    public double prevY;
    public double prevZ;
    protected EntityPlayer holder = null;
    public EntityQuaffle(World worldIn){
        super(worldIn);
        prevX=this.posX;
        prevY=this.posY;
        prevZ=this.posZ;
        this.setNoGravity(false);
    }
    @Nullable
    @Override
    public ItemStack getDropItemStack() {
        ItemStack is =new ItemStack(ModItems.QUAFFLE_ITEM,1);
        is = SpawnBall.addNBT2Pos(is,new BlockPos(x1,y1,z1),new BlockPos(x2,y2,z2));
        return is;
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        this.setHolder(player);
        return true;
    }
    public void setHolder(EntityPlayer player){
        this.holder = player;
    }

    public EntityPlayer getHolder() {
        return holder;
    }

    @Override
    public void onEntityUpdate() {
        this.setRotation(0, 0);

        if (!world.isRemote) {
            prevX = posX;
            prevY = posY;
            prevZ = posZ;
            if (getHolder() != null) {
                setInHandOf(getHolder());
            } else {
                fallEngine();
                if (isAirBlockOnNextMove() && isInsideBlocksOnNextMove()) {
                    addPosition(move_x * speed, move_y * speed, move_z * speed);
                    this.setRotation(0, 0);
                } else {
                    this.move_x = 0;
                    this.move_y = 0;
                    this.move_z = 0;
                }
            }
            doAirAction();
            this.setRotation(0, 0);
            if (!Functions.isInsideBlocks(this.getPosition(), new BlockPos(x1, y1, z1), new BlockPos(x2, y2, z2))) {
                setPointingZone();
                holder = null;
            }
        }


    }
    public void doAirAction(){
        this.move_x/=FREIN;
        this.move_y/=FREIN;
        this.move_z/=FREIN;
    }

    public void setInHandOf(EntityPlayer player){
        double addx = player.getLookVec().x*DISTANCE_TO_HOLDING_PLAYER;
        double addy = player.getLookVec().y*DISTANCE_TO_HOLDING_PLAYER+1;
        double addz = player.getLookVec().z*DISTANCE_TO_HOLDING_PLAYER;
        this.setPosition(addx+player.posX,addy+player.posY,addz+player.posZ);
        this.setRotation(0,0);
    }
    public boolean isAirBlockOnNextMove(){
        return Functions.isAirBlock(world,new BlockPos(posX+move_x*speed,posY+move_y*speed,posZ+move_z*speed));
    }
    public boolean isInsideBlocksOnNextMove(){
        return Functions.isInsideBlocks(new BlockPos(posX+move_x*speed,posY+move_y*speed,posZ+move_z*speed),getFirstLimit(),getSecondLimit());
    }

    public void fallEngine(){
        if(!world.isRemote && move_y<4) {
            move_y -= GRAVITY_FORCE_PER_TICK;
        }
    }

    @Override
    public void applyEntityCollision(Entity entityIn) {
        if(!(entityIn instanceof EntityPlayer)){
            super.applyEntityCollision(entityIn);
        }

    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(source.damageType.equals("player")){
            setHolder(null);
            if(source.getTrueSource() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) source.getTrueSource();
                ItemStack main_stack = player.getHeldItem(EnumHand.MAIN_HAND);
                ItemStack off_stack = player.getHeldItem(EnumHand.OFF_HAND);
                if(main_stack.getItem()==ModItems.NET||off_stack.getItem()==ModItems.NET){
                    this.setDead();
                }
                this.move_x =source.getTrueSource().getLookVec().x*MULTIPLIER_DISTANCE;
                this.move_y =source.getTrueSource().getLookVec().y*MULTIPLIER_DISTANCE;
                this.move_z =source.getTrueSource().getLookVec().z*MULTIPLIER_DISTANCE;
                this.speed = 0.25;
            }
            return false;
        }
        if(source== DamageSource.IN_WALL) {
            if(getHolder() != null) {
                this.setPosition(getHolder().posX, getHolder().posY, getHolder().posZ);
                this.setHolder(null);
            }else {
                this.move_x=0;
                this.move_y=0;
                this.move_z=0;
                this.setPosition(prevX,prevY,prevZ);
            }
            return false;
        }
        this.setDead();
        return true;
    }
}
