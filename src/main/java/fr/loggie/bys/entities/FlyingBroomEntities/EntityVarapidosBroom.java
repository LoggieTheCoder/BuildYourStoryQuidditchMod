package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;

import fr.loggie.bys.entities.render.brooms.RenderVarapidosBroom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityVarapidosBroom extends EntityFlyingBroom {

    public EntityVarapidosBroom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.VARAPIDOS.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.VARAPIDOS.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.VARAPIDOS_ITEM, 1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderVarapidosBroom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderVarapidosBroom.MODEL;
    }
}
