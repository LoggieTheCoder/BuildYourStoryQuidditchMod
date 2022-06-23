package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderMargotin90Broom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityMargotin90Broom extends EntityFlyingBroom {
    public EntityMargotin90Broom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.MARGOTIN_90.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.MARGOTIN_90.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.MARGOTIN_90_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderMargotin90Broom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderMargotin90Broom.MODEL;
    }

}
