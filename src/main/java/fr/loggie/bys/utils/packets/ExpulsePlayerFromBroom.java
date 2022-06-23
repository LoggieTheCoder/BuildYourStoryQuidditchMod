package fr.loggie.bys.utils.packets;

import fr.loggie.bys.entities.EntityFlyingBroom;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.Charset;

public class ExpulsePlayerFromBroom implements IMessage {
    int id;

    public ExpulsePlayerFromBroom(){}
    public ExpulsePlayerFromBroom(int ID){
        this.id = ID;

    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.id = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(id);
    }
    public static class MessageHandler implements IMessageHandler<ExpulsePlayerFromBroom, IMessage> {
        @Override
        public IMessage onMessage(ExpulsePlayerFromBroom message, MessageContext ctx) {
            if(Minecraft.getMinecraft().world.getEntityByID(message.id) !=null) {
                if(Minecraft.getMinecraft().world.getEntityByID(message.id).getPassengers().size()==1) {
                    EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().world.getEntityByID(message.id).getPassengers().get(0);

                    if(Minecraft.getMinecraft().world.getEntityByID(message.id) instanceof EntityFlyingBroom){
                        //((EntityFlyingBroom) Minecraft.getMinecraft().world.getEntityByID(message.id)).doRiderCatapultage();
                        ((EntityFlyingBroom) Minecraft.getMinecraft().world.getEntityByID(message.id)).client_only_doNextTick = true;
                        ((EntityFlyingBroom) Minecraft.getMinecraft().world.getEntityByID(message.id)).client_player = (EntityPlayer) Minecraft.getMinecraft().world.getEntityByID(message.id).getPassengers().get(0);
                    }
                    player.dismountRidingEntity();
                }
            }
            return null;
        }
    }
}
