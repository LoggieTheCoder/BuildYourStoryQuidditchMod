package fr.loggie.bys.items.spawn_items.spawn_brooms;

import fr.loggie.bys.entities.FlyingBroomEntities.EntityBrossdur1Broom;
import fr.loggie.bys.entities.FlyingBroomEntities.EntityNimbus1000Broom;
import fr.loggie.bys.items.abstract_items.SpawnEntityItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpawnNimbus1000 extends SpawnEntityItem {
    public SpawnNimbus1000(String name, CreativeTabs creativeTab) {
        super(name, creativeTab);
    }

    @Override
    public Entity getEntity(World world, ItemStack stack) {
        return new EntityNimbus1000Broom(world);
    }
}
