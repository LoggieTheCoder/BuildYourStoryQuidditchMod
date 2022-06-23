package fr.loggie.bys.entities.model;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.obj.model.ObjModelRenderer;
import fr.loggie.bys.utils.References;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * ModelGoldenSnitch - loggie
 * Created using Tabula 6.0.0
 */
public class ModelGoldenSnitch extends ModelObjBase {
    public ModelGoldenSnitch() {
        super(new ResourceLocation(References.MODID, "models/entity/golden_snitch.obj"));
    }

    protected ObjModelRenderer lwing;
    protected ObjModelRenderer rwing;
    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GlStateManager.pushMatrix();
        GlStateManager.scale(2.0,2.0,2.0);
        GlStateManager.rotate(180,1.0F,0.0F,0.0F);
        model.renderAll(scale);
        GlStateManager.popMatrix();
    }

    @Override
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

            if(renderer.getName().equalsIgnoreCase("Body")) {
                body = renderer;
            }
            if(renderer.getName().equalsIgnoreCase("LeftWing")) {
                lwing = renderer;
            }
            if(renderer.getName().equalsIgnoreCase("RightWing")) {
                rwing = renderer;
            }


        }

        // WARNING: When(if) you add children to some other parts, be sure to call model#clearDuplications() method
        // after adding all children, as you can see here.
        model.clearDuplications();
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

        this.lwing.rotateAngleZ = 0.5F*MathHelper.cos((limbSwing*30F));
        this.rwing.rotateAngleZ = -0.5F*MathHelper.cos((limbSwing*30F));

    }
}