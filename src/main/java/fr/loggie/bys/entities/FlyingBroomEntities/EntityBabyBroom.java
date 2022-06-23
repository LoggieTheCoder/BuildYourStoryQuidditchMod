package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBrossdur5Broom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBabyBroom extends EntityFlyingBroom {
    public EntityBabyBroom(World worldIn){
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.BABY_BROOM.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.BABY_BROOM.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        ItemStack is =  new ItemStack(ModItems.NIMBUS_2001_ITEM,1);
        is.setStackDisplayName("Malefoy");
        return is;
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderBrossdur5Broom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderBrossdur5Broom.MODEL;
    }

}
