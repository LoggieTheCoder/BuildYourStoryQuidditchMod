package fr.loggie.bys.entities.render;

import fr.loggie.bys.entities.EntityQuaffle;
import fr.loggie.bys.entities.model.ModelQuaffle;

import fr.loggie.bys.utils.References;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderQuaffle extends RenderLiving<EntityQuaffle> {
    public static final ResourceLocation TEXTURES = new ResourceLocation(References.MODID+":textures/entity/quaffle.png");
    public RenderQuaffle(RenderManager rendermanagerIn) {
        super(rendermanagerIn,new ModelQuaffle(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityQuaffle entity) {
        return TEXTURES;
    }
}
