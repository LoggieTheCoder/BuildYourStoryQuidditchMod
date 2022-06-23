package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderNimbus2001Broom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityNimbus2001Broom extends EntityFlyingBroom {
    public EntityNimbus2001Broom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.NIMBUS_2001.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.NIMBUS_2001.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.NIMBUS_2001_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderNimbus2001Broom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderNimbus2001Broom.MODEL;
    }

}
