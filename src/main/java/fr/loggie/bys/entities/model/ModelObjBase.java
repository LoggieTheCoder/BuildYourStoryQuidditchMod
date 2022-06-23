package fr.loggie.bys.entities.model;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.obj.ObjModelLoader;
import fr.loggie.bys.entities.obj.model.ObjModelBase;
import fr.loggie.bys.entities.obj.model.ObjModelRenderer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.core.layout.GelfLayout;

import javax.annotation.ParametersAreNonnullByDefault;

public abstract class ModelObjBase extends ObjModelBase {
    protected ObjModelRenderer body;
    public ModelObjBase(ResourceLocation resourceLocation) {
        super(ObjModelLoader.load(resourceLocation));
        this.init();
    }
    public void init(){
        // Here you can see that I pull some parts out of model to control their rendering.
        // I also add children to other parts in order to not render each thing separately.
        // (why will you do it for every hair on the hair separately for example)
        // This will be useful if you have not grouped the models while creating the obj file.
        //
        // As an example I add "ph_headfeather_small_L" part to "head". So the feather on the head is now part of head and can be rotated as head rotates.
        //
        // WARNING: When you add children to some other parts, be sure to call model#clearDuplications() method
        // after adding all children, as you can see below.
        for (ObjModelRenderer renderer : model.getParts()) {
            body = renderer;
        }

        // WARNING: When(if) you add children to some other parts, be sure to call model#clearDuplications() method
        // after adding all children, as you can see here.
        model.clearDuplications();
    }
    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GlStateManager.pushMatrix();
        GlStateManager.scale(2.0,2.0,2.0);
        GlStateManager.rotate(180,1.0F,0.0F,0.0F);
        model.renderAll(scale);
        GlStateManager.popMatrix();
    }

    private void setRotation(ObjModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    @Override
    @ParametersAreNonnullByDefault
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.body.rotateAngleY = netHeadYaw * 0.017453292F;
        this.body.rotateAngleX = entityIn instanceof EntityFlyingBroom ?  ((EntityFlyingBroom) entityIn).flyingBroomPitch : headPitch * 0.017453292F;
    }


}
