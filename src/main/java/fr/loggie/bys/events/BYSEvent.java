package fr.loggie.bys.events;


import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.init.ModItems;
import fr.loggie.bys.init.ModKeybinds;
import fr.loggie.bys.init.ModPotions;
import fr.loggie.bys.items.abstract_items.SpawnBall;
import fr.loggie.bys.utils.Functions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;

import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;



public class BYSEvent {

    @SubscribeEvent
    public void getRidingEntity(TickEvent.PlayerTickEvent e){
        //System.out.println("ClientSide: "+e.player.world.isRemote+"     MotionX: "+e.player.motionX+"     MotionY: "+e.player.motionY+"     MotionZ: "+e.player.motionZ);
        if(e.player.isRiding() && e.player.getRidingEntity() !=null && e.player.getRidingEntity() instanceof EntityFlyingBroom){
            if(!e.player.world.isRemote) {
                EntityFlyingBroom fb = (EntityFlyingBroom) e.player.getRidingEntity();
                if(ModKeybinds.accelerateKey.isKeyDown()) {
                    fb.accelerate();
                    KeyBinding.setKeyBindState(ModKeybinds.accelerateKey.getKeyCode(),false);
                }
                if(ModKeybinds.deccelerateKey.isKeyDown()){
                    fb.decelerate();
                    KeyBinding.setKeyBindState(ModKeybinds.deccelerateKey.getKeyCode(),false);
                }
                if(ModKeybinds.salto_key.isKeyDown()){
                    if(fb.turnLefting==0) {
                        fb.setSalto();
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public void slowFalling(LivingEvent.LivingUpdateEvent e){
        if(!e.getEntityLiving().onGround && e.getEntityLiving().motionY<0.0D) {
            for (PotionEffect activePotionEffect : e.getEntityLiving().getActivePotionEffects()) {
                if (activePotionEffect.getPotion().toString().equalsIgnoreCase(ModPotions.SLOW_FALLING_EFFECT.toString())) {
                    e.getEntityLiving().motionY *= 0.7D;
                    e.getEntityLiving().fallDistance = 0;
                    return;
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void addRotation(RenderPlayerEvent.Pre event)
    {
        if(event.isCanceled()){
            return;
        }
        if(event.getEntityPlayer().isRiding() && event.getEntityPlayer().getRidingEntity() instanceof EntityFlyingBroom) {

            boolean isClientPlayer = event.getEntityPlayer().getUniqueID().toString().equalsIgnoreCase(Minecraft.getMinecraft().player.getUniqueID().toString());
            if(isClientPlayer) {
                GlStateManager.pushMatrix();

                EntityPlayer player = event.getEntityPlayer();
                EntityFlyingBroom fb = (EntityFlyingBroom) event.getEntityPlayer().getRidingEntity();
                //BlockPos difference = Functions.distanceBetween(player, Minecraft.getMinecraft().player);


                float a = player.rotationYaw;
                float b = player.rotationPitch;
                GlStateManager.rotate(a * (-1), 0, 1, 0);
                GlStateManager.rotate(b * (-1), 1, 0, 0);
                //if(!isClientPlayer) {GlStateManager.translate(difference.getX(),difference.getY(),difference.getZ());}
                assert fb != null;
                GlStateManager.rotate(fb.rotationPitch, 1, 0, 0);
                GlStateManager.rotate(b, 1, 0, 0);
                GlStateManager.rotate(a, 0, 1, 0);


                //if(!isClientPlayer) {GlStateManager.translate(difference.getX()*(-1),difference.getY()*(-1),difference.getZ()*(-1));}
            }
        }

    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void cancelRotation(RenderPlayerEvent.Post event)
    {
        if(event.getEntityPlayer().isRiding() && event.getEntityPlayer().getRidingEntity() instanceof EntityFlyingBroom) {
            if(Functions.isClientPlayer(event.getEntityPlayer())) {
                GlStateManager.popMatrix();
            }
        }
    }
    @SubscribeEvent
    public void leftClickBlockWithBall(PlayerInteractEvent.LeftClickBlock e) {
        if (!e.getEntityPlayer().isCreative()) {
            return;
        }
        boolean isMainHand = e.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND).getItem() == ModItems.QUAFFLE_ITEM || e.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND).getItem() == ModItems.GOLDEN_SNITCH_ITEM || e.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND).getItem() == ModItems.BLUDGER_ITEM;
        boolean isOffHand = e.getEntityPlayer().getHeldItem(EnumHand.OFF_HAND).getItem() == ModItems.QUAFFLE_ITEM || e.getEntityPlayer().getHeldItem(EnumHand.OFF_HAND).getItem() == ModItems.GOLDEN_SNITCH_ITEM || e.getEntityPlayer().getHeldItem(EnumHand.OFF_HAND).getItem() == ModItems.BLUDGER_ITEM;
        ItemStack is;
        if (isMainHand) {
            is = e.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND);
        } else if (isOffHand) {
            is = e.getEntityPlayer().getHeldItem(EnumHand.OFF_HAND);
        } else {
            return;
        }
        SpawnBall.addNBTPos(is, e.getPos(), e.getEntityPlayer());
        if (isMainHand) {
            e.getEntityPlayer().setHeldItem(EnumHand.MAIN_HAND, is);
        } else {
            e.getEntityPlayer().setHeldItem(EnumHand.OFF_HAND, is);
        }
    }
}
