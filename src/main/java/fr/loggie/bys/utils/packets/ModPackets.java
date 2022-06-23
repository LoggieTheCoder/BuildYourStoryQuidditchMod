package fr.loggie.bys.utils.packets;

import fr.loggie.bys.BYS;
import fr.loggie.bys.utils.References;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class ModPackets {
    public static void registerPackets(){
        BYS.PACKETS_CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(References.MODID);
        BYS.PACKETS_CHANNEL.registerMessage(SpawnBriefcaseContentPacket.MessageHandler.class,SpawnBriefcaseContentPacket.class,2, Side.SERVER);
        BYS.PACKETS_CHANNEL.registerMessage(SetBriefcaseFieldsValuePacket.MessageHandler.class,SetBriefcaseFieldsValuePacket.class,1, Side.SERVER);
        BYS.PACKETS_CHANNEL.registerMessage(ExpulsePlayerFromBroom.MessageHandler.class,ExpulsePlayerFromBroom.class,3, Side.CLIENT);
    }
}
