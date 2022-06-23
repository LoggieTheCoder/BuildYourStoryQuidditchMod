package fr.loggie.bys.utils.handlers;

import fr.loggie.bys.entities.EntityBludger;
import fr.loggie.bys.entities.EntityGoldenSnitch;
import fr.loggie.bys.entities.EntityQuaffle;
import fr.loggie.bys.entities.FlyingBroomEntities.*;
import fr.loggie.bys.entities.render.RenderBludger;
import fr.loggie.bys.entities.render.RenderGoldenSnitch;
import fr.loggie.bys.entities.render.RenderQuaffle;
import fr.loggie.bys.entities.render.brooms.*;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
    public static void registerEntityRenders(){
        RenderingRegistry.registerEntityRenderingHandler(EntityQuaffle.class, RenderQuaffle::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGoldenSnitch.class, RenderGoldenSnitch::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBludger.class, RenderBludger::new);
        //BROOMS
        RenderingRegistry.registerEntityRenderingHandler(EntityVarapidosBroom.class, RenderVarapidosBroom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBrossdur1Broom.class, RenderBrossdur1Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBrossdur3Broom.class, RenderBrossdur3Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBrossdur5Broom.class, RenderBrossdur5Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBrossdur6Broom.class, RenderBrossdur6Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBrossdur7Broom.class, RenderBrossdur7Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBrossdur11Broom.class, RenderBrossdur11Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityComete140Broom.class, RenderComete140Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityComete180Broom.class, RenderComete180Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityComete260Broom.class, RenderComete260Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityComete290Broom.class, RenderComete290Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityEtoileFilanteBroom.class, RenderEtoileFilanteBroom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFireboltBroom.class, RenderFireboltBroom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFiretopowdersBroom.class, RenderFiretopowdersBroom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFriseluneBroom.class, RenderFriseluneBroom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLancechene79Broom.class, RenderLancechene79Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityManchevifBroom.class, RenderManchevifBroom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMargotin90Broom.class, RenderMargotin90Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityNimbus1000Broom.class, RenderNimbus1000Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityNimbus1001Broom.class, RenderNimbus1001Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityNimbus1500Broom.class, RenderNimbus1500Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityNimbus1700Broom.class, RenderNimbus1700Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityNimbus2000Broom.class, RenderNimbus2000Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityNimbus2001Broom.class, RenderNimbus2001Broom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySteelArrowBroom.class, RenderSteelArrowBroom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFireboltSupremeBroom.class, RenderFireboltSupremeBroom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityYajirushiBroom.class, RenderYajirushiBroom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBaletoileXXIBroom.class, RenderBaletoileXXIBroom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBlueBombBroom.class, RenderBlueBombBroom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBabyBroom.class, RenderBabyBroom::new);

    }
}
