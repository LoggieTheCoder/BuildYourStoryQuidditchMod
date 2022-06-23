package fr.loggie.bys.entities.model.brooms;

import fr.loggie.bys.entities.obj.ObjModelLoader;
import fr.loggie.bys.entities.obj.model.ObjModelBase;
import fr.loggie.bys.entities.obj.model.ObjModelRenderer;
import fr.loggie.bys.utils.Functions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.debug.DebugRendererCollisionBox;
import net.minecraft.entity.Entity;

import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.FOVUpdateEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLSync;
import scala.Function;
import scala.Function0$mcS$sp;
import scala.reflect.macros.Universe;

import javax.annotation.ParametersAreNonnullByDefault;

public abstract class ModelObjBroom extends ObjModelBase {
    private ObjModelRenderer body;
    private final double size;
    public ModelObjBroom(ResourceLocation resourceLocation,double size) {
        super(ObjModelLoader.load(resourceLocation));
        this.size = size;
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
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
       // this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GlStateManager.pushMatrix();
        GlStateManager.scale(this.size*8.0,this.size*8.0,this.size*8.0);
        GlStateManager.rotate(180,0.0F,0.0F,1.0F);
        GlStateManager.translate(0,-1*(0.9/(this.size*8.0)),0);
        this.body.render(scale);
        GlStateManager.popMatrix();
    }
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.body.rotateAngleY = netHeadYaw * 0.017453292F;
        this.body.rotateAngleX = headPitch * 0.017453292F;
    }

    /**@Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GlStateManager.pushMatrix();
        GlStateManager.scale(this.size*8.0,this.size*8.0,this.size*8.0);
        //GlStateManager.rotate(headPitch,1.0F,0.0F,0.0F);
        BlockPos playerPos = Minecraft.getMinecraft().player.getPosition();
        BlockPos entityPos = entityIn.getPosition();
        BlockPos difference = Functions.getBlockPosDifference(playerPos,entityPos);
        GlStateManager.translate(difference.getX(),difference.getY(),difference.getZ());
        model.renderAll(scale);
        GlStateManager.popMatrix();

    }

    @Override
    @ParametersAreNonnullByDefault
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.body.rotateAngleY = netHeadYaw * 0.017453292F;
        this.body.rotateAngleX = headPitch* 0.017453292F;



        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }**/


}