package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderComete290Broom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityComete290Broom extends EntityFlyingBroom {
    public EntityComete290Broom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.COMETE_290.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.COMETE_290.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.COMETE_290_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderComete290Broom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderComete290Broom.MODEL;
    }

}
