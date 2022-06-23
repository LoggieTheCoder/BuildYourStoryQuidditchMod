package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;

import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBrossdur1Broom extends EntityFlyingBroom {
    public EntityBrossdur1Broom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.BROSSDUR_1.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.BROSSDUR_1.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.BROSSDUR_1_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderBrossdur1Broom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderBrossdur1Broom.MODEL;
    }

}
