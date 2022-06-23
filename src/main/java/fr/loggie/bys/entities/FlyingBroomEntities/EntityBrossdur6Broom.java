package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur6Broom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBrossdur6Broom extends EntityFlyingBroom {
    public EntityBrossdur6Broom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.BROSSDUR_6.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.BROSSDUR_6.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.BROSSDUR_6_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderBrossdur6Broom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderBrossdur6Broom.MODEL;
    }

}
