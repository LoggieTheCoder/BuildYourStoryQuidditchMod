package fr.loggie.bys.entities.model;

import fr.loggie.bys.utils.References;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;


public class ModelQuaffle extends ModelObjBase {
    public ModelQuaffle() {
        super(new ResourceLocation(References.MODID, "models/entity/quaffle.obj"));
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GlStateManager.pushMatrix();

        GlStateManager.scale(2.0,2.0,2.0);
        GlStateManager.translate(0,0.5,0);
        GlStateManager.rotate(180,1.0F,0.0F,0.0F);
        model.renderAll(scale);
        GlStateManager.popMatrix();
    }

}

