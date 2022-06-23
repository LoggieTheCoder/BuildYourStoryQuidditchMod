package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderFriseluneBroom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFriseluneBroom extends EntityFlyingBroom {
    public EntityFriseluneBroom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.FRISELUNE.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.FRISELUNE.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.FRISELUNE_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderFriseluneBroom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderFriseluneBroom.MODEL;
    }

}
