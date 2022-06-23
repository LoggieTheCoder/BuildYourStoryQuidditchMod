package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderFireboltSupremeBroom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFireboltSupremeBroom extends EntityFlyingBroom {
    public EntityFireboltSupremeBroom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.YAJIRUSHI.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.YAJIRUSHI.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.FIREBOLT_SUPREME_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderFireboltSupremeBroom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderFireboltSupremeBroom.MODEL;
    }

}
