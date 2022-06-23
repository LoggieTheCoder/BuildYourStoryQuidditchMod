package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBaletoileXXIBroom;
import fr.loggie.bys.entities.render.brooms.RenderYajirushiBroom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityYajirushiBroom extends EntityFlyingBroom {
    public EntityYajirushiBroom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.YAJIRUSHI.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.YAJIRUSHI.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.YAJIRUSHI_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderYajirushiBroom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderYajirushiBroom.MODEL;
    }

}
