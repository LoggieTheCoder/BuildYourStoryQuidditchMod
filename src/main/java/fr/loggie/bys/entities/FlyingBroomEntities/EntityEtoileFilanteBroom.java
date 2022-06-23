package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderEtoileFilanteBroom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityEtoileFilanteBroom extends EntityFlyingBroom {
    public EntityEtoileFilanteBroom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.ETOILE_FILANTE.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.ETOILE_FILANTE.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.ETOILE_FILANTE_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderEtoileFilanteBroom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderEtoileFilanteBroom.MODEL;
    }

}
