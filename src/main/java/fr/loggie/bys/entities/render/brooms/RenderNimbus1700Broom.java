package fr.loggie.bys.entities.render.brooms;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.FlyingBroomEntities.EntityBrossdur1Broom;
import fr.loggie.bys.entities.FlyingBroomEntities.EntityNimbus1700Broom;
import fr.loggie.bys.entities.model.brooms.ModelBroomBase;
import fr.loggie.bys.utils.References;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderNimbus1700Broom extends RenderLiving<EntityNimbus1700Broom> {
    public static final String UNLOCALISED_NAME = EntityFlyingBroom.FlyingBrooms.NIMBUS_1700.getCustomName();
    public static final ResourceLocation TEXTURES = new ResourceLocation(References.MODID+":textures/entity/"+UNLOCALISED_NAME+".png");
    public static final ModelBase MODEL = new ModelBroomBase(UNLOCALISED_NAME);
    public RenderNimbus1700Broom(RenderManager rendermanagerIn) {
        super(rendermanagerIn,MODEL,0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityNimbus1700Broom entity) {
        return TEXTURES;
    }
}
