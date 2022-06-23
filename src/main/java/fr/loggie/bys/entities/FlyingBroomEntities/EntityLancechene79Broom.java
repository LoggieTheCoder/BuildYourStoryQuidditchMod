package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderLancechene79Broom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityLancechene79Broom extends EntityFlyingBroom {
    public EntityLancechene79Broom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.LANCECHENE_79.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.LANCECHENE_79.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.LANCECHENE_79_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderLancechene79Broom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderLancechene79Broom.MODEL;
    }

}
