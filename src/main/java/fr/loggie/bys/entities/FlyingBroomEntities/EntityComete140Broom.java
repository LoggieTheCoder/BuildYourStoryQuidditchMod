package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur1Broom;
import fr.loggie.bys.entities.render.brooms.RenderComete140Broom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityComete140Broom extends EntityFlyingBroom {
    public EntityComete140Broom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.COMETE_140.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.COMETE_140.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.COMETE_140_ITEM,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderComete140Broom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderComete140Broom.MODEL;
    }

}
