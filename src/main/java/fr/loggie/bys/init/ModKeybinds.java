package fr.loggie.bys.init;

import fr.loggie.bys.utils.References;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jline.terminal.impl.jna.osx.CLibrary;

import java.awt.event.KeyEvent;

@SideOnly(Side.CLIENT)
public class ModKeybinds {
    public static KeyBinding accelerateKey;
    public static KeyBinding deccelerateKey;
    public static KeyBinding salto_key;
    public static KeyBinding SORTS_KEY;

    public static void register(final FMLInitializationEvent e){
        accelerateKey =create("accelerate_key", 200);//Touche haut
        ClientRegistry.registerKeyBinding(accelerateKey);
        deccelerateKey =create("deccelerate_key", 208);//Touche bas
        ClientRegistry.registerKeyBinding(deccelerateKey);
        salto_key=create("salto_key", KeyEvent.VK_S);//touche droite
        ClientRegistry.registerKeyBinding(salto_key);
        SORTS_KEY = create("sorts_key",KeyEvent.VK_ALT);//touche O
        ClientRegistry.registerKeyBinding(SORTS_KEY);
    }
    private static KeyBinding create(String name, int key){
        return new KeyBinding("key."+ References.MODID+"."+name,key,"key.category."+References.MODID);
    }
}
