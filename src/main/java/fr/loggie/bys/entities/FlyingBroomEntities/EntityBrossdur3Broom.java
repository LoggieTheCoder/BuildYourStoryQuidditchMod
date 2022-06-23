package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur3Broom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBrossdur3Broom extends EntityFlyingBroom {
    public EntityBrossdur3Broom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.BROSSDUR_3.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.BROSSDUR_3.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.BROSSDUR_3_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderBrossdur3Broom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderBrossdur3Broom.MODEL;
    }

}
