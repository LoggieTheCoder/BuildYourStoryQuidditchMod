package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur11Broom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBrossdur11Broom extends EntityFlyingBroom {
    public EntityBrossdur11Broom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.BROSSDUR_11.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.BROSSDUR_11.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.BROSSDUR_11_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderBrossdur11Broom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderBrossdur11Broom.MODEL;
    }

}
