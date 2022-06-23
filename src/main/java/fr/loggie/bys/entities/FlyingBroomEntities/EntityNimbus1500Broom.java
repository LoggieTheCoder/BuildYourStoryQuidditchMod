package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderNimbus1500Broom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityNimbus1500Broom extends EntityFlyingBroom {
    public EntityNimbus1500Broom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.NIMBUS_1500.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.NIMBUS_1500.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.NIMBUS_1500_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderNimbus1500Broom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderNimbus1500Broom.MODEL;
    }

}
