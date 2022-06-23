package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderSteelArrowBroom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntitySteelArrowBroom extends EntityFlyingBroom {
    public EntitySteelArrowBroom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.STEEL_ARROW_BROOM.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.STEEL_ARROW_BROOM.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.STEEL_ARROW_BROOM_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderSteelArrowBroom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderSteelArrowBroom.MODEL;
    }

}
