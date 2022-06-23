package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderNimbus1001Broom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityNimbus1001Broom extends EntityFlyingBroom {
    public EntityNimbus1001Broom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.NIMBUS_1001.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.NIMBUS_1001.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.NIMBUS_1001_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderNimbus1001Broom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderNimbus1001Broom.MODEL;
    }

}
