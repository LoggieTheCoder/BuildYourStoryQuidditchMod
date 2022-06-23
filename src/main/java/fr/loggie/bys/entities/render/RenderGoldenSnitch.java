package fr.loggie.bys.entities.render;

import fr.loggie.bys.entities.EntityGoldenSnitch;
import fr.loggie.bys.entities.EntityQuaffle;
import fr.loggie.bys.entities.model.ModelGoldenSnitch;
import fr.loggie.bys.entities.model.ModelQuaffle;
import fr.loggie.bys.utils.References;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderGoldenSnitch extends RenderLiving<EntityGoldenSnitch> {
    public static final ResourceLocation TEXTURES = new ResourceLocation(References.MODID+":textures/entity/golden_snitch.png");
    public RenderGoldenSnitch(RenderManager rendermanagerIn) {
        super(rendermanagerIn,new ModelGoldenSnitch(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityGoldenSnitch entity) {
        return TEXTURES;
    }
}
