package fr.loggie.bys.utils;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.init.ModPotions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public enum Sorts {
    NO_SORT(-1),COUSSINAGE(0),CATAPULTAGE(1),FREINAGE(2);
    public void doAction(Entity entityAttacking,Entity receiving){
        switch (this){
            case NO_SORT:
                break;
            case COUSSINAGE:
            case CATAPULTAGE:
                if(receiving instanceof EntityFlyingBroom){
                    ((EntityFlyingBroom) receiving).addSort(this);
                }
                break;
            case FREINAGE:
                if(receiving instanceof EntityFlyingBroom){
                    ((EntityFlyingBroom) receiving).startFreinage();
                }
                break;
        }
    }
    private final int ID;
    Sorts(final int ID){
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }
    public static Sorts getElementFromID(int id){
        for (Sorts value : Sorts.values()) {
            if(value.getID()==id){
                return value;
            }
        }
        return Sorts.NO_SORT;
    }
    public Sorts next(){
        Sorts result = getElementFromID(this.getID()+1);
        if(result ==  Sorts.NO_SORT){
            result = Sorts.COUSSINAGE;
        }
        return result;
    }
}
