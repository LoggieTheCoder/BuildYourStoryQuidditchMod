package fr.loggie.bys.utils.packets;

import fr.loggie.bys.blocks.BriefcaseBlock;
import fr.loggie.bys.containers.ContainerBriefcase;
import fr.loggie.bys.entities.EntityBall;
import fr.loggie.bys.init.ModBlocks;
import fr.loggie.bys.items.abstract_items.SpawnBall;
import fr.loggie.bys.tilentities.TileEntityBriefcase;
import fr.loggie.bys.utils.Functions;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SpawnBriefcaseContentPacket implements IMessage {

    int x;
    int y;
    int z;

    public SpawnBriefcaseContentPacket(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public SpawnBriefcaseContentPacket(){}

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();

    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }
    public static class MessageHandler implements IMessageHandler<SpawnBriefcaseContentPacket,IMessage>{

        @Override
        public IMessage onMessage(SpawnBriefcaseContentPacket message, MessageContext ctx) {

            BlockPos pos = new BlockPos(message.x,message.y,message.z);
            TileEntity myb_te = ctx.getServerHandler().player.world.getTileEntity(pos);
            Block myb_block = ctx.getServerHandler().player.world.getBlockState(pos).getBlock();
            if(myb_te instanceof TileEntityBriefcase && myb_block instanceof BriefcaseBlock){
                TileEntityBriefcase te= (TileEntityBriefcase) myb_te;
                int j =0;
                BlockPos[] positions = te.getGuiPositions();
                for (ItemStack is : te.getChestContents()) {
                    if(!is.isEmpty()){
                        if(is.getItem() instanceof SpawnBall){
                            EntityBall entity = ((SpawnBall) is.getItem()).getBrutEntity(ctx.getServerHandler().player.world);
                            if(positions == null) {
                                if (Functions.getDoubleNBT(is, SpawnBall.x1) != null && Functions.getDoubleNBT(is, SpawnBall.y1) != null && Functions.getDoubleNBT(is, SpawnBall.z1) != null && Functions.getDoubleNBT(is, SpawnBall.x2) != null && Functions.getDoubleNBT(is, SpawnBall.y2) != null && Functions.getDoubleNBT(is, SpawnBall.z2) != null) {
                                    entity.x1 = Functions.getDoubleNBT(is, SpawnBall.x1);
                                    entity.y1 = Functions.getDoubleNBT(is, SpawnBall.y1);
                                    entity.z1 = Functions.getDoubleNBT(is, SpawnBall.z1);
                                    entity.x2 = Functions.getDoubleNBT(is, SpawnBall.x2);
                                    entity.y2 = Functions.getDoubleNBT(is, SpawnBall.y2);
                                    entity.z2 = Functions.getDoubleNBT(is, SpawnBall.z2);
                                    spawnBall(entity, message, ctx, te, j);
                                }
                            }else {
                                entity.x1 = positions[0].getX();
                                entity.y1 = positions[0].getY();
                                entity.z1 = positions[0].getZ();
                                entity.x2 = positions[1].getX();
                                entity.y2 = positions[1].getY();
                                entity.z2 = positions[1].getZ();
                                spawnBall(entity,message,ctx,te,j);
                            }

                        }
                    }j++;
                }
                ItemStack bluger1 = te.getStackInSlot(0);
                ItemStack quaffle = te.getStackInSlot(1);
                ItemStack golden_snitch = te.getStackInSlot(2);
                ItemStack bluger2 = te.getStackInSlot(3);
                boolean b1 = !bluger1.isEmpty();
                boolean q = !quaffle.isEmpty();
                boolean gs = !golden_snitch.isEmpty();
                boolean b2 = !bluger2.isEmpty();
                IBlockState state = ctx.getServerHandler().player.world.getBlockState(pos);
                IBlockState state2 = ModBlocks.BRIEFCASE.getDefaultState().withProperty(BriefcaseBlock.BLUDGER1,b1)
                        .withProperty(BriefcaseBlock.BLUDGER2,b2)
                        .withProperty(BriefcaseBlock.GOLDEN_SNITCH,gs)
                        .withProperty(BriefcaseBlock.QUAFFLE,q)
                        .withProperty(BriefcaseBlock.OPEN,state.getValue(BriefcaseBlock.OPEN))
                        .withProperty(BriefcaseBlock.FACING,state.getValue(BriefcaseBlock.FACING));
                ctx.getServerHandler().player.world.setBlockState(pos,state2);


            }
            return null;
        }
        public static void spawnBall(EntityBall entity,SpawnBriefcaseContentPacket message, MessageContext ctx,TileEntityBriefcase te ,int j){
            if(Functions.isInsideBlocks(new BlockPos(message.x, message.y, message.z), new BlockPos(entity.x1,entity.y1,entity.z1), new BlockPos(entity.x2,entity.y2,entity.z2))){
                entity.posX = message.x;
                entity.posY = message.y+3;
                entity.posZ = message.z;
                ctx.getServerHandler().player.world.spawnEntity(entity);
                te.setInventorySlotContents(j,new ItemStack(Blocks.AIR));
            }
        }
    }
}
