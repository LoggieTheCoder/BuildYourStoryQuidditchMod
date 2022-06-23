package fr.loggie.bys.init;

import fr.loggie.bys.BYS;
import fr.loggie.bys.entities.*;
import fr.loggie.bys.entities.FlyingBroomEntities.EntityVarapidosBroom;
import fr.loggie.bys.entities.FlyingBroomEntities.EntityBrossdur1Broom;
import fr.loggie.bys.utils.id.EntitiesID;
import fr.loggie.bys.utils.References;
import net.minecraft.entity.Entity;

import net.minecraft.util.ResourceLocation;

import net.minecraft.util.datafix.fixes.EntityId;
import net.minecraftforge.fml.common.registry.EntityRegistry;



public class ModEntities{
    public static void registerEntities()
    {
        registerEntity("sortilege", EntitySortilege.class,EntitiesID.SORTILEGE_ID,References.VISION_ENTITY_RANGE,-1,-1);
        registerEntity("quaffle", EntityQuaffle.class, EntitiesID.QUAFFLE_ID, References.VISION_BALLS_RANGE, -1, -1);
        registerEntity("golden_snitch", EntityGoldenSnitch.class, EntitiesID.GOLDEN_SNITCH_ID, References.VISION_BALLS_RANGE, -1, -1);
        registerEntity("bludger", EntityBludger.class, EntitiesID.BLUDGER_ID, References.VISION_BALLS_RANGE, -1, -1);

        registerBrooms();

    }
    private static void registerBrooms(){
        for (int i = 1; i < EntityFlyingBroom.FlyingBrooms.values().length; i++) {
            registerFlyingBroomEntity(EntityFlyingBroom.FlyingBrooms.getElement(i).getCustomName(),EntityFlyingBroom.FlyingBrooms.getElement(i).getEntityClass(),i+EntitiesID.BROOMS_BASE-1);
        }
    }
    /** POUR CHACUN {@link fr.loggie.bys.utils.handlers.RenderHandler}
     * AJOUTER AUSSI UNE CLASSE RENDER
     * UNE CLASSE MODEL                     (non nécessaire pour les balais)
     * UNE CLASSE ENTITE
     * UNE CONFIGURATION {@link EntitiesID} (non nécessaire pour les balais)
     * UN ITEM DE SPAWN {@link ModItems}
     * UN MODEL JSON et UNE TEXTURE
    **/
    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2) {
        if (color1 < 0 || color2 < 0) {
            EntityRegistry.registerModEntity(new ResourceLocation(References.MODID + ":" + name), entity, name, id, BYS.instance, range, 1, true);
        } else {
            EntityRegistry.registerModEntity(new ResourceLocation(References.MODID + ":" + name), entity, name, id, BYS.instance, range, 1, true, color1, color2);

        }
    }
    private static void registerFlyingBroomEntity(String name,Class<? extends EntityFlyingBroom > entity_class,int id){
        registerEntity(name, entity_class,id,References.VISION_ENTITY_RANGE,-1,-1);
    }

}
