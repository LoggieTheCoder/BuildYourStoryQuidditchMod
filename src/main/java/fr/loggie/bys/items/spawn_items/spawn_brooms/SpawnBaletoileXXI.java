package fr.loggie.bys.items.spawn_items.spawn_brooms;

import fr.loggie.bys.entities.FlyingBroomEntities.EntityBaletoileXXIBroom;
import fr.loggie.bys.items.abstract_items.SpawnEntityItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpawnBaletoileXXI extends SpawnEntityItem {
    public SpawnBaletoileXXI(String name, CreativeTabs creativeTab) {
        super(name, creativeTab);
    }

    @Override
    public Entity getEntity(World world, ItemStack stack) {
        return new EntityBaletoileXXIBroom(world);
    }

}