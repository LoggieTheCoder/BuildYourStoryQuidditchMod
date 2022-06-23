package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderComete180Broom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityComete180Broom extends EntityFlyingBroom {
    public EntityComete180Broom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.COMETE_180.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.COMETE_180.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.COMETE_180_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderComete180Broom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderComete180Broom.MODEL;
    }

}
