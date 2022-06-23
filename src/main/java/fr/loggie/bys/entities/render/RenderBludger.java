package fr.loggie.bys.entities.render;

import fr.loggie.bys.entities.EntityBludger;
import fr.loggie.bys.entities.EntityQuaffle;
import fr.loggie.bys.entities.model.ModelBludger;
import fr.loggie.bys.entities.model.ModelQuaffle;
import fr.loggie.bys.utils.References;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderBludger extends RenderLiving<EntityBludger> {
    public static final ResourceLocation TEXTURES = new ResourceLocation(References.MODID+":textures/entity/bludger.png");
    public RenderBludger(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelBludger(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityBludger entity) {
        return TEXTURES;
    }
}
