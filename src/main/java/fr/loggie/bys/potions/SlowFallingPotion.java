package fr.loggie.bys.potions;

import fr.loggie.bys.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class SlowFallingPotion extends Potion {

    public SlowFallingPotion(String name,boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
        setPotionName("effect."+name);
        setRegistryName(new ResourceLocation(References.MODID+":"+name));
    }

    @Override
    public boolean hasStatusIcon() {
       // Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(References.MODID+"/textures/gui/potion_effects.png"));
        return false;
    }
}
