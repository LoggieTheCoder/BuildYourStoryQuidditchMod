package fr.loggie.bys.entities.FlyingBroomEntities;

import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.entities.render.brooms.RenderBaletoileXXIBroom;
import fr.loggie.bys.entities.render.brooms.RenderBlueBombBroom;
import fr.loggie.bys.init.ModItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBaletoileXXIBroom extends EntityFlyingBroom {
    public EntityBaletoileXXIBroom(World worldIn) {
        super(worldIn);
        this.ACCELERATION = FlyingBrooms.BALETOILE_XXI.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.BALETOILE_XXI.getMaxSpeed();
    }

    @Override
    public ItemStack getLoot() {
        return new ItemStack(ModItems.BALETOILE_XXI,1);
    }

    @Override
    public ResourceLocation getTexture() {
        return RenderBaletoileXXIBroom.TEXTURES;
    }

    @Override
    public ModelBase getModel() {
        return RenderBaletoileXXIBroom.MODEL;
    }

}
