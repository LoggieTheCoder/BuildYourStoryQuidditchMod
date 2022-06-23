package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderManchevifBroom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityManchevifBroom extends EntityFlyingBroom {
    public EntityManchevifBroom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.MANCHEVIF.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.MANCHEVIF.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.MANCHEVIF_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderManchevifBroom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderManchevifBroom.MODEL;
    }

}
