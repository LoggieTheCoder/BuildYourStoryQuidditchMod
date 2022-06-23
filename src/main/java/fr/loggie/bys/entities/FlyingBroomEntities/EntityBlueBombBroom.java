package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBlueBombBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBlueBombBroom extends EntityFlyingBroom {
    public EntityBlueBombBroom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.BLUE_BOMB.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.BLUE_BOMB.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.BLUE_BOMB,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderBlueBombBroom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderBlueBombBroom.MODEL;
    }

}
