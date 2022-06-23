package fr.loggie.bys.utils.packets;

import fr.loggie.bys.tilentities.TileEntityBriefcase;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SetBriefcaseFieldsValuePacket implements IMessage {
    int pos1X;
    int pos1Y;
    int pos1Z;
    int pos2X;
    int pos2Y;
    int pos2Z;
    BlockPos pos;
    public SetBriefcaseFieldsValuePacket(BlockPos pos, int pos1X, int pos1Y, int pos1Z, int pos2X, int pos2Y, int pos2Z ){
        this.pos1X = pos1X;
        this.pos1Y = pos1Y;
        this.pos1Z = pos1Z;
        this.pos2X = pos2X;
        this.pos2Y = pos2Y;
        this.pos2Z = pos2Z;
        this.pos = pos;
    }
    public SetBriefcaseFieldsValuePacket(){}

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos1X = buf.readInt();
        this.pos1Y = buf.readInt();
        this.pos1Z = buf.readInt();
        this.pos2X = buf.readInt();
        this.pos2Y = buf.readInt();
        this.pos2Z = buf.readInt();
        this.pos = new BlockPos(buf.readInt(),buf.readInt(),buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(pos1X);
        buf.writeInt(pos1Y);
        buf.writeInt(pos1Z);
        buf.writeInt(pos2X);
        buf.writeInt(pos2Y);
        buf.writeInt(pos2Z);
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
    }

    public static class MessageHandler implements IMessageHandler<SetBriefcaseFieldsValuePacket,IMessage>{

        @Override
        public IMessage onMessage(SetBriefcaseFieldsValuePacket message, MessageContext ctx) {

            if(ctx.getServerHandler().player.world.getTileEntity(message.pos) instanceof TileEntityBriefcase){
                TileEntityBriefcase te = (TileEntityBriefcase) ctx.getServerHandler().player.world.getTileEntity(message.pos);
                assert te != null;
                te.pos1X = Integer.toString(message.pos1X);
                te.pos1Y = Integer.toString(message.pos1Y);
                te.pos1Z = Integer.toString(message.pos1Z);
                te.pos2X = Integer.toString(message.pos2X);
                te.pos2Y = Integer.toString(message.pos2Y);
                te.pos2Z = Integer.toString(message.pos2Z);
                te.markDirty();

            }
            return null;
        }
    }
}
