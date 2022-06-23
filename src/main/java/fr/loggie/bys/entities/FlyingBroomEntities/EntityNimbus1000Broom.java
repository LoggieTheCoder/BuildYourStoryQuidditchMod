package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderNimbus1000Broom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityNimbus1000Broom extends EntityFlyingBroom {
    public EntityNimbus1000Broom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.NIMBUS_1000.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.NIMBUS_1000.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.NIMBUS_1000_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderNimbus1000Broom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderNimbus1000Broom.MODEL;
    }

}
