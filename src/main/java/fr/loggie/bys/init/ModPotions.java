package fr.loggie.bys.init;

import fr.loggie.bys.potions.SlowFallingPotion;

import net.minecraft.potion.Potion;

import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModPotions
{
    public static final Potion SLOW_FALLING_EFFECT = new SlowFallingPotion("slow_falling",false,65535 );

    public static void registerPotions(){
        registerPotion(SLOW_FALLING_EFFECT);
    }
    protected static void registerPotion(Potion potion){
        ForgeRegistries.POTIONS.register(potion);
    }
}
