package fr.loggie.bys;


import fr.loggie.bys.events.BYSEvent;
import fr.loggie.bys.events.RegisteringEvent;
import fr.loggie.bys.init.ModItems;
import fr.loggie.bys.init.ModKeybinds;
import fr.loggie.bys.proxy.CommonProxy;
import fr.loggie.bys.tabs.BuildYourStoryTab;
import fr.loggie.bys.tabs.QuidditchTab;
import fr.loggie.bys.utils.References;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketJoinGame;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid= References.MODID,name=References.NAME,version=References.VERSION)
public class BYS{

    public static SimpleNetworkWrapper PACKETS_CHANNEL;
    public static final CreativeTabs QUIDDITCH_TAB = new QuidditchTab("quidditchtab","bys.png");

    @Mod.Instance(References.MODID)
    public static BYS instance;

    @SidedProxy(clientSide = References.C_P,serverSide = References.S_P)
    public static CommonProxy proxy;

    public BYS(){
        MinecraftForge.EVENT_BUS.register(new BYSEvent());
        MinecraftForge.EVENT_BUS.register(new RegisteringEvent());
    }
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){

        proxy.preInit();
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        ModKeybinds.register(e) ;
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){
        proxy.postInit();
    }

}
