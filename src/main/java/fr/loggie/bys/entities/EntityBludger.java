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

public class EntityBludger extends EntityBall {

    double prevBX;
    double prevBY;
    double prevBZ;
    public static final double ATTACK_SPEED = 2.0;
    public static final double MAX_RANGE = 100.0;
    public static final double MAX_SPEED = 4.0;
    public static final double MIN_SPEED = 1;
    public static final int COOLDOWN_ATTACK=2*200;
    EntityPlayer target = null;
    int cooldown = 0;

    public EntityBludger(World worldIn) {
        super(worldIn);
        this.prevBX = this.posX;
        this.prevBY = this.posY;
        this.prevBZ = this.posZ;
    }
    public void setRandom(){
        move_x = Math.random()-0.5;
        move_y = Math.random()-0.5;
        move_z = Math.random()-0.5;
        speed = Math.random()*(MAX_SPEED-MIN_SPEED)+MIN_SPEED;
    }
    public void moveRandom(){
        if(isAirBlockOnNextMove() && isNextMoveInZone()) {
            this.setPosition(this.move_x * speed + this.posX, move_y * speed + this.posY, move_z * speed + this.posZ);
        }else {
            setRandom();
        }
    }
    public boolean isAirBlockOnNextMove(){
        return Functions.isAirBlock(world,new BlockPos(posX+move_x*speed,posY+move_y*speed,posZ+move_z*speed));
    }
    public boolean isNextMoveInZone(){
        return Functions.isInsideBlocks(new BlockPos(posX+move_x*speed,posY+move_y*speed,posZ+move_z*speed),new BlockPos(x1,y1,z1),new BlockPos(x2,y2,z2));
    }

    @Nullable
    @Override
    public ItemStack getDropItemStack() {
        ItemStack is = new ItemStack(ModItems.BLUDGER_ITEM,1);
        is = SpawnBall.addNBT2Pos(is,new BlockPos(x1,y1,z1),new BlockPos(x2,y2,z2));
        return is;
    }
    public boolean isNameDobby(){
        return this.getName().startsWith("Dobby ");
    }
    public String getPlayerName(){
        if(isNameDobby()){
            StringBuilder playerName = new StringBuilder();
            int i = 0;
            for(char ch:this.getName().toCharArray()){
                if(i>5){
                    playerName.append(ch);
                }
                i++;
            }
            return playerName.toString();
        }
        return "";
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(source.getDamageType().equals("player") && source.getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) source.getTrueSource();
            ItemStack main_stack = player.getHeldItem(EnumHand.MAIN_HAND);
            ItemStack off_stack = player.getHeldItem(EnumHand.OFF_HAND);

            if(main_stack.getItem() == ModItems.BEATER){
                this.cooldown = 50;
                this.move_x = player.getLookVec().x*amount*0.3;
                this.move_y = player.getLookVec().y*amount*0.3;
                this.move_z = player.getLookVec().z*amount*0.3;

            }else if (off_stack.getItem() == ModItems.BEATER){
                this.cooldown = 50;
                this.move_x = player.getLookVec().x*amount*0.15;
                this.move_y = player.getLookVec().y*amount*0.15;
                this.move_z = player.getLookVec().z*amount*0.15;
            }else if(main_stack.getItem()==ModItems.NET || off_stack.getItem()==ModItems.NET){
                this.setDead();
            }
            return true;
        }
        if(source==DamageSource.OUT_OF_WORLD) {
            this.setDead();
        }
        return true;

    }


    public EntityPlayer getNearestPlayer(final double max_range){
        double distance = max_range+1;
        EntityPlayer currentPlayer=null;
        BlockPos pos = this.getPosition();
        for(EntityPlayer player : this.world.playerEntities){
            double distancePlayer =Functions.distanceBetween(pos,player.getPosition());
            if(distancePlayer<=max_range){
                if(distancePlayer<=distance){
                    distance = distancePlayer;
                    currentPlayer = player;
                }
            }
        }
        return currentPlayer;
    }


    @Override
    public void onEntityUpdate() {
        if(!world.isRemote) {
            prevBX = posX;
            prevBY = posY;
            prevBZ = posZ;
            if(cooldown ==0) {
                if ((int) (Math.random() * 70) == 35) {
                    this.setRandom();
                }


                if(isNameDobby() && Functions.getPlayer(getPlayerName(),world)!=null){
                    EntityPlayer player = Functions.getPlayer(getPlayerName(),world);
                    double distance = Functions.distanceBetween(this.getPosition(), player.getPosition());
                    double xd = player.posX - this.posX;
                    double yd = player.posY - this.posY;
                    double zd = player.posZ - this.posZ;
                    move_x = xd / distance;
                    move_y = yd / distance;
                    move_z = zd / distance;
                    speed = 4;
                    cooldown = 100;
                   // System.out.println("Targetting "+player.getName());

                }else if (getNearestPlayer(MAX_RANGE) != null) {
                    EntityPlayer player = getNearestPlayer(MAX_RANGE);
                    double distance = Functions.distanceBetween(this.getPosition(), player.getPosition());
                    double xd = player.posX - this.posX;
                    double yd = player.posY - this.posY;
                    double zd = player.posZ - this.posZ;
                    move_x = xd / distance;
                    move_y = yd / distance;
                    move_z = zd / distance;
                    speed = ATTACK_SPEED;
                    cooldown = COOLDOWN_ATTACK;
                }


            }else {
                cooldown -=1;
            }
            this.moveRandom();
            if (!Functions.isInsideBlocks(this.getPosition(), new BlockPos(x1, y1, z1), new BlockPos(x2, y2, z2))) {
                setPointingZone();
            }

        }


    }

    @Override
    protected void collideWithEntity(Entity entityIn) {
        if(entityIn instanceof EntityPlayer){
            if(!world.isRemote){
                entityIn.attackEntityFrom(DamageSource.causeMobDamage(this),6);
            }
        }
    }

}
