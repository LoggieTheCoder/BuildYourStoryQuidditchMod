package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderFireboltBroom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFireboltBroom extends EntityFlyingBroom {
    public EntityFireboltBroom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.FIREBOLT.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.FIREBOLT.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.FIREBOLT_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderFireboltBroom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderFireboltBroom.MODEL;
    }

}
