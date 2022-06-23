package fr.loggie.bys.items.abstract_items;

import fr.loggie.bys.entities.EntityBall;
import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.EntityGoldenSnitch;
import fr.loggie.bys.entities.EntitySortilege;
import fr.loggie.bys.items.spawn_items.MagicQuiddichWand;
import fr.loggie.bys.utils.Functions;
import fr.loggie.bys.utils.Messages;
import fr.loggie.bys.utils.Sorts;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public abstract class SpawnEntityItem extends BYSItem {



    public SpawnEntityItem(String name, CreativeTabs creativeTab){
        super(name,creativeTab);
        this.setMaxStackSize(1);
    }
    public BlockPos getPosPosition(EntityPlayer playerIn){return new BlockPos(playerIn.posX, playerIn.posY, playerIn.posZ);}
    public Entity getEntity(World world,ItemStack is) {
        return null;
    }
    public Entity getEntityFromPlayer(World world, EntityPlayer player,EnumHand hand){return getEntity(world, player.getHeldItem(hand));}
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote) {
            ItemStack stack = playerIn.getHeldItem(handIn);
            Entity entity = getEntityFromPlayer(worldIn,playerIn,handIn);
            if(entity != null) {
                entity.setPosition(getPosPosition(playerIn).getX(),getPosPosition(playerIn).getY(),getPosPosition(playerIn).getZ());
                if(!(entity instanceof EntitySortilege)) {
                    entity.rotationYaw = playerIn.rotationYaw;
                }
                if(entity instanceof EntityFlyingBroom){
                    if(stack.hasTagCompound() && Boolean.TRUE.equals(Functions.getBooleanNBT(stack, "hascatapultage"))){
                        Sorts.CATAPULTAGE.doAction(playerIn,entity);
                    }
                }
                if(entity instanceof EntityBall){
                    EntityBall eb = (EntityBall) entity;
                    if(!Functions.isInsideBlocks(entity.getPosition(),new BlockPos(eb.x1,eb.y1,eb.z1),new BlockPos(eb.x2,eb.y2,eb.z2))){
                        playerIn.sendMessage(new TextComponentString("\u00A74"+ Messages.NOT_IN_ZONE.getTranslatedText()));

                        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
                    }
                }
                if(this.shouldBeUsed()) {
                    if (!playerIn.isCreative()) {
                        playerIn.setHeldItem(handIn, new ItemStack(playerIn.getHeldItem(handIn).getItem(), playerIn.getHeldItem(handIn).getCount() - 1));
                    }
                }
                if(!playerIn.getHeldItem(handIn).getDisplayName().equals(new ItemStack(playerIn.getHeldItem(handIn).getItem(), 1).getDisplayName())){
                    entity.setCustomNameTag(playerIn.getHeldItem(handIn).getDisplayName());
                }
                worldIn.spawnEntity(entity);

                return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }else{
                playerIn.sendMessage(new TextComponentString("\u00A74"+Messages.LIMITS_NOT_SET.getTranslatedText()));
            }
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
    public boolean shouldBeUsed(){
        return true;
    }
}
