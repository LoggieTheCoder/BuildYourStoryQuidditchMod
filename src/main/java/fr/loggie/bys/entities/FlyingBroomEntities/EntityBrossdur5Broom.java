package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur5Broom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBrossdur5Broom extends EntityFlyingBroom {
    public EntityBrossdur5Broom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.BROSSDUR_5.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.BROSSDUR_5.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.BROSSDUR_5_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderBrossdur5Broom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderBrossdur5Broom.MODEL;
    }

}
