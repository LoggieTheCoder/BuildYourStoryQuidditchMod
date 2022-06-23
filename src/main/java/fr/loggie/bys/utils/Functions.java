package fr.loggie.bys.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.time.Instant;
import java.util.UUID;

public class Functions {
    public static float getRotation(float d){
        return Math.abs(d-360*getEncadrement(d,360));
    }
    public static double getRotation(double d){
        return Math.abs(d-360*getEncadrement(d,360));
    }
    public static int getRotation(int d){
        return Math.abs(d-360*getEncadrement(d,360));
    }
    public static int getEncadrement(double x,double other){
        return (int)Math.floor(x/other);
    }
    public static boolean isInside(double value, double first, double second){
        if(first>second){
            return value <= first && value >= second;
        }else if(first<second){
            return value >= first && value <= second;

        }else if (first == second){
            return value == first;
        }
        return false;
    }
    public static long getUnixTime(){
        return Instant.now().getEpochSecond();
    }

    public static Double getDoubleNBT(ItemStack itemStack, String key) {
        if(itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey(key)){
            return itemStack.getTagCompound().getDouble(key);
        }
        return null;
    }
    public static Boolean getBooleanNBT(ItemStack itemStack, String key) {
        if(itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey(key)){
            return itemStack.getTagCompound().getBoolean(key);
        }
        return null;
    }
    public static AxisAlignedBB getAABBSixteenths(double x1,double y1,double z1,double x2,double y2,double z2){
        return new AxisAlignedBB(x1/16.0,y1/16.0,z1/16.0,x2/16.0,y2/16.0,z2/16.0);
    }

    public static BlockPos getBlockPosDifference(BlockPos pos,BlockPos toSubstract){

        return new BlockPos(pos.getX()-toSubstract.getX(),pos.getY()-toSubstract.getY(),pos.getZ()-toSubstract.getZ());
    }
    public static boolean isAirBlock(World world, BlockPos pos){
        return (world.getBlockState(pos).getBlock()== Blocks.AIR);
    }
    public static EntityPlayer getPlayer(String name,World world){
        return world.getPlayerEntityByName(name);
    }
    public static boolean isInsideBlocks(BlockPos pos, BlockPos first, BlockPos second){
        if(isInside(pos.getX(),first.getX(),second.getX())){
            if(isInside(pos.getY(),first.getY(),second.getY())){
                return isInside(pos.getZ(), first.getZ(), second.getZ());
            }
        }
        return false;
    }
    public static double nearlier(double value,double first,double second){
        if(Math.abs(value-first)>Math.abs(value-second)){
            return second;
        }
        return first;
    }
    public static double distanceBetween(BlockPos pos1, BlockPos pos2){
        double distX = Math.abs(pos1.getX()-pos2.getX());
        double distY = Math.abs(pos1.getY()-pos2.getY());
        double distZ = Math.abs(pos1.getZ()-pos2.getZ());
        return Math.sqrt(sqr(Math.sqrt(sqr(distX)+sqr(distY)))+sqr(distZ));
    }
    public static double sqr(double nbr){
        return nbr*nbr;
    }
    public static double puissance(final double x,final int exponent){
        double result = 1;
        for (int i = 0; i<exponent;i++){
            result *=x;
        }
        return result;
    }
    public static Vec3d vectorCalculator(float yaw,float pitch){
        return Vec3d.fromPitchYaw(pitch,yaw);
    }
    public static BlockPos distanceBetween(Entity entity,Entity otherEntity){
        return getBlockPosDifference(entity.getPosition(),otherEntity.getPosition());
    }
    public static boolean isClientPlayer(EntityPlayer player){
        return isClientPlayer(player.getUniqueID());
    }
    public static boolean isClientPlayer(UUID uuid){
        return uuid.toString().equalsIgnoreCase(Minecraft.getMinecraft().player.getUniqueID().toString());
    }
    public static double getTickSpeedFromKmH(double km_per_hour){
        return km_per_hour/72;
    }
    public static double getTickSpeedFromMH(double m_per_hour){
        return m_per_hour/72000;
    }
    public static double getTickSpeedFromKmM(double km_per_minute){
        return km_per_minute/1.2;
    }
    public static double getTickSpeedFromMM(double m_per_minute){
        return m_per_minute/1200;
    }
    public static double getTickSpeedFromKmS(double km_per_second){
        return km_per_second*50;
    }
    public static double getTickSpeedFromMS(double m_per_second){
        return m_per_second/20;
    }
    public static double getTickSpeedFromKmT(double km_per_tick){
        return km_per_tick*1000;
    }

}
