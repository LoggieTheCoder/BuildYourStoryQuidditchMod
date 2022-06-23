package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderFiretopowdersBroom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFiretopowdersBroom extends EntityFlyingBroom {
    public EntityFiretopowdersBroom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.FIRETOPOWDERS.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.FIRETOPOWDERS.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.FIRETOPOWDERS_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderFiretopowdersBroom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderFiretopowdersBroom.MODEL;
    }

}
