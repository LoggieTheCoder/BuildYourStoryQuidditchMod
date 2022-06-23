package fr.loggie.bys.items.spawn_items.spawn_brooms;

import fr.loggie.bys.entities.FlyingBroomEntities.EntityBabyBroom;
import fr.loggie.bys.entities.FlyingBroomEntities.EntityBaletoileXXIBroom;
import fr.loggie.bys.entities.FlyingBroomEntities.EntityBrossdur1Broom;
import fr.loggie.bys.entities.FlyingBroomEntities.EntityNimbus2001Broom;
import fr.loggie.bys.items.abstract_items.SpawnEntityItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemClock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class SpawnNimbus2001 extends SpawnEntityItem {
    public SpawnNimbus2001(String name, CreativeTabs creativeTab) {
        super(name, creativeTab);
        this.addPropertyOverride(new ResourceLocation("ismalefoy"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            @Override
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return (stack.getDisplayName().equalsIgnoreCase("Malefoy") || stack.getDisplayName().equalsIgnoreCase("Malfoy"))?1:0;
            }
        });
    }

    @Override
    public Entity getEntity(World world, ItemStack stack) {
        return (stack.getDisplayName().equalsIgnoreCase("Malefoy") || stack.getDisplayName().equalsIgnoreCase("Malfoy"))?new EntityBabyBroom(world) :new EntityNimbus2001Broom(world);
    }
}
