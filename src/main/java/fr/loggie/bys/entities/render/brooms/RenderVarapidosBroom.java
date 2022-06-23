package fr.loggie.bys.entities.render.brooms;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.FlyingBroomEntities.EntityVarapidosBroom;
import fr.loggie.bys.entities.model.brooms.ModelBroomBase;
import fr.loggie.bys.utils.References;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderVarapidosBroom extends RenderLiving<EntityVarapidosBroom> {
    public static final String UNLOCALISED_NAME = EntityFlyingBroom.FlyingBrooms.VARAPIDOS.getCustomName();
    public static final ResourceLocation TEXTURES = new ResourceLocation(References.MODID+":textures/entity/"+UNLOCALISED_NAME+".png");
    public static final ModelBase MODEL = new ModelBroomBase(UNLOCALISED_NAME);
    public RenderVarapidosBroom(RenderManager rendermanagerIn) {
        super(rendermanagerIn,MODEL, 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(
            EntityVarapidosBroom entity)
    {return TEXTURES;}
}
